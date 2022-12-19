package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.dtos.EventGetDto;
import com.example.EindOpdrachtBackend.dtos.EventPostDto;
import com.example.EindOpdrachtBackend.mappers.EventMapper;
import com.example.EindOpdrachtBackend.models.*;
import com.example.EindOpdrachtBackend.repositories.EventRepository;
import com.example.EindOpdrachtBackend.repositories.ImageRepository;
import com.example.EindOpdrachtBackend.repositories.UserRepository;
import com.example.EindOpdrachtBackend.security.JwtService;
import com.example.EindOpdrachtBackend.util.DateConverter;
import com.example.EindOpdrachtBackend.validation.IdChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(EventService.class)
class EventServiceTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtService jwtService;
    @MockBean
    private EventRepository repos;
    @MockBean
    private EventMapper mapper;
    @MockBean
    private AuthService currentUser;
    @MockBean
    private IdChecker idChecker;
    @MockBean
    ImageRepository imageRepos;

    @MockBean
    private UserRepository userRepos;
    @Captor
    private ArgumentCaptor<Event> eventArgumentCaptor;

    @Test
    @WithMockUser(username = "jadey", roles = "ORGANIZER")
    @DisplayName("Should return a list of all Events")
    void shouldReturnAllEvents() {

        EventService eventService = new EventService(repos, mapper, userRepos, imageRepos, currentUser, idChecker);

        List<Event> eventList = new ArrayList<>();

        Mockito.when(repos.findAll()).thenReturn(eventList);

        assertEquals(eventList, eventService.getAllEvents());
    }

    @Test
    @WithMockUser(username = "jadey", roles = "ORGANIZER")
    @DisplayName("Should create and save an event in repos")
    void shouldCreateEvent() {

        Category category = new Category(CategoryOption.FAIR, null);

        Role role = new Role(RoleOption.ORGANIZER, null);

        User user = new User("jadey", "123", "bv", null, null, null, role, null, null, null);

        EventService eventService = new EventService(repos, mapper, userRepos, imageRepos, currentUser, idChecker);

        EventPostDto eventPostDto = new EventPostDto("FAIR", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"));
        Event event = new Event(1L, category, "bv", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, null, null, null, null);

        Mockito.when(mapper.toEntity(eventPostDto)).thenReturn(event);
        Mockito.when(currentUser.authenticateUser()).thenReturn(user);

        repos.save(event);

        Mockito.verify(repos, Mockito.times(1)).save(eventArgumentCaptor.capture());

        assertEquals(new ResponseEntity<>("Event " + event.getId() + " - " + event.getName() + " was created successfully!", HttpStatus.CREATED), eventService.createEvent(eventPostDto));

        assertEquals(category, eventArgumentCaptor.getValue().getCategory());
        assertEquals("Kermis", eventArgumentCaptor.getValue().getName());
        assertEquals("bv", eventArgumentCaptor.getValue().getOrganizationName());
        assertEquals("Nijmegen", eventArgumentCaptor.getValue().getLocation());
        assertEquals("Burchtstraat 1", eventArgumentCaptor.getValue().getAddress());
        assertEquals(50.0000, eventArgumentCaptor.getValue().getLatCoordinate());
        assertEquals(5.0000, eventArgumentCaptor.getValue().getLongCoordinate());
        assertEquals("5 euro", eventArgumentCaptor.getValue().getEntryPrice());
        assertEquals("gezellige kermis", eventArgumentCaptor.getValue().getTextDescription());
        assertEquals(DateConverter.parseDate("2022-12-31"), eventArgumentCaptor.getValue().getStartDate());
        assertEquals(DateConverter.parseDate("2023-01-01"), eventArgumentCaptor.getValue().getEndDate());
        assertEquals(2, eventArgumentCaptor.getValue().getStarRating());
    }

    @Test
    @WithMockUser(username = "jadey", roles = "ORGANIZER")
    @DisplayName("Should return a EventGetDto")
    void shouldReturnEventGetDto() {

        EventService eventService = new EventService(repos, mapper, userRepos, imageRepos, currentUser, idChecker);

        Category category = new Category(CategoryOption.FAIR, null);
        Event event = new Event(1L, category, "bv", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, null, null, null, null);
        EventGetDto eventGetDto = new EventGetDto(1L, category, "bv", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, null, null);

        Mockito.when(idChecker.checkID(1L, repos)).thenReturn(event);
        Mockito.when(mapper.toDto(event)).thenReturn(eventGetDto);

        assertEquals(eventGetDto, eventService.getEvent(1L));
    }

    @Test
    @WithMockUser(username = "jadey", roles = "ORGANIZER")
    @DisplayName("Should save the updated Event to repos and return the event id")
    void shouldUpdateEventAndReturnId() {

        EventService eventService = new EventService(repos, mapper, userRepos, imageRepos, currentUser, idChecker);

        Category category = new Category(CategoryOption.FAIR, null);

        Role role = new Role(RoleOption.ORGANIZER, null);

        User user = new User("jadey", "123", "bv", null, null, null, role, null, null, null);

        EventPostDto eventPostDto = new EventPostDto("FAIR", "Kermis", "Arnhem", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"));
        Event event = new Event(1L, category, "bv", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, null, user, null, null);
        Event updatedEvent = new Event(1L, category, "bv", "Kermis", "Arnhem", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, null, user, null, null);

        repos.save(event);

        Mockito.when(currentUser.authenticateUser()).thenReturn(user);
        Mockito.when(idChecker.checkID(1L, repos)).thenReturn(event);
        Mockito.when(mapper.updateEntity(eventPostDto, event)).thenReturn(updatedEvent);
        Mockito.when(userRepos.save(user)).thenReturn(null);

        eventService.updateEvent(eventPostDto, 1L);

        Mockito.verify(repos, Mockito.times(2)).save(eventArgumentCaptor.capture());

        assertEquals("The event was updated successfully!", eventService.updateEvent(eventPostDto, 1L));

        assertEquals(category, eventArgumentCaptor.getValue().getCategory());
        assertEquals("Kermis", eventArgumentCaptor.getValue().getName());
        assertEquals("bv", eventArgumentCaptor.getValue().getOrganizationName());
        assertEquals("Arnhem", eventArgumentCaptor.getValue().getLocation());
        assertEquals("Burchtstraat 1", eventArgumentCaptor.getValue().getAddress());
        assertEquals(50.0000, eventArgumentCaptor.getValue().getLatCoordinate());
        assertEquals(5.0000, eventArgumentCaptor.getValue().getLongCoordinate());
        assertEquals("5 euro", eventArgumentCaptor.getValue().getEntryPrice());
        assertEquals("gezellige kermis", eventArgumentCaptor.getValue().getTextDescription());
        assertEquals(DateConverter.parseDate("2022-12-31"), eventArgumentCaptor.getValue().getStartDate());
        assertEquals(DateConverter.parseDate("2023-01-01"), eventArgumentCaptor.getValue().getEndDate());
        assertEquals(2, eventArgumentCaptor.getValue().getStarRating());
    }

    @Test
    @WithMockUser(username = "jadey", roles = "ORGANIZER")
    @DisplayName("Should delete the Event from Repos and remove from all USer Favorites")
    void shouldDeleteEventAndRemoveForFavorites() {


        Role role = new Role(RoleOption.ORGANIZER, null);

        Event favoriteEvent = new Event(1L, null, "bv", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, null, null, null, null);

        List<Event> favoriteList = new ArrayList<>();
        favoriteList.add(favoriteEvent);

        User user = new User("jadey", "123", "bv", null, null, null, role, favoriteList, null, null);

        List<User> visitorList = new ArrayList<>();
        visitorList.add(user);

        EventService eventService = new EventService(repos, mapper, userRepos, imageRepos, currentUser, idChecker);

        Category category = new Category(CategoryOption.FAIR, null);

        Event event = new Event(1L, category, "bv", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, null, user, null, visitorList);

        Mockito.when(currentUser.authenticateUser()).thenReturn(user);
        Mockito.when(idChecker.checkID(1L, repos)).thenReturn(event);

        repos.save(event);

        eventService.deleteEvent(1L);

        assertEquals(new ResponseEntity<>("The event was deleted successfully!", HttpStatus.OK), eventService.deleteEvent(1L));
    }
}