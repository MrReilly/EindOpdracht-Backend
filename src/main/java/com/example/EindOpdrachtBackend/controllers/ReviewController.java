package com.example.EindOpdrachtBackend.controllers;

import com.example.EindOpdrachtBackend.dtos.ReviewPostDto;
import com.example.EindOpdrachtBackend.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

            return service.createReview(dto, eventId);
        }
    }

    @GetMapping("/reviews")

    public ResponseEntity<Object> getAllReviews() {
        return new ResponseEntity<>(service.getAllReviews(), HttpStatus.OK);
    }

    @GetMapping("/review/{eventId}")
    public ResponseEntity<Object> getReview(@PathVariable Long eventId) {

        return new ResponseEntity<>(service.getReview(eventId), HttpStatus.OK);
    }

    @DeleteMapping("/review/{id}")
    public ResponseEntity<Object> deleteReview(@PathVariable Long id) {

        return new ResponseEntity<>("Review with ID number" + service.deleteReview(id) + " was deleted successfully", HttpStatus.OK);
    }

}

