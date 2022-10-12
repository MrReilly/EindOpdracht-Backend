package com.example.EindOpdrachtBackend.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDetailsUpdateDto {

    private String[] roles;

    private String username;

    private String defaultLocation;

    private String organizationName;

}

