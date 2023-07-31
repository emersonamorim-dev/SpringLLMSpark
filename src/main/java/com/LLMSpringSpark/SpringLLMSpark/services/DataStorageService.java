package com.LLMSpringSpark.SpringLLMSpark.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.LLMSpringSpark.SpringLLMSpark.models.DataDocument;
import com.LLMSpringSpark.SpringLLMSpark.repositories.DataRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataStorageService {

    private final DataRepository dataRepository;

    public DataStorageService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @KafkaListener(topics = "myTopic", groupId = "myGroup")
    public void storeMessage(String message) {
        DataDocument dataDocument = new DataDocument();
        dataDocument.setData(message);
        dataRepository.save(dataDocument);
    }

public void storeData(String data) {
    DataDocument dataDocument = new DataDocument();
    dataDocument.setData(data);
    dataRepository.save(dataDocument);
}

public String retrieveData() {
    List<DataDocument> dataDocuments = dataRepository.findAll();
    return dataDocuments.stream()
        .map(DataDocument::getData)
        .collect(Collectors.joining("\n"));
 }
}
