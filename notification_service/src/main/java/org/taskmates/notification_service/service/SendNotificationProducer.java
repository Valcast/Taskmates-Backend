package org.taskmates.notification_service.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class SendNotificationProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String SEND_NOTIFICATION_EMAIL = "send-notification-email";
    private static final String SEND_NOTIFICATION_SMS = "send-notification-sms";


    public SendNotificationProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendEmailNotification(String email, String message, Instant scheduled) {
    }

    public void sendSmsNotification(String phoneNumber, String message, Instant scheduled) {
    }

    public void sendPushNotification(UUID uuid, String message, Instant scheduled) {
    }

    public void sendInAppNotification(UUID uuid, String message, Instant scheduled) {
    }
}
