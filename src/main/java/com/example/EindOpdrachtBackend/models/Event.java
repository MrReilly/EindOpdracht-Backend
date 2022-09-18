package com.example.EindOpdrachtBackend.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Event {


    public enum eventCategory{festival, art, concert, kids, carnival, nature, market, theater, conference, circus, sport, other};

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Organizer organizer;
    @OneToMany
    private List<Review> review;
    private eventCategory category;
    private String name;
    private String location;
    private Double latCoordinate;
    private Double longCoordinate;
    private String entryPrice;
    private String textDescription;
    private Date startDate;
    private Date endDate;

    public Event() {

    }

    public Event(eventCategory category, String name, String location, String entryPrice, String textDescription, Date startDate, Date endDate) {
        this.category = category;
        this.name = name;
        this.location = location;
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

    public eventCategory getCategory() {
        return category;
    }

    public void setCategory(eventCategory category) {
        this.category = category;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) && Objects.equals(organizer, event.organizer) && Objects.equals(review, event.review) && category == event.category && Objects.equals(name, event.name) && Objects.equals(location, event.location) && Objects.equals(latCoordinate, event.latCoordinate) && Objects.equals(longCoordinate, event.longCoordinate) && Objects.equals(entryPrice, event.entryPrice) && Objects.equals(textDescription, event.textDescription) && Objects.equals(startDate, event.startDate) && Objects.equals(endDate, event.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, organizer, review, category, name, location, latCoordinate, longCoordinate, entryPrice, textDescription, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", organizer=" + organizer +
                ", review=" + review +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", latCoordinate=" + latCoordinate +
                ", longCoordinate=" + longCoordinate +
                ", entryPrice='" + entryPrice + '\'' +
                ", textDescription='" + textDescription + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

}
