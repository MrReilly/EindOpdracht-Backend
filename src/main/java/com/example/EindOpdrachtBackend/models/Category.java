package com.example.EindOpdrachtBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name= "categories")
public class Category {

   @Id
   @Enumerated(value = EnumType.STRING)
   private CategoryOption category;
   @OneToMany(mappedBy = "category")
   @JsonIgnore
   private List<Event> events = new ArrayList<>();
}



