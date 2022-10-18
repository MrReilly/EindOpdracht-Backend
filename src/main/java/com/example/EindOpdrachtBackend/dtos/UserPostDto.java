package com.example.EindOpdrachtBackend.dtos;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPostDto {

        @NotNull
        private String[] roles;
        @NotNull
        @Size(max = 30)
        private String username;
        @NotNull
        @Size(max = 30)
        private String password;
        @NotNull
        @Size(max = 30)
        private String defaultLocation;
        @Size(max = 30)
        private String organizationName;
    }


