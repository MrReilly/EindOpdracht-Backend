package com.example.EindOpdrachtBackend.repositories;

import com.example.EindOpdrachtBackend.models.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
