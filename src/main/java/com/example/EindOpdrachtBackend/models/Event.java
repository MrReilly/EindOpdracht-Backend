package com.example.EindOpdrachtBackend.models;

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
@Table(name="events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="category")
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
    @ManyToOne
    @JoinColumn(name = "organizer")
    private User organizer;

    @OneToMany(
            mappedBy = "event",
            cascade = CascadeType.ALL)
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "myFavoriteEvents")
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<User> visitor = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Event event = (Event) o;
        return id != null && Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}

