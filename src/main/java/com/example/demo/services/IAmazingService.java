package com.example.demo.services;

import com.example.demo.models.AmazingModel;

import java.util.List;
import java.util.Optional;

public interface IAmazingService {
    List<AmazingModel> getModels();
    Optional<AmazingModel> getModelById(String id);
}
