package com.example.EindOpdrachtBackend.dtos;

import com.example.EindOpdrachtBackend.models.RoleOption;
import com.example.EindOpdrachtBackend.validation.ValueOfRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Objects;

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
    private String defaultLocationName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDetailsUpdateDto)) return false;
        UserDetailsUpdateDto that = (UserDetailsUpdateDto) o;
        return Objects.equals(role, that.role) && Objects.equals(organizationName, that.organizationName) && Objects.equals(password, that.password) && Objects.equals(defaultLatCoordinate, that.defaultLatCoordinate) && Objects.equals(defaultLongCoordinate, that.defaultLongCoordinate) && Objects.equals(defaultLocationName, that.defaultLocationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, organizationName, password, defaultLatCoordinate, defaultLongCoordinate, defaultLocationName);
    }
}

