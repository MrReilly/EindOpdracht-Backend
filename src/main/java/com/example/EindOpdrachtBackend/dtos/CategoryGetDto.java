package com.example.EindOpdrachtBackend.dtos;

import lombok.*;

import java.util.List;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryGetDto {

    private List<EventGetDto> events;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryGetDto)) return false;
        CategoryGetDto that = (CategoryGetDto) o;
        return Objects.equals(events, that.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(events);
    }
}
