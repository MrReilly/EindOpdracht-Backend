package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.repositories.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository repos;

    public EventService(EventRepository repos) {this.repos = repos;}

}
