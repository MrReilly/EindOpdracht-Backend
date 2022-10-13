package com.example.EindOpdrachtBackend.dtos;

import com.example.EindOpdrachtBackend.models.CategoryOption;
import com.example.EindOpdrachtBackend.validation.ValueOfCategoryEnum;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import java.util.Date;

@Getter
@Setter
public class EventPostDto {

    @NotNull
    @ValueOfCategoryEnum(enumClass = CategoryOption.class)
    private String category;
    @NotNull
    private String name;
    @NotNull
    private String location;
    @NotNull
    private String address;
    @NotNull
    private Double latCoordinate;
    @NotNull
    private Double longCoordinate;
    @NotNull
    private String entryPrice;
    @NotNull
    private String textDescription;
    @NotNull
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    @FutureOrPresent
    private Date startDate;
    @NotNull
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    @Future
    private Date endDate;


}
