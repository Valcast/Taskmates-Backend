package com.taskmates.backend.service;

import com.taskmates.backend.model.dto.NotificationDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    private final KafkaTemplate<String, NotificationDTO> kafkaTemplate;

    public NotificationProducer(KafkaTemplate<String, NotificationDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(NotificationDTO notificationDTO) {
        kafkaTemplate.send("send-notification", notificationDTO);
    }
}
