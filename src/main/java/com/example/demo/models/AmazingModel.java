package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class AmazingModel {
    private String id;
    private String name;
    private String description;
}
