package com.LLMSpringSpark.SpringLLMSpark.services;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.springframework.stereotype.Service;

@Service
public class SparkService {

    private final SparkSession sparkSession;
    private final MongoDBService mongoDBService;

    public SparkService(MongoDBService mongoDBService) {
        this.sparkSession = SparkSession.builder()
            .appName("LLM-Spark")
            .master("local[*]")
            .getOrCreate();
        this.mongoDBService = mongoDBService;
    }

    public Dataset<Row> readData(String path) {
        return sparkSession.read().json(path);
    }

    public Dataset<Row> processData(Dataset<Row> data) {
        Dataset<Row> words = data.withColumn("words", functions.split(functions.col("text"), " "));
        
        Dataset<Row> processedData = words.withColumn("wordCount", functions.size(functions.col("words")));
        
        // Converte o Dataset<Row> para JSON e salva no MongoDB
        mongoDBService.saveData(processedData.toJSON().collectAsList());
        
        return processedData;
    }
    
}

