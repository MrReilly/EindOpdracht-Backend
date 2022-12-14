package com.example.EindOpdrachtBackend.dtos;

import com.example.EindOpdrachtBackend.models.RoleOption;
import com.example.EindOpdrachtBackend.validation.ValueOfRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsUpdateDto {

    @ValueOfRoleEnum(enumClass = RoleOption.class)
    private String role;
    @Size(max = 30)
    private String organizationName;
    @Size(max = 30)
    private String password;
    @Min(value = -90)
    @Max(value = 90)
    private Double defaultLatCoordinate;
    @Min(value = -180)
    @Max(value = 180)
    private Double defaultLongCoordinate;

}

