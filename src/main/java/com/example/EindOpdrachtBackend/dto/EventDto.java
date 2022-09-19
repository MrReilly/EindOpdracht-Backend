package com.example.EindOpdrachtBackend.dto;

import com.example.EindOpdrachtBackend.model.EventCategory;
import com.example.EindOpdrachtBackend.model.Organizer;
import com.example.EindOpdrachtBackend.model.Review;
import com.example.EindOpdrachtBackend.model.Visitor;

import java.util.Date;
import java.util.List;

public class EventDto {

    private Long id;
    private Organizer organizer;
    private List<Review> review;
    private List<Visitor> visitorsFavorites;
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

    public EventDto() {
    }

    public EventDto(Long id, Organizer organizer, List<Review> review, List<Visitor> visitorsFavorites, EventCategory category, String organizationInfo, String name, String location, String address, Double latCoordinate, Double longCoordinate, String entryPrice, String textDescription, Date startDate, Date endDate) {
        this.id = id;
        this.organizer = organizer;
        this.review = review;
        this.visitorsFavorites = visitorsFavorites;
        this.category = category;
        this.organizationInfo = organizationInfo;
        this.name = name;
        this.location = location;
        this.address = address;
        this.latCoordinate = latCoordinate;
        this.longCoordinate = longCoordinate;
        this.entryPrice = entryPrice;
        this.textDescription = textDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

    public List<Visitor> getVisitorsFavorites() {
        return visitorsFavorites;
    }

    public void setVisitorsFavorites(List<Visitor> visitorsFavorites) {
        this.visitorsFavorites = visitorsFavorites;
    }

    public EventCategory getCategory() {
        return category;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
    }

    public String getOrganizationInfo() {
        return organizationInfo;
    }

    public void setOrganizationInfo(String organizationInfo) {
        this.organizationInfo = organizationInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatCoordinate() {
        return latCoordinate;
    }

    public void setLatCoordinate(Double latCoordinate) {
        this.latCoordinate = latCoordinate;
    }

    public Double getLongCoordinate() {
        return longCoordinate;
    }

    public void setLongCoordinate(Double longCoordinate) {
        this.longCoordinate = longCoordinate;
    }

    public String getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(String entryPrice) {
        this.entryPrice = entryPrice;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
