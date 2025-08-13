package com.mobilebikerepair.bikerepair.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobilebikerepair.bikerepair.model.RepairRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class RepairEventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendRepairCreated(RepairRequest rr) {
        Map<String, Object> payload = Map.of(
                "event", "REPAIR_CREATED",
                "id", rr.getId(),
                "customerId", rr.getCustomer() != null ? rr.getCustomer().getId() : null,
                "description", rr.getDescription()
        );
        try {
            String message = objectMapper.writeValueAsString(payload);
            kafkaTemplate.send(KafkaTopics.REPAIR_REQUEST_CREATED,
                    rr.getId() != null ? rr.getId().toString() : null,
                    message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize Kafka message", e);
        }
    }
}
