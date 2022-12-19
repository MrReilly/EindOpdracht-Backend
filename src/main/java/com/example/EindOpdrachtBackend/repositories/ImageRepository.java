package com.example.EindOpdrachtBackend.repositories;

import com.example.EindOpdrachtBackend.models.ImageData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<ImageData, Long> {
}
