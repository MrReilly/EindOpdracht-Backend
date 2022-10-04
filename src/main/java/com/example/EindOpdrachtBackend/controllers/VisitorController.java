package com.example.EindOpdrachtBackend.controllers;

import com.example.EindOpdrachtBackend.dtos.VisitorPostDto;
import com.example.EindOpdrachtBackend.services.VisitorService;
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
public class VisitorController {

    private final VisitorService service;


    VisitorController(VisitorService service) {
        this.service = service;

    }

    @PostMapping("/visitor")
    public ResponseEntity<Object> createVisitor(@RequestBody VisitorPostDto dto, BindingResult br) {

        if (br.hasErrors()) {

            return stringBuilder.validation(br);

        } else {

            Long createdVisitorId = service.createVisitor(dto);

            return new ResponseEntity<>("ID number " + createdVisitorId + " was created successfully", HttpStatus.CREATED);
        }
    }

    @GetMapping("/visitors")
    public ResponseEntity<Object> getAllVisitors() {
        return new ResponseEntity(service.getAllVisitors(), HttpStatus.OK);
    }


    @GetMapping("/visitor/{id}")
    public ResponseEntity<Object> getVisitor(@PathVariable Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) {
            return new ResponseEntity<>("Hello " + ((UserDetails) auth.getPrincipal()).getUsername() + "We found user: " + service.getVisitor(id).getUsername(), HttpStatus.FOUND);
        }

        return new ResponseEntity<>(" Hello Stranger, we found user with ID: " + service.getVisitor(id), HttpStatus.OK);

    }

    @PutMapping("/visitor/{id}")
    public ResponseEntity<Object> updateVisitor(@PathVariable Long id, @Valid @RequestBody VisitorPostDto dto, BindingResult br) {

        if (br.hasErrors()) {

            return stringBuilder.validation(br);

        } else {

            return new ResponseEntity<>("ID number " + service.updateVisitor(dto, id).getId() + " was updated successfully", HttpStatus.CREATED);
        }
    }


    @DeleteMapping("/visitor/{id}")
    public ResponseEntity<Object> deleteVisitor(@PathVariable Long id) {

        return new ResponseEntity<>("ID number" + service.deleteVisitor(id) + " was deleted successfully", HttpStatus.OK);
    }

}
