package com.example.EindOpdrachtBackend.dtos;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserPostDto {
        @NotNull
        private String[] roles;
        @NotNull
        private String username;
        @NotNull
        private String password;
        @NotNull
        private String defaultLocation;

        private String organizationName;
    }


