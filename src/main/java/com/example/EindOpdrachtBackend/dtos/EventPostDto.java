package com.example.EindOpdrachtBackend.dtos;

import com.example.EindOpdrachtBackend.models.CategoryOption;
import com.example.EindOpdrachtBackend.validation.ValueOfCategoryEnum;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(max = 50)
    private String name;
    @NotNull
    @Size(max = 50)
    private String location;
    @NotNull
    @Size(max = 50)
    private String address;
    @NotNull
    @Size(max = 10)
    private Double latCoordinate;
    @NotNull
    @Size(max = 10)
    private Double longCoordinate;
    @NotNull
    @Size(max = 20)
    private String entryPrice;
    @NotNull
    @Size(max = 150)
    private String textDescription;
    @NotNull
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    @FutureOrPresent
    private Date startDate;
    @NotNull
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
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
