package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.dtos.EventPostDto;
import com.example.EindOpdrachtBackend.mappers.EventMapper;
import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.repositories.EventRepository;
import com.example.EindOpdrachtBackend.models.User;
import com.example.EindOpdrachtBackend.validation.IdChecker;
import com.example.EindOpdrachtBackend.validation.UserAuthenticator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

//----------------------------------------------------------------------------------------------------------------------

@Service
public class EventService {

    private final EventRepository repos;
    private final EventMapper mapper;
    private final UserAuthenticator currentUser;
    private final IdChecker idChecker;

    public EventService(@Qualifier("events") EventRepository repos, EventMapper mapper, UserAuthenticator currentUser, IdChecker idChecker) {

        this.repos = repos;
        this.mapper = mapper;
        this.currentUser = currentUser;
        this.idChecker = idChecker;
    }

//----------------------------------------------------------------------------------------------------------------------
    public List<Event> getAllEvents() {

        return (List<Event>) repos.findAll();
    }

//----------------------------------------------------------------------------------------------------------------------
    public Event createEvent(EventPostDto dto) {

        Event newEvent = mapper.toEntity(dto);
        User user = currentUser.authenticateUser();

        newEvent.setOrganizer(user);

        newEvent.setOrganizationName(user.getUsername());

        repos.save(newEvent);

        return newEvent;
    }

//----------------------------------------------------------------------------------------------------------------------
    public Object getEvent(Long id) {

        return mapper.toDto((Event)idChecker.checkID(id, repos));
    }

//----------------------------------------------------------------------------------------------------------------------
    public Object updateEvent(EventPostDto dto, Long id) {

        User user = currentUser.authenticateUser();
        Event event = (Event) idChecker.checkID(id, repos);

        if (user.equals(event.getOrganizer())) {

            Event updated = mapper.updateEntity(dto, event);

            Event saved = this.repos.save(updated);

            return "Event with ID: " + saved.getId() + " was updated successfully!";
        }

        return "Event not updated";
    }

//----------------------------------------------------------------------------------------------------------------------
    public Object deleteEvent(Long id) {

        User user = currentUser.authenticateUser();
        Event event = (Event) idChecker.checkID(id, repos);

        if (user.equals(event.getOrganizer())) {

            this.repos.deleteById(event.getId());

            return "Event with ID: " + id + " was deleted successfully!";
        }

        return "Event was not deleted";
    }
}
