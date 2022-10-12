package com.example.EindOpdrachtBackend.dtos;

import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.models.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
public class ReviewGetDto {

    private Long id;
    private Long eventId;
    private String authorName;
    private String reviewText;
    private Date reviewDate;
    private Integer starRating;

}
