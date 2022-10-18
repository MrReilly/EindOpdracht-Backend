package com.example.EindOpdrachtBackend.dtos;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserMyEventGetDto {

    private List<Object> myEvents;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserMyEventGetDto)) return false;
        UserMyEventGetDto that = (UserMyEventGetDto) o;
        return Objects.equals(myEvents, that.myEvents);
    }
    @Override
    public int hashCode() {
        return Objects.hash(myEvents);
    }
}
