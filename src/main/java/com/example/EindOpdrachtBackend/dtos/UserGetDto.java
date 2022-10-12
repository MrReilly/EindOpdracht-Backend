package com.example.EindOpdrachtBackend.dtos;

import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.models.Review;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class UserGetDto {

    private String username;
    private String defaultLocation;
    private String organizationName;
    private Object[] roles;
    private List<Object> myFavoriteEvents;
    private List<Review> myReviews;
    private List<Object> myEvents;
}

