package com.example.EindOpdrachtBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
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
@Table(name="users")
    public class User {

    @Id
    @Column(name = "user_id")
    private String username;
    private String password;
    private String defaultLocation;
    private String organizationName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_name"))
    private Collection<Role> roles;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "favorite_saved_events",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"event_id"})})
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
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return username != null && Objects.equals(username, user.username);
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}






