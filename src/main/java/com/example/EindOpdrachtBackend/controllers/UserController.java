package com.example.EindOpdrachtBackend.controllers;

import com.example.EindOpdrachtBackend.dtos.UserDetailsUpdateDto;
import com.example.EindOpdrachtBackend.dtos.UserPostDto;
import com.example.EindOpdrachtBackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.EindOpdrachtBackend.validation.StringBuilderValidation.stringBuilder;


@RestController
public class UserController {

    private final UserService service;

    UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserPostDto dto, BindingResult br) {

        if (br.hasErrors()) {

            return stringBuilder.validation(br);

        } else {

            String createdUserName = service.createUser(dto);

            return new ResponseEntity<>( createdUserName + " was created successfully", HttpStatus.CREATED);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Object> getUser() {

        return new ResponseEntity<>(service.getUserProfile(), HttpStatus.OK);
        }

    @GetMapping("/user/myFavorites")
    public ResponseEntity<Object> getMyFavorites() {

        return new ResponseEntity<>((service.getMyFavoriteEvents()), HttpStatus.OK);
    }

    @GetMapping("/user/myEvents")
    public ResponseEntity<Object> getMyEvents() {

        return new ResponseEntity<>((service.getMyEvents()), HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDetailsUpdateDto dto, BindingResult br) {

        if (br.hasErrors()) {

            return stringBuilder.validation(br);

        } else {

            return new ResponseEntity<>( "User " + service.updateUser(dto) + " was updated successfully", HttpStatus.CREATED);
        }
    }

    @PatchMapping("/user/myFavorites/add/{eventId}")
    public ResponseEntity<Object> saveFavoriteEvent(@PathVariable Long eventId) {

           return service.saveFavoriteEvent(eventId);
        }

    @DeleteMapping("/user/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable String username) {

        return new ResponseEntity<>(service.deleteUser(username) + " was deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/user/myFavorites/remove/{eventId}")
    public ResponseEntity<Object> removeFavoriteEvent(@PathVariable Long eventId) {

        return new ResponseEntity<>("Your favorite event with ID: " + service.removeFavorite(eventId) + " was successfully removed", HttpStatus.CREATED);
    }
}
