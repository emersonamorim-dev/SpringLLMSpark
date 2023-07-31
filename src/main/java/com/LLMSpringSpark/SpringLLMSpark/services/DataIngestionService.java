package com.LLMSpringSpark.SpringLLMSpark.services;

import com.LLMSpringSpark.SpringLLMSpark.kafka.KafkaProducerService;
import org.springframework.stereotype.Service;


@Service
public class DataIngestionService {

    private final KafkaProducerService kafkaProducerService;
    private final MongoDBService mongoDBService;

    public DataIngestionService(KafkaProducerService kafkaProducerService, MongoDBService mongoDBService) {
        this.kafkaProducerService = kafkaProducerService;
        this.mongoDBService = mongoDBService;
    }

    public void ingestData(String data) {
        kafkaProducerService.sendMessage("myTopic", data);
        mongoDBService.saveData(data);
    }
}
