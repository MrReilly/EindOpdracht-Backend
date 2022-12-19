package com.example.EindOpdrachtBackend.dtos;

import com.example.EindOpdrachtBackend.models.Category;
import com.example.EindOpdrachtBackend.models.ImageData;
import com.example.EindOpdrachtBackend.models.Review;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventGetDto {

    private Long id;
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
    private List<Review> reviews;
    private ImageData imageData;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventGetDto that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(category, that.category) && Objects.equals(organizationName, that.organizationName) && Objects.equals(name, that.name) && Objects.equals(location, that.location) && Objects.equals(address, that.address) && Objects.equals(latCoordinate, that.latCoordinate) && Objects.equals(longCoordinate, that.longCoordinate) && Objects.equals(entryPrice, that.entryPrice) && Objects.equals(textDescription, that.textDescription) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(starRating, that.starRating) && Objects.equals(reviews, that.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, organizationName, name, location, address, latCoordinate, longCoordinate, entryPrice, textDescription, startDate, endDate, starRating, reviews);
    }
}
