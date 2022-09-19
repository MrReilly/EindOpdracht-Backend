package com.example.EindOpdrachtBackend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="visitors")
public class Visitor extends User{

   @ManyToMany(cascade = CascadeType.ALL)
    private List<Event> myFavoriteEvents;

    public Visitor() {
    }

    public Visitor(String userName, String password, String defaultLocation) {
        super(userName, password, defaultLocation);
    }

    public List<Event> getMyFavoriteEvents() {
        return myFavoriteEvents;
    }

    public void setMyFavoriteEvents(List<Event> myFavoriteEvents) {
        this.myFavoriteEvents = myFavoriteEvents;
    }
}
