package com.example.EindOpdrachtBackend.dtos;

import com.example.EindOpdrachtBackend.validation.ValueOfCategoryEnum;
import lombok.*;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewPostDto {

    @NotNull
    @Size(max = 255)
    private String reviewText;
    @NotNull
    @FutureOrPresent
    private Date reviewDate;
    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    private Integer starRating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewPostDto)) return false;
        ReviewPostDto that = (ReviewPostDto) o;
        return Objects.equals(reviewText, that.reviewText) && Objects.equals(reviewDate, that.reviewDate) && Objects.equals(starRating, that.starRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewText, reviewDate, starRating);
    }
}

