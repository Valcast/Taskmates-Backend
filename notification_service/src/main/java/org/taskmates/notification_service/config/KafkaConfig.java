package org.taskmates.notification_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class KafkaConfig {

    @Bean
    public List<NewTopic> notificationTopics() {
        return List.of(
                new NewTopic("send-email-notification", 1, (short) 1),
                new NewTopic("send-push-notification", 1, (short) 1),
                new NewTopic("send-sms-notification", 1, (short) 1)
        );
    }

}
