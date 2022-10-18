package com.example.EindOpdrachtBackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsUpdateDto {

    private String[] roles;

    private String username;

    private String defaultLocation;

    private String organizationName;

}

