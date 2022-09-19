package com.example.EindOpdrachtBackend.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="visitors")
public class Visitor extends User{

   @OneToMany(mappedBy = "visitor")
    private List<FavoriteEvent> myFavoriteEvents;

    public Visitor() {
    }

    public Visitor(String userName, String password, String defaultLocation) {
        super(userName, password, defaultLocation);
    }

    public List<FavoriteEvent> getMyFavoriteEvents() {
        return myFavoriteEvents;
    }

    public void setMyFavoriteEvents(List<FavoriteEvent> myFavoriteEvents) {
        this.myFavoriteEvents = myFavoriteEvents;
    }
}
