package com.example.EindOpdrachtBackend.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Organizer extends User {

    @OneToMany
    List<Event> myEvents;

}
