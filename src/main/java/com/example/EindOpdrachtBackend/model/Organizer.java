package com.example.EindOpdrachtBackend.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="organizers")
public class Organizer extends User {

    @OneToMany(mappedBy = "organizer")
    private List<Event> myEvents;

    private String OrganizationName;

    public Organizer() {
    }

    public Organizer(String userName, String password, String defaultLocation, String organizationName) {
        super(userName, password, defaultLocation);
        OrganizationName = organizationName;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    public List<Event> getMyEvents() {
        return myEvents;
    }

    public void setMyEvents(List<Event> myEvents) {
        this.myEvents = myEvents;
    }
}
