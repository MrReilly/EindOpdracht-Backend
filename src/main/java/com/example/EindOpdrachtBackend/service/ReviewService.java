package com.example.EindOpdrachtBackend.service;

import com.example.EindOpdrachtBackend.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository repos;

    public ReviewService(ReviewRepository repos) {this.repos = repos;}
}
