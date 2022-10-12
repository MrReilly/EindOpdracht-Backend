package com.example.EindOpdrachtBackend.dtos;

import com.example.EindOpdrachtBackend.validation.ValueOfCategoryEnum;
import lombok.*;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
public class ReviewPostDto {

    @NotNull
    @Size(max = 150)
    private String reviewText;
    @NotNull
    @FutureOrPresent
    private Date reviewDate;
    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    private Integer starRating;

    }

