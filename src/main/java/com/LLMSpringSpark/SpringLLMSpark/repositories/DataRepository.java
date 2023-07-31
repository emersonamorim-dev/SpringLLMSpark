package com.LLMSpringSpark.SpringLLMSpark.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.LLMSpringSpark.SpringLLMSpark.models.DataDocument;

public interface DataRepository extends MongoRepository<DataDocument, String> {
}

