package com.example.EindOpdrachtBackend.dtos;

import com.example.EindOpdrachtBackend.models.RoleOption;
import com.example.EindOpdrachtBackend.validation.ValueOfRoleEnum;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPostDto {

        @NotNull
        @ValueOfRoleEnum(enumClass = RoleOption.class)
        private String role;
        @NotNull
        @Size(max = 30)
        private String username;
        @NotNull
        @Size(max = 30)
        private String password;
        @Size(max = 30)
        private String organizationName;
    }


