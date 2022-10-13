package com.example.EindOpdrachtBackend.controllers;

import com.example.EindOpdrachtBackend.dtos.EventPostDto;
import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.services.EventService;
import com.example.EindOpdrachtBackend.validation.UserAuthenticator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.EindOpdrachtBackend.validation.StringBuilderValidation.stringBuilder;


@RestController
public class EventController {

    private final EventService service;


    EventController(EventService service) {
        this.service = service;

    }

    @PostMapping("/event")
    public ResponseEntity<Object> createEvent(@Valid @RequestBody EventPostDto dto, BindingResult br) {

        if (br.hasErrors()) {

            return stringBuilder.validation(br);

        } else {

            Event newEvent = service.createEvent(dto);

            return new ResponseEntity<>("Event " + newEvent.getName() + " with ID number " + newEvent.getId() + " was created successfully", HttpStatus.CREATED);
        }
    }

    @GetMapping("/event/All")
    public ResponseEntity<Object> getAllEvent() {
        return new ResponseEntity<>(service.getAllEvents(), HttpStatus.OK);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<Object> getEvent(@PathVariable Long id) {

            return new ResponseEntity<>( service.getEvent(id), HttpStatus.FOUND);
        }


    @PutMapping("/event/{id}")
    public ResponseEntity<Object> updateEvent(@PathVariable Long id, @Valid @RequestBody EventPostDto dto, BindingResult br) {

        if (br.hasErrors()) {

            return stringBuilder.validation(br);

        } else {

            return new ResponseEntity<>(service.updateEvent(dto, id), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<Object> deleteEvent(@PathVariable Long id) {

        return new ResponseEntity<>( service.deleteEvent(id) , HttpStatus.OK);
    }
}
