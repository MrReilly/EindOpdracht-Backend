package com.example.EindOpdrachtBackend.dtos;

import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.models.Visitor;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class ReviewGetDto {

    private Long id;
    private Event event;
    private Visitor visitor;
    private String reviewText;

}
