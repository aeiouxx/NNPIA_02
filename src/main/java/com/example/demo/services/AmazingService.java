package com.example.demo.services;

import com.example.demo.models.AmazingModel;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AmazingService implements IAmazingService {
    private final Map<String, AmazingModel> _models
            = new HashMap<>();

    public AmazingService() {
        _models.put("1",
                new AmazingModel("1", "Name1", "Description1"));
        _models.put("2",
                new AmazingModel("2", "Name2", "Description2"));
        _models.put("3",
                new AmazingModel("3", "Name3", "Description3"));
    }
    @Override
    public List<AmazingModel> getModels() {
        return new ArrayList<>(_models.values());
    }

    @Override
    public Optional<AmazingModel> getModelById(String id) {
        return Optional.ofNullable(_models.get(id));
    }
}
