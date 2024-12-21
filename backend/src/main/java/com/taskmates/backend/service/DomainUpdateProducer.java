package com.taskmates.backend.service;

import com.taskmates.backend.model.dto.domain_update.DomainEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DomainUpdateProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String DOMAIN_EVENTS_TOPIC = "domain-events";

    public DomainUpdateProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishEvent(DomainEvent domainUpdateEvent) {
        kafkaTemplate.send(DOMAIN_EVENTS_TOPIC, domainUpdateEvent);
    }
}
