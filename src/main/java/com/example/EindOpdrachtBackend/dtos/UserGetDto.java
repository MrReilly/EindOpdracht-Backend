package com.example.EindOpdrachtBackend.dtos;

import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.models.Review;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserGetDto {

    private String username;
    private String organizationName;
    private String role;
    private Double defaultLatCoordinate;
    private Double defaultLongCoordinate;
    private List<Object> myFavoriteEvents;
    private List<Review> myReviews;
    private List<Object> myEvents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserGetDto)) return false;
        UserGetDto that = (UserGetDto) o;
        return Objects.equals(username, that.username) && Objects.equals(organizationName, that.organizationName) && Objects.equals(role, that.role) && Objects.equals(defaultLatCoordinate, that.defaultLatCoordinate) && Objects.equals(defaultLongCoordinate, that.defaultLongCoordinate) && Objects.equals(myFavoriteEvents, that.myFavoriteEvents) && Objects.equals(myReviews, that.myReviews) && Objects.equals(myEvents, that.myEvents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, organizationName, role, defaultLatCoordinate, defaultLongCoordinate, myFavoriteEvents, myReviews, myEvents);
    }
}

