package com.example.EindOpdrachtBackend.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "image")
@Entity
@ToString
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    private String name;
    private String type;
    @OneToOne
    @JsonIgnore
    private Event event;

    @Lob
    @Column(name = "image", length = 1000)
    private byte[] image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ImageData imageData = (ImageData) o;
        return id != null && Objects.equals(id, imageData.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
