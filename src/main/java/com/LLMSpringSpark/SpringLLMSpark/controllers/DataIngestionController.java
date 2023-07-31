package com.LLMSpringSpark.SpringLLMSpark.controllers;

import com.LLMSpringSpark.SpringLLMSpark.kafka.KafkaProducerService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;



@RestController
public class DataIngestionController {

    private final KafkaProducerService kafkaProducerService;

    public DataIngestionController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/ingest")
    public ResponseEntity<Void> ingestData(@Valid @NotBlank @RequestBody String data) {
        try {
            kafkaProducerService.sendMessage("myTopic", data);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao enviar mensagem para Kafka", e);
        }
    }
}
