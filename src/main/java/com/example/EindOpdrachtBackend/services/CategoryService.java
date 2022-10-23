package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.exception.RecordNotFoundException;
import com.example.EindOpdrachtBackend.mappers.CategoryMapper;
import com.example.EindOpdrachtBackend.models.Category;
import com.example.EindOpdrachtBackend.models.CategoryOption;
import com.example.EindOpdrachtBackend.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    CategoryMapper mapper;
    CategoryRepository repos;

    CategoryService(CategoryMapper mapper, CategoryRepository repos) {
        this.mapper = mapper;
        this.repos = repos;
    }

    public Object getCategory(CategoryOption category) {

        Optional<Category> oc = repos.findById(category);

        if (oc.isPresent()) {


            return mapper.CategoryGetDto(oc.get());
        }

        throw new RecordNotFoundException("Category not found");

    }

}
