package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.dtos.EventGetDto;
import com.example.EindOpdrachtBackend.dtos.EventPostDto;
import com.example.EindOpdrachtBackend.mappers.EventMapper;
import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.EindOpdrachtBackend.validation.IdChecker.idChecker;

@Service
public class EventService {

    private final EventRepository repos;
    private final EventMapper mapper;

    public EventService(EventRepository repos, EventMapper mapper) {

        this.repos = repos;
        this.mapper = mapper;

    }


    public List<Event> getAllEvents(){

        return (List<Event>) repos.findAll();
    }

    public Long createEvent(EventPostDto dto) {

        Event newEvent = mapper.toEntity(dto);

        repos.save(newEvent);

        return newEvent.getId();
    }

    public EventGetDto getEvent (Long id){

        Event toGet = (Event) idChecker.checkID(id, repos);

        return mapper.toDto(toGet);
    }

    public EventGetDto updateEvent(EventPostDto dto, Long id) {

        Event toUpdate = (Event) idChecker.checkID(id, repos);

        Event updated = mapper.updateEntity(dto, toUpdate);

        Event saved = this.repos.save(updated);

        return mapper.toDto(saved);
    }

    public Long deleteEvent(Long id) {

        Event toDelete = (Event) idChecker.checkID(id, repos);

        this.repos.deleteById(toDelete.getId());

        return id;
    }

}
