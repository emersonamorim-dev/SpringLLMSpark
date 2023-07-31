package com.LLMSpringSpark.SpringLLMSpark.kafka;

import java.io.ByteArrayInputStream;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import scala.collection.Seq;

@Service
public class KafkaConsumerService {

    @Autowired
    private SparkSession sparkSession;

    @KafkaListener(topics = "myTopic", groupId = "myGroup")
    public void listen(String message) {
        Dataset<Row> df = sparkSession.read().json((Seq<String>) new ByteArrayInputStream(message.getBytes()));
        df.show();
    }

    public void sendMessage(String string, String data) {
    }
}
