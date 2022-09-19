package com.example.EindOpdrachtBackend.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="events")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Event {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name="organizer_id")
    private Organizer organizer;
    @OneToMany(mappedBy = "event")
    private List<Review> review;
    @ManyToMany
    private List<Visitor> visitorsFavorites;
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

    public Event() {
    }

    public Event(EventCategory category, String organizationInfo, String name, String location, String address, String entryPrice, String textDescription, Date startDate, Date endDate) {
        this.category = category;
        this.organizationInfo = organizationInfo;
        this.name = name;
        this.location = location;
        this.address = address;
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
