package com.example.EindOpdrachtBackend.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor

@Entity
public class Visitor extends User{

    private String defaultLocation;
    @ManyToMany
    @JoinTable (
            name = "favorite_saved_events",
            joinColumns = @JoinColumn(name = "visitor_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    @ToString.Exclude
    private List<Event> myFavoriteEvents = new ArrayList<>();

    @OneToMany(mappedBy = "visitor")
    @ToString.Exclude
    private List<Review> myReviews = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Visitor visitor = (Visitor) o;
        return getId() != null && Objects.equals(getId(), visitor.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
