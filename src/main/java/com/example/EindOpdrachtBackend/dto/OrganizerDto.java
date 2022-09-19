package com.example.EindOpdrachtBackend.dto;

import com.example.EindOpdrachtBackend.model.Event;

import java.util.List;
public class OrganizerDto extends UserDto{

    private List<Event> myEvents;

    private String OrganizationName;

    public OrganizerDto(List<Event> myEvents, String organizationName) {
        this.myEvents = myEvents;
        OrganizationName = organizationName;
    }

    public OrganizerDto(Long id, String userName, String password, String defaultLocation, List<Event> myEvents, String organizationName) {
        super(id, userName, password, defaultLocation);
        this.myEvents = myEvents;
        OrganizationName = organizationName;
    }

    public List<Event> getMyEvents() {
        return myEvents;
    }

    public void setMyEvents(List<Event> myEvents) {
        this.myEvents = myEvents;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }
}
