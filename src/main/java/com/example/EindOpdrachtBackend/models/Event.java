package com.example.EindOpdrachtBackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @Column(columnDefinition = "TEXT")
    private String textDescription;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    private Integer starRating;
    @OneToOne
    @JoinColumn(name = "image_id")
    private ImageData imageData;
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
    private List<User> visitor = new ArrayList<>();

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

