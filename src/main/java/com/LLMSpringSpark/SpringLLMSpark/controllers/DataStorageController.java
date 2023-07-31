package com.LLMSpringSpark.SpringLLMSpark.controllers;

import com.LLMSpringSpark.SpringLLMSpark.services.DataStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/storage")
public class DataStorageController {

    private final DataStorageService dataStorageService;

    public DataStorageController(DataStorageService dataStorageService) {
        this.dataStorageService = dataStorageService;
    }

    @PostMapping("/store")
    public ResponseEntity<Void> storeData(@RequestBody String data) {
        dataStorageService.storeData(data);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/retrieve")
    public ResponseEntity<String> retrieveData() {
        String data = (String) dataStorageService.retrieveData();
        return ResponseEntity.ok(data);
    }
}
