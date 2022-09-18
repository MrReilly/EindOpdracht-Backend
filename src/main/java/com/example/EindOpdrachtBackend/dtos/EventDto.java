package com.example.EindOpdrachtBackend.dtos;

import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.models.Organizer;
import com.example.EindOpdrachtBackend.models.Review;

import java.util.Date;
import java.util.List;

public class EventDto {

    private Long id;
    private Organizer organizer;
    private List<Review> review;
    private Event.eventCategory category;
    private String name;
    private String location;
    private Double latCoordinate;
    private Double longCoordinate;
    private String entryPrice;
    private String textDescription;
    private Date startDate;
    private Date endDate;

}
