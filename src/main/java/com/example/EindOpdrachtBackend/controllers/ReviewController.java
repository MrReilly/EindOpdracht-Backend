package com.example.EindOpdrachtBackend.controllers;

import com.example.EindOpdrachtBackend.dtos.ReviewPostDto;
import com.example.EindOpdrachtBackend.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PostMapping("/review")
    public ResponseEntity<Object> createReview(@RequestBody ReviewPostDto dto, BindingResult br) {

        if (br.hasErrors()) {

            return stringBuilder.validation(br);

        } else {

            Long createdReviewId = service.createReview(dto);

            return new ResponseEntity<>("ID number " + createdReviewId + " was created successfully", HttpStatus.CREATED);
        }
    }

    @GetMapping("/reviews")
    public ResponseEntity<Object> getAllReviews() {
        return new ResponseEntity(service.getAllReviews(), HttpStatus.OK);
    }


    @GetMapping("/review/{id}")
    public ResponseEntity<Object> getReview(@PathVariable Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) {
            return new ResponseEntity<>("Hello " + ((UserDetails) auth.getPrincipal()).getUsername() + "We found review: " + service.getReview(id).getId(), HttpStatus.FOUND);
        }

        return new ResponseEntity<>(" Hello Stranger, we found review with ID: " + service.getReview(id), HttpStatus.OK);

    }

    @PutMapping("/review/{id}")
    public ResponseEntity<Object> updateReview(@PathVariable Long id, @Valid @RequestBody ReviewPostDto dto, BindingResult br) {

        if (br.hasErrors()) {

            return stringBuilder.validation(br);

        } else {

            return new ResponseEntity<>("Review with ID number " + service.updateReview(dto, id).getId() + " was updated successfully", HttpStatus.CREATED);
        }
    }


    @DeleteMapping("/review/{id}")
    public ResponseEntity<Object> deleteReview(@PathVariable Long id) {

        return new ResponseEntity<>("Review with ID number" + service.deleteReview(id) + " was deleted successfully", HttpStatus.OK);
    }

}

