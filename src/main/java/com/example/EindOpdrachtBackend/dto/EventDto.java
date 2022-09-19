package com.example.EindOpdrachtBackend.dto;

import com.example.EindOpdrachtBackend.model.EventCategory;
import com.example.EindOpdrachtBackend.model.Organizer;
import com.example.EindOpdrachtBackend.model.Review;

import java.util.Date;
import java.util.List;

public class EventDto {

    private Long id;
    private Organizer organizer;
    private List<Review> review;
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
