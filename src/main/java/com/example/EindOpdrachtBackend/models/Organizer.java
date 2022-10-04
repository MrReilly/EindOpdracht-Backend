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
@Table(name="organizers")
public class Organizer extends User{

   private String organizationName;

   @OneToMany(mappedBy = "organizer")
   @ToString.Exclude
   private List<Event> myEvents = new ArrayList<>();

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
      Organizer organizer = (Organizer) o;
      return getId() != null && Objects.equals(getId(), organizer.getId());
   }

   @Override
   public int hashCode() {
      return getClass().hashCode();
   }
}
