package com.example.demo.controllers;

import com.example.demo.models.AmazingModel;
import com.example.demo.services.AmazingService;
import com.example.demo.services.IAmazingService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class AmazingController {
    private final IAmazingService _service;
    @Autowired
    public AmazingController(IAmazingService service) {
        this._service = service;
    }
    @GetMapping("/models")
    public List<AmazingModel> getModels() {
        return _service.getModels();
    }

    @GetMapping("/models/{id}")
    public ResponseEntity<AmazingModel> getModelById(@PathVariable String id) {
        return _service.getModelById(id)
                .map(model -> ResponseEntity.ok(model))
                .orElse((ResponseEntity.notFound().build()));
    }
}
