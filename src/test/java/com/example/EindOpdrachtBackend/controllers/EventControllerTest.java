package com.example.EindOpdrachtBackend.controllers;

import com.example.EindOpdrachtBackend.dtos.EventGetDto;
import com.example.EindOpdrachtBackend.models.*;
import com.example.EindOpdrachtBackend.security.JwtService;
import com.example.EindOpdrachtBackend.services.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;

@WebMvcTest(EventController.class)
class EventControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtService jwtService;
    @MockBean
    EventService eventService;
    @MockBean
    BindingResult bindingResult;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        }

    @Test
    @WithMockUser(username="jadey", roles="ORGANIZER")
    @DisplayName("Should return all Events")
    void shouldReturnAllEvent() throws Exception{

        List<EventGetDto> events = new ArrayList<>();

        Mockito.when(eventService.getAllEvents()).thenReturn(events);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/event/all"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", is(events)));
    }

    @Test
    @WithMockUser(username="jadey", roles="ORGANIZER")
    @DisplayName("Should return EventDto")
    void shouldReturnEventDto() throws Exception {

        EventGetDto dto = new EventGetDto(1L, null,  "bv", "Kermis", "Nijmegen", "Burchtstraat", 50.0000, 5.0000,  "5 euro", "gezellige kermis", null, null, 2, null, null);

        Mockito.when(eventService.getEvent(1L)).thenReturn(dto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/event/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.organizationName", is("bv")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Kermis")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location", is("Nijmegen")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", is("Burchtstraat")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.latCoordinate", is(50.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.longCoordinate", is(5.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.entryPrice", is("5 euro")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.textDescription", is("gezellige kermis")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.starRating", is(2)));
    }

    @Test
    @WithMockUser(username = "jadey", roles="ORGANIZER")
    @DisplayName("Should delete Event and return success message")
    void shouldDeleteEvent() throws Exception{

        Mockito.when(eventService.deleteEvent(1L)).thenReturn("The event was deleted successfully!");

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/event/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}