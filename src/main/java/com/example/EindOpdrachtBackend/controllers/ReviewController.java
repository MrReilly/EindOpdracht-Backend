package com.example.EindOpdrachtBackend.controllers;

import com.example.EindOpdrachtBackend.dtos.ReviewPostDto;
import com.example.EindOpdrachtBackend.models.Review;
import com.example.EindOpdrachtBackend.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.EindOpdrachtBackend.validation.StringBuilderValidation.stringBuilder;


@RestController
public class ReviewController {

    private final ReviewService service;

    ReviewController(ReviewService service) {
        this.service = service;

    }

    @PostMapping("/review/{eventId}")
    public ResponseEntity<Object> createReview(@Valid @PathVariable Long eventId, @RequestBody ReviewPostDto dto, BindingResult br) {

        if (br.hasErrors()) {

            return stringBuilder.validation(br);

        } else {

            Review createdReview = service.createReview(dto, eventId);

            return new ResponseEntity<>("Review with ID number " + createdReview.getId() + " was created successfully", HttpStatus.CREATED);
        }
    }

    @GetMapping("/reviews")

    public ResponseEntity<Object> getAllReviews() {
        return new ResponseEntity<>(service.getAllReviews(), HttpStatus.OK);
    }

    @GetMapping("/review/{id}")
    public ResponseEntity<Object> getReview(@PathVariable Long id) {

            return new ResponseEntity<>( service.getReview(id), HttpStatus.FOUND);
        }

    @DeleteMapping("/review/{id}")
    public ResponseEntity<Object> deleteReview(@PathVariable Long id) {

        return new ResponseEntity<>("Review with ID number" + service.deleteReview(id) + " was deleted successfully", HttpStatus.OK);
    }

}

