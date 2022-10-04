package com.example.EindOpdrachtBackend.dtos;

import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.models.Visitor;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class ReviewPostDto {

        @NotNull
        private Event event;
        @NotNull
        private Visitor visitor;
        @NotNull
        private String reviewText;
    }

