package com.LLMSpringSpark.SpringLLMSpark.services;

import com.LLMSpringSpark.SpringLLMSpark.kafka.KafkaProducerService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DataProcessingService {

    private final SparkSession sparkSession;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final MongoDBService mongoDBService;
    private final AtomicInteger processedDataCount = new AtomicInteger();

    public DataProcessingService(SparkSession sparkSession, KafkaTemplate<String, String> kafkaTemplate, MongoDBService mongoDBService) {
        this.sparkSession = sparkSession;
        this.kafkaTemplate = kafkaTemplate;
        this.mongoDBService = mongoDBService;
    }

    @KafkaListener(topics = "inputTopic")
    public void startProcessing(ConsumerRecord<String, String> record) {
        try {
            // Converter o registro em um DataFrame
            Dataset<Row> df = sparkSession.createDataFrame(
                Collections.singletonList(record),
                ConsumerRecord.class
            );

            Dataset<Row> processedDf = df; 
            Row processedRow = processedDf.first();
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("outputTopic", record.key(), record.value());

            kafkaTemplate.send(producerRecord);
            mongoDBService.saveData(record.value()); 
            processedDataCount.incrementAndGet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getProcessingStatus() {
        return "Contagem de dados processados: " + processedDataCount.get();
    }
}


