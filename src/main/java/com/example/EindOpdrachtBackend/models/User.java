package com.example.EindOpdrachtBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    private String username;
    private String password;
    private String organizationName;
    private Double defaultLatCoordinate;
    private Double defaultLongCoordinate;
    private String defaultLocationName;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "role")
    @ToString.Exclude
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "favorite_saved_events",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    @ToString.Exclude
    private List<Event> myFavoriteEvents = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    private List<Review> myReviews = new ArrayList<>();

    @OneToMany(mappedBy = "organizer")

    @ToString.Exclude
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Event> myEvents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(organizationName, user.organizationName) && Objects.equals(defaultLatCoordinate, user.defaultLatCoordinate) && Objects.equals(defaultLongCoordinate, user.defaultLongCoordinate) && Objects.equals(defaultLocationName, user.defaultLocationName) && Objects.equals(role, user.role) && Objects.equals(myFavoriteEvents, user.myFavoriteEvents) && Objects.equals(myReviews, user.myReviews) && Objects.equals(myEvents, user.myEvents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, organizationName, defaultLatCoordinate, defaultLongCoordinate, defaultLocationName, role, myFavoriteEvents, myReviews, myEvents);
    }
}






