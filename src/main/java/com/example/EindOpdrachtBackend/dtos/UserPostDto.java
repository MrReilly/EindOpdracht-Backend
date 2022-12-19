package com.example.EindOpdrachtBackend.dtos;

import com.example.EindOpdrachtBackend.models.RoleOption;
import com.example.EindOpdrachtBackend.validation.ValueOfRoleEnum;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @Size(max = 20)
    private String username;
    @NotNull
    @Size(max = 20)
    private String password;
    @Size(max = 20)
    private String organizationName;
    @Min(value = -90)
    @Max(value = 90)
    private Double defaultLatCoordinate;
    @Min(value = -180)
    @Max(value = 180)
    private Double defaultLongCoordinate;
    @NotNull
    private String defaultLocationName;


}


