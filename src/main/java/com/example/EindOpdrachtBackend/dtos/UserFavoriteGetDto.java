package com.example.EindOpdrachtBackend.dtos;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserFavoriteGetDto {
    private List<Object> myFavoriteEvents;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserFavoriteGetDto)) return false;
        UserFavoriteGetDto that = (UserFavoriteGetDto) o;
        return Objects.equals(myFavoriteEvents, that.myFavoriteEvents);
    }
    @Override
    public int hashCode() {
        return Objects.hash(myFavoriteEvents);
    }
}
