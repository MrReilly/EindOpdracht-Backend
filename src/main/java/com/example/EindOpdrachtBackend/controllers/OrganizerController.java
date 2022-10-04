package com.example.EindOpdrachtBackend.controllers;

import com.example.EindOpdrachtBackend.dtos.OrganizerPostDto;
import com.example.EindOpdrachtBackend.services.OrganizerService;
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
public class OrganizerController {

    private final OrganizerService service;


    OrganizerController(OrganizerService service) {
        this.service = service;

    }

    @PostMapping("/organizer")
    public ResponseEntity<Object> createOrganizer(@RequestBody OrganizerPostDto dto, BindingResult br) {

        if (br.hasErrors()) {

            return stringBuilder.validation(br);

        } else {

            Long createdOrganizerId = service.createOrganizer(dto);

            return new ResponseEntity<>("ID number " + createdOrganizerId + " was created successfully", HttpStatus.CREATED);
        }
    }

    @GetMapping("/organizers")
    public ResponseEntity<Object> getAllOrganizers() {
        return new ResponseEntity(service.getAllOrganizers(), HttpStatus.OK);
    }


    @GetMapping("/organizer/{id}")
    public ResponseEntity<Object> getOrganizer(@PathVariable Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) {
            return new ResponseEntity<>("Hello " + ((UserDetails) auth.getPrincipal()).getUsername() + "We found user: " + service.getOrganizer(id).getUsername(), HttpStatus.FOUND);
        }

        return new ResponseEntity<>(" Hello Stranger, we found user with ID: " + service.getOrganizer(id), HttpStatus.OK);

    }

    @PutMapping("/organizer/{id}")
    public ResponseEntity<Object> updateOrganizer(@PathVariable Long id, @Valid @RequestBody OrganizerPostDto dto, BindingResult br) {

        if (br.hasErrors()) {

            return stringBuilder.validation(br);

        } else {

            return new ResponseEntity<>("ID number " + service.updateOrganizer(dto, id).getId() + " was updated successfully", HttpStatus.CREATED);
        }
    }


    @DeleteMapping("/organizer/{id}")
    public ResponseEntity<Object> deleteOrganizer(@PathVariable Long id) {

        return new ResponseEntity<>("ID number" + service.deleteOrganizer(id) + " was deleted successfully", HttpStatus.OK);
    }
}




