package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.dtos.EventGetDto;
import com.example.EindOpdrachtBackend.dtos.EventPostDto;
import com.example.EindOpdrachtBackend.exception.RecordNotFoundException;
import com.example.EindOpdrachtBackend.mappers.EventMapper;
import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.repositories.EventRepository;
import com.example.EindOpdrachtBackend.models.User;
import com.example.EindOpdrachtBackend.validation.IdChecker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private final EventRepository repos;
    private final EventMapper mapper;
    private final AuthService currentUser;
    private final IdChecker idChecker;

    public EventService(EventRepository repos, EventMapper mapper, AuthService currentUser, IdChecker idChecker) {

        this.repos = repos;
        this.mapper = mapper;
        this.currentUser = currentUser;
        this.idChecker = idChecker;
    }

    public List<EventGetDto> getAllEvents() {

         List<Event> allEvents = (List<Event>) repos.findAll();

         List<EventGetDto> allEventDtos = new ArrayList<>();

        for (Event event : allEvents) {

            allEventDtos.add(mapper.toDto(event));
        }

        return allEventDtos;
    }

    public ResponseEntity<Object> createEvent(EventPostDto dto) {

        Event newEvent = mapper.toEntity(dto);
        User user = currentUser.authenticateUser();

        newEvent.setOrganizer(user);

        newEvent.setOrganizationName(user.getOrganizationName());

        repos.save(newEvent);

        return (new ResponseEntity<>("Event " + newEvent.getId() + " - " + newEvent.getName() + " was created successfully!", HttpStatus.CREATED));
    }

    public Object getEvent(Long id) {

        return mapper.toDto((Event)idChecker.checkID(id, repos));
    }

    public String updateEvent(EventPostDto dto, Long id) {

        User user = currentUser.authenticateUser();
        Event event = (Event) idChecker.checkID(id, repos);

        if (user.equals(event.getOrganizer())) {

            Event updated = mapper.updateEntity(dto, event);

            this.repos.save(updated);

            return "The event was updated successfully!";
        }

            throw new RecordNotFoundException("Event not updated");
    }

    public ResponseEntity<Object> deleteEvent(Long id) {

        User user = currentUser.authenticateUser();
        Event event = (Event) idChecker.checkID(id, repos);

        if (user.equals(event.getOrganizer())) {

            this.repos.deleteById(event.getId());

            return new ResponseEntity<>( "The event was deleted successfully!", HttpStatus.OK) ;
        }

        return new ResponseEntity<>( "This is not your event to delete ", HttpStatus.UNAUTHORIZED);
    }
}
