package org.taskmates.notification_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.taskmates.notification_service.model.entities.UserEntity;
import org.taskmates.notification_service.model.entities.UserPreferenceEntity;
import org.taskmates.notification_service.model.enums.ChannelType;
import org.taskmates.notification_service.model.enums.NotificationType;
import org.taskmates.notification_service.model.events.SendNotificationEvent;
import org.taskmates.notification_service.repository.UserPreferencesRepository;
import org.taskmates.notification_service.repository.UserRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@EnableKafka
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationValidationService notificationValidationService;
    private final NotificationTemplateService notificationTemplateService;
    private final NotificationSchedulerService notificationSchedulerService;
    private final SendNotificationProducer sendNotificationProducer;

    private final UserPreferencesRepository userPreferencesRepository;
    private final UserRepository userRepository;

    public NotificationService(NotificationValidationService notificationValidationService,
                               NotificationTemplateService notificationTemplateService,
                               NotificationSchedulerService notificationSchedulerService, SendNotificationProducer sendNotificationProducer,
                               UserPreferencesRepository userPreferencesRepository, UserRepository userRepository) {
        this.notificationValidationService = notificationValidationService;
        this.userRepository = userRepository;
        this.notificationTemplateService = notificationTemplateService;
        this.notificationSchedulerService = notificationSchedulerService;
        this.sendNotificationProducer = sendNotificationProducer;
        this.userPreferencesRepository = userPreferencesRepository;
    }

    @KafkaListener(topics = "send-notification", groupId = "notification-service")
    public void processNotification(SendNotificationEvent sendNotificationEvent) {
        notificationValidationService.validateNotification(sendNotificationEvent);

        UserEntity receiver = userRepository.findById(sendNotificationEvent.receiverId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with the given userId: " + sendNotificationEvent.receiverId()));

        List<ChannelType> activeChannels = resolveNotificationChannels(sendNotificationEvent.receiverId(), sendNotificationEvent.type());

        activeChannels.forEach(
                channel -> {
                    Instant scheduled = notificationSchedulerService.resolveScheduledTime(sendNotificationEvent.type(),
                            sendNotificationEvent.projectId().orElse(sendNotificationEvent.taskId().orElse(null)));

                    String message = notificationTemplateService.resolveMessage(channel, sendNotificationEvent.type(),
                            sendNotificationEvent.senderId().orElse(null),
                            sendNotificationEvent.projectId().orElse(sendNotificationEvent.taskId().orElse(null)));

                    switch (channel) {
                        case EMAIL ->
                                sendNotificationProducer.sendEmailNotification(receiver.getEmail(), message, scheduled);
                        case SMS ->
                                sendNotificationProducer.sendSmsNotification(receiver.getPhoneNumber(), message, scheduled);
                        case PUSH ->
                                sendNotificationProducer.sendPushNotification(sendNotificationEvent.receiverId(), message, scheduled);
                        case IN_APP ->
                                sendNotificationProducer.sendInAppNotification(sendNotificationEvent.receiverId(), message, scheduled);
                    }
                }

        );
    }

    public List<ChannelType> resolveNotificationChannels(UUID receiverID, NotificationType notificationType) {
        UserPreferenceEntity preferences = userPreferencesRepository
                .findByUserIdAndNotificationType(receiverID, notificationType.name())
                .orElseThrow(() -> new IllegalArgumentException("User preferences not found"));

        List<ChannelType> channels = new ArrayList<>();
        if (preferences.getSms()) channels.add(ChannelType.SMS);
        if (preferences.getEmail()) channels.add(ChannelType.EMAIL);
        if (preferences.getPushNotification()) channels.add(ChannelType.PUSH);
        if (preferences.getInApp()) channels.add(ChannelType.IN_APP);

        List<ChannelType> allowedChannels = getAllowedChannelsForNotificationType(notificationType);


        if (channels.isEmpty()) {
            log.warn("No notification channels enabled for user: {}", receiverID);
            throw new IllegalArgumentException("No channels found for the user");
        }

        return channels.stream().filter(allowedChannels::contains).toList();
    }

    private List<ChannelType> getAllowedChannelsForNotificationType(NotificationType notificationType) {
        return switch (notificationType) {
            case PROJECT_INVITATION_ACCEPTED, PROJECT_INVITATION_DECLINED -> List.of(ChannelType.IN_APP);
            case PROJECT_DELETED, DEADLINE_PASSED -> List.of(ChannelType.EMAIL, ChannelType.SMS, ChannelType.IN_APP);
            case TASK_ASSIGNED, TASK_COMPLETED -> List.of(ChannelType.PUSH, ChannelType.IN_APP);
            default -> List.of(ChannelType.EMAIL, ChannelType.PUSH, ChannelType.IN_APP);
        };
    }


}
