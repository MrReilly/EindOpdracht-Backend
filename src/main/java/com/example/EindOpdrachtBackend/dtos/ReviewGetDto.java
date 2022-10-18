package com.example.EindOpdrachtBackend.dtos;

import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.models.User;
import lombok.*;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewGetDto {

    private Long id;
    private Long eventId;
    private String authorName;
    private String reviewText;
    private Date reviewDate;
    private Integer starRating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewGetDto)) return false;
        ReviewGetDto that = (ReviewGetDto) o;
        return Objects.equals(id, that.id) && Objects.equals(eventId, that.eventId) && Objects.equals(authorName, that.authorName) && Objects.equals(reviewText, that.reviewText) && Objects.equals(reviewDate, that.reviewDate) && Objects.equals(starRating, that.starRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventId, authorName, reviewText, reviewDate, starRating);
    }
}
