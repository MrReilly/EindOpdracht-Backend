package com.example.EindOpdrachtBackend.dtos;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class VisitorPostDto {

        @NotNull
        private String username;
        @NotNull
        private String password;
        @NotNull
        private String defaultLocation;
    }


