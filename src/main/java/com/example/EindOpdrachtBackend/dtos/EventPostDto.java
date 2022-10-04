package com.example.EindOpdrachtBackend.dtos;

import com.example.EindOpdrachtBackend.models.EventCategory;
import com.example.EindOpdrachtBackend.models.Organizer;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class EventPostDto {

    private Organizer organizer;

    @Enumerated(value = EnumType.STRING)
    private EventCategory category;
    @NotNull
    private String organizationInfo;
    @NotNull
    private String name;
    @NotNull
    private String location;
    @NotNull
    private String address;
    @NotNull
    private String entryPrice;
    @NotNull
    private String textDescription;
    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;

}
