package com.mobilebikerepair.bikerepair.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RepairEventConsumer {

    @KafkaListener(topics = KafkaTopics.REPAIR_REQUEST_CREATED, groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(String message) {
        log.info("🔔 Kafka event received: {}", message);
    }
}
