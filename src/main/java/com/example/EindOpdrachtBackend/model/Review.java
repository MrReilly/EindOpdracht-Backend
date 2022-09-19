package com.example.EindOpdrachtBackend.model;

import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;
    @ManyToOne
    @JoinColumn(name="visitor_id")
    private Visitor author;
    private String reviewText;
    private Integer starRating;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
