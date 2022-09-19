package com.example.EindOpdrachtBackend.dto;

import com.example.EindOpdrachtBackend.model.Event;

import java.util.List;

public class VisitorDto extends UserDto{

    private List<Event> myFavoriteEvents;

    public VisitorDto() {
    }

    public VisitorDto(List<Event> myFavoriteEvents) {
        this.myFavoriteEvents = myFavoriteEvents;
    }

    public List<Event> getMyFavoriteEvents() {
        return myFavoriteEvents;
    }

    public void setMyFavoriteEvents(List<Event> myFavoriteEvents) {
        this.myFavoriteEvents = myFavoriteEvents;
    }
}
