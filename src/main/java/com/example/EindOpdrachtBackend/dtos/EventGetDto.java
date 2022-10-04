package com.example.EindOpdrachtBackend.dtos;

import com.example.EindOpdrachtBackend.models.EventCategory;
import com.example.EindOpdrachtBackend.models.Organizer;
import com.example.EindOpdrachtBackend.models.Review;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class EventGetDto {

    private Long id;

    private Organizer organizer;

    private List<Review> reviews;

    @Enumerated(value = EnumType.STRING)
    private EventCategory category;

    private String organizationInfo;
    private String name;
    private String location;
    private String address;
    private Double latCoordinate;
    private Double longCoordinate;
    private String entryPrice;
    private String textDescription;
    private Date startDate;
    private Date endDate;

}
