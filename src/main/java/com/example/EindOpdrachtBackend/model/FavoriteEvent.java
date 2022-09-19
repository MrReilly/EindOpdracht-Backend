package com.example.EindOpdrachtBackend.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class FavoriteEvent extends Event{

    @ManyToOne
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;

    public FavoriteEvent() {
    }

    public FavoriteEvent(EventCategory category, String organizationInfo, String name, String location, String address, String entryPrice, String textDescription, Date startDate, Date endDate) {
        super(category, organizationInfo, name, location, address, entryPrice, textDescription, startDate, endDate);

    }

    public Visitor getOwner() {
        return visitor;
    }

    public void setOwner(Visitor owner) {
        this.visitor = owner;
    }
}
