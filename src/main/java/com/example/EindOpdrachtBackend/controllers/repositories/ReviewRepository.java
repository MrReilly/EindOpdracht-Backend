package com.example.EindOpdrachtBackend.controllers.repositories;

import com.example.EindOpdrachtBackend.models.Review;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Qualifier("reviews")
@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
}
