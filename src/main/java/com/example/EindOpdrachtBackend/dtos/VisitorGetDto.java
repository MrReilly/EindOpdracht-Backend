package com.example.EindOpdrachtBackend.dtos;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class VisitorGetDto {

        private Long id;
        private String username;
        private String defaultLocation;

    }

