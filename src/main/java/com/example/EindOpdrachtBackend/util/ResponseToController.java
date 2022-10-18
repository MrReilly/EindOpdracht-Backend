package com.example.EindOpdrachtBackend.util;

import com.example.EindOpdrachtBackend.models.Event;
import lombok.*;

import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ResponseToController {

    public String string1;
    public Event event;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponseToController response)) return false;
        return Objects.equals(string1, response.string1) && Objects.equals(event, response.event) && Objects.equals(string2, response.string2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string1, event, string2);
    }

    public String string2;

}
