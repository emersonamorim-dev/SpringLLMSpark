package com.LLMSpringSpark.SpringLLMSpark.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.LLMSpringSpark.SpringLLMSpark.services.DataProcessingService;

@RestController
@RequestMapping("/processing")
public class DataProcessingController {

    private final DataProcessingService dataProcessingService;

    public DataProcessingController(DataProcessingService dataProcessingService) {
        this.dataProcessingService = dataProcessingService;
    }

    @PostMapping("/start")
    public ResponseEntity<Void> startProcessing() {
        dataProcessingService.startProcessing(null);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/status")
    public ResponseEntity<String> getProcessingStatus() {
        String status = dataProcessingService.getProcessingStatus();
        return ResponseEntity.ok(status);
    }
}

