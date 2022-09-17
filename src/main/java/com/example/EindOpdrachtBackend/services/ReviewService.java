package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository repos;

    public ReviewService(ReviewRepository repos) {this.repos = repos;}
}
