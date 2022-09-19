package com.example.EindOpdrachtBackend.service;

import com.example.EindOpdrachtBackend.exception.RecordNotFoundException;
import com.example.EindOpdrachtBackend.model.Event;
import com.example.EindOpdrachtBackend.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository repos;
    public EventService(EventRepository repos) {this.repos = repos;}
//----------------------------------------------------------------------------
    public List<Event> getAllEvents() {
        return (List<Event>) repos.findAll();
    }
//----------------------------------------------------------------------------

}
