package com.example.EindOpdrachtBackend.dtos;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class OrganizerGetDto{

    private Long id;
    private String username;
    private String organizationName;

    }


