package com.example.EindOpdrachtBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name= "categories")
public class Category {

   @Id
   @Enumerated(value = EnumType.STRING)
   private CategoryOption category;
   @OneToMany(mappedBy = "category")
   @JsonIgnore
   @ToString.Exclude
   private List<Event> events = new ArrayList<>();

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
      Category category1 = (Category) o;
      return category != null && Objects.equals(category, category1.category);
   }

   @Override
   public int hashCode() {
      return getClass().hashCode();
   }
}



