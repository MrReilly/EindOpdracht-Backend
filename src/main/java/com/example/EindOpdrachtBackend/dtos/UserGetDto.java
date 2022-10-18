package com.example.EindOpdrachtBackend.dtos;

import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.models.Review;
import lombok.*;

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
    private String defaultLocation;
    private String organizationName;
    private Object[] roles;
    private List<Object> myFavoriteEvents;
    private List<Review> myReviews;
    private List<Object> myEvents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserGetDto)) return false;
        UserGetDto that = (UserGetDto) o;
        return Objects.equals(username, that.username) && Objects.equals(defaultLocation, that.defaultLocation) && Objects.equals(organizationName, that.organizationName) && Arrays.equals(roles, that.roles) && Objects.equals(myFavoriteEvents, that.myFavoriteEvents) && Objects.equals(myReviews, that.myReviews) && Objects.equals(myEvents, that.myEvents);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(username, defaultLocation, organizationName, myFavoriteEvents, myReviews, myEvents);
        result = 31 * result + Arrays.hashCode(roles);
        return result;
    }


}

