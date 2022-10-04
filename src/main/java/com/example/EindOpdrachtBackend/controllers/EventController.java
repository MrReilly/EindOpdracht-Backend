package com.example.EindOpdrachtBackend.controllers;

import com.example.EindOpdrachtBackend.dtos.EventPostDto;
import com.example.EindOpdrachtBackend.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<Object> createEvent(@RequestBody EventPostDto dto, BindingResult br) {

        if (br.hasErrors()) {

            return stringBuilder.validation(br);

        } else {

            Long createdEventId = service.createEvent(dto);

            return new ResponseEntity<>("Event with ID number " + createdEventId + " was created successfully", HttpStatus.CREATED);
        }
    }

    @GetMapping("/events")
    public ResponseEntity<Object> getAllEvent() {
        return new ResponseEntity(service.getAllEvents(), HttpStatus.OK);
    }


    @GetMapping("/event/{id}")
    public ResponseEntity<Object> getEvent(@PathVariable Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) {
            return new ResponseEntity<>("Hello " + ((UserDetails) auth.getPrincipal()).getUsername() + "We found event with ID: " + service.getEvent(id).getId(), HttpStatus.FOUND);
        }

        return new ResponseEntity<>(" Hello Stranger, we found Event with ID: " + service.getEvent(id), HttpStatus.OK);

    }

    @PutMapping("/event/{id}")
    public ResponseEntity<Object> updateEvent(@PathVariable Long id, @Valid @RequestBody EventPostDto dto, BindingResult br) {

        if (br.hasErrors()) {

            return stringBuilder.validation(br);

        } else {

            return new ResponseEntity<>("Event with ID number " + service.updateEvent(dto, id).getId() + " was updated successfully", HttpStatus.CREATED);
        }
    }


    @DeleteMapping("/event/{id}")
    public ResponseEntity<Object> deleteEvent(@PathVariable Long id) {

        return new ResponseEntity<>("Event with ID number" + service.deleteEvent(id) + " was deleted successfully", HttpStatus.OK);
    }
}
