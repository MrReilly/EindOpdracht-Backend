package com.example.EindOpdrachtBackend.dto;

import com.example.EindOpdrachtBackend.model.Event;
import com.example.EindOpdrachtBackend.model.Visitor;

public class ReviewDto {

    private Long id;
    private Event event;
    private Visitor author;
    private String reviewText;
    private Integer starRating;

    public ReviewDto() {
    }

    public ReviewDto(Long id, Event event, Visitor author, String reviewText, Integer starRating) {
        this.id = id;
        this.event = event;
        this.author = author;
        this.reviewText = reviewText;
        this.starRating = starRating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Visitor getAuthor() {
        return author;
    }

    public void setAuthor(Visitor author) {
        this.author = author;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Integer getStarRating() {
        return starRating;
    }

    public void setStarRating(Integer starRating) {
        this.starRating = starRating;
    }
}
