package com.example.EindOpdrachtBackend.dtos;


import com.example.EindOpdrachtBackend.models.Category;
import com.example.EindOpdrachtBackend.models.Review;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class EventGetDto {

    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    private String organizationName;
    private String name;
    private String location;
    private String address;
    private Double latCoordinate;
    private Double longCoordinate;
    private String entryPrice;
    private String textDescription;
    private Date startDate;
    private Date endDate;
    private Integer starRating;

    private List<Review> reviews;
}
