package com.LLMSpringSpark.SpringLLMSpark.services;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Service;

@Service
public class MongoDBService {

    private final MongoCollection<Document> collection;

    public MongoDBService(MongoClient mongoClient) {
        MongoDatabase database = mongoClient.getDatabase("SpringLLMSparkDB");
        this.collection = database.getCollection("LLMSpark");
    }

    public void saveData(List<String> dataList) {
        for (String data : dataList) {
            Document document = Document.parse(data);
            collection.insertOne(document);
        }
    }
    public void saveData(String data) {
        Document document = Document.parse(data);
        collection.insertOne(document);
    }
}
