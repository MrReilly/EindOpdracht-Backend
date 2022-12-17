package com.example.EindOpdrachtBackend.dtos;

import com.example.EindOpdrachtBackend.models.CategoryOption;
import com.example.EindOpdrachtBackend.validation.ValueOfCategoryEnum;
import lombok.*;

import javax.validation.constraints.*;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventPostDto {

    @NotNull
    @ValueOfCategoryEnum(enumClass = CategoryOption.class)
    private String category;
    @NotNull
    @Size(max = 30)
    private String name;
    @NotNull
    private String location;
    @NotNull
    @Size(max = 30)
    private String address;
    @NotNull
    @Min(value = -90)
    @Max(value = 90)
    private Double latCoordinate;
    @NotNull
    @Min(value = -180)
    @Max(value = 180)
    private Double longCoordinate;
    @NotNull
    @Size(max = 15)
    private String entryPrice;
    @NotNull
    @Size(max = 300)
    private String textDescription;
    @NotNull
    @FutureOrPresent
    private Date startDate;
    @NotNull
    @Future
    private Date endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventPostDto)) return false;
        EventPostDto that = (EventPostDto) o;
        return Objects.equals(category, that.category) && Objects.equals(name, that.name) && Objects.equals(location, that.location) && Objects.equals(address, that.address) && Objects.equals(latCoordinate, that.latCoordinate) && Objects.equals(longCoordinate, that.longCoordinate) && Objects.equals(entryPrice, that.entryPrice) && Objects.equals(textDescription, that.textDescription) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, name, location, address, latCoordinate, longCoordinate, entryPrice, textDescription, startDate, endDate);
    }

}

