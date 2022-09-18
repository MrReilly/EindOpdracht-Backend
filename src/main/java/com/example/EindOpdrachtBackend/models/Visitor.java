package com.example.EindOpdrachtBackend.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Visitor extends User{

    @OneToMany
    private List<Event> myFavoriteEvents;

    public List<Event> getMyFavoriteEvents() {
        return myFavoriteEvents;
    }

    public void setMyFavoriteEvents(List<Event> myFavoriteEvents) {
        this.myFavoriteEvents = myFavoriteEvents;
    }
}
