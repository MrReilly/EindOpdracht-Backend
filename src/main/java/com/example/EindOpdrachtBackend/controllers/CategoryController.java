package com.example.EindOpdrachtBackend.controllers;

import com.example.EindOpdrachtBackend.models.CategoryOption;
import com.example.EindOpdrachtBackend.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CategoryController {

    private final CategoryService service;

    CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Object> getCategory(@PathVariable CategoryOption category) {

        return new ResponseEntity<>(service.getCategory(category), HttpStatus.FOUND);
    }

}
