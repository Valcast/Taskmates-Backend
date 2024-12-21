package org.taskmates.notification_service.service;

import org.springframework.stereotype.Service;
import org.taskmates.notification_service.model.entities.NotificationTemplateEntity;
import org.taskmates.notification_service.model.enums.ChannelsType;
import org.taskmates.notification_service.model.enums.NotificationType;
import org.taskmates.notification_service.repository.NotificationTemplateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NotificationTemplateService {

    private final NotificationTemplateRepository notificationTemplateRepository;
    private final UserService userService;

    public NotificationTemplateService(NotificationTemplateRepository notificationTemplateRepository, UserService userService) {
        this.notificationTemplateRepository = notificationTemplateRepository;
        this.userService = userService;
    }


    public String getTemplate(NotificationType type, ChannelsType channel) {
        NotificationTemplateEntity template = notificationTemplateRepository.findByNotificationType(type.name()).orElseThrow(
                () -> new IllegalArgumentException("Notification template not found")
        );

        return switch (channel) {
            case IN_APP -> template.getInAppTemplate();
            case EMAIL -> null;
            case SMS -> template.getSmsTemplate();
            case PUSH_NOTIFICATION -> template.getPushTemplate();
        };
    }
    
    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("\\{([^}]+)}");

    public List<String> extractPlaceholders(String template) {
        Matcher matcher = PLACEHOLDER_PATTERN.matcher(template);
        List<String> placeholders = new ArrayList<>();
        while (matcher.find()) {
            placeholders.add(matcher.group(1).trim());
        }
        return placeholders;
    }

    public String fillTemplate(String template, Map<String, String> values) {
        StringBuilder sb = new StringBuilder();
        Matcher matcher = PLACEHOLDER_PATTERN.matcher(template);
        int lastIndex = 0;

        while (matcher.find()) {
            sb.append(template, lastIndex, matcher.start());
            String placeholderKey = matcher.group(1).trim();
            sb.append(values.getOrDefault(placeholderKey, ""));
            lastIndex = matcher.end();
        }

        sb.append(template, lastIndex, template.length());
        return sb.toString();
    }
}
