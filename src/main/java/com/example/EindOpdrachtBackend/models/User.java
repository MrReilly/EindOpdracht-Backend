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
    private String organizationName;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="role")
    @ToString.Exclude
    private Role role;

    @ManyToMany(mappedBy = "visitor", cascade = CascadeType.REMOVE)
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
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return username != null && Objects.equals(username, user.username);
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}






