package org.taskmates.notification_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.taskmates.notification_service.model.Notification;
import org.taskmates.notification_service.model.dto.NotificationDTO;
import org.taskmates.notification_service.model.entities.UserPreferenceEntity;
import org.taskmates.notification_service.model.enums.ChannelsType;
import org.taskmates.notification_service.model.enums.NotificationType;
import org.taskmates.notification_service.repository.UserPreferencesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@EnableKafka
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationValidationService notificationValidationService;
    private final NotificationPriorityService notificationPriorityService;
    private final NotificationTemplateService notificationTemplateService;

    private final UserPreferencesRepository userPreferencesRepository;

    public NotificationService(NotificationValidationService notificationValidationService,
                               NotificationPriorityService notificationPriorityService,
                               NotificationTemplateService notificationTemplateService,
                               UserPreferencesRepository userPreferencesRepository) {
        this.notificationValidationService = notificationValidationService;
        this.notificationPriorityService = notificationPriorityService;
        this.notificationTemplateService = notificationTemplateService;
        this.userPreferencesRepository = userPreferencesRepository;
    }

    @KafkaListener(topics = "notification-topic", groupId = "notification-service")
    public void processNotification(NotificationDTO notificationDTO) {
        notificationValidationService.validateNotification(notificationDTO);

        List<ChannelsType> activeChannels = resolveNotificationChannels(notificationDTO.receiverId(), notificationDTO.type());

        List<Notification> notifications = activeChannels.stream().map(
                channel -> {
                    String template = notificationTemplateService.getTemplate(notificationDTO.type(), channel);
                    return new Notification(
                            template,
                            channel
                    );
                }
        ).toList();


    }

    public List<ChannelsType> resolveNotificationChannels(UUID receiverID, NotificationType notificationType) {
        UserPreferenceEntity preferences = userPreferencesRepository
                .findByUserIdAndNotificationType(receiverID, notificationType.name())
                .orElseThrow(() -> new IllegalArgumentException("User preferences not found"));

        List<ChannelsType> channels = new ArrayList<>();
        if (preferences.getSms()) channels.add(ChannelsType.SMS);
        if (preferences.getEmail()) channels.add(ChannelsType.EMAIL);
        if (preferences.getPushNotification()) channels.add(ChannelsType.PUSH_NOTIFICATION);
        if (preferences.getInApp()) channels.add(ChannelsType.IN_APP);

        if (channels.isEmpty()) {
            log.warn("No notification channels enabled for user: {}", receiverID);
            throw new IllegalArgumentException("No channels found for the user");
        }

        return channels;
    }


}
