package com.example.EindOpdrachtBackend.mappers;

import com.example.EindOpdrachtBackend.dtos.EventGetDto;
import com.example.EindOpdrachtBackend.dtos.EventPostDto;
import com.example.EindOpdrachtBackend.dtos.ReviewPostDto;
import com.example.EindOpdrachtBackend.models.*;
import com.example.EindOpdrachtBackend.security.JwtService;
import com.example.EindOpdrachtBackend.util.DateConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(EventMapper.class)
class EventMapperTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtService jwtService;
    @MockBean
    ModelMapper modelMapper;

    @Test
    @WithMockUser(username = "jadey", roles="ORGANIZER")
    @DisplayName("Should map an Event to a EventDto and return it")
    void shouldReturnEventDto() {

        EventMapper eventMapper = new EventMapper(modelMapper);

        Category category = new Category(CategoryOption.FAIR, null);

        List<Review> fullListReviews = new ArrayList<>();
        List<Review> censoredlistReviews = new ArrayList<>();

        User user = new User("thomas", "123", "bv",null, null, null, null);
        Event event = new Event(1L, category, "bv", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, null, null, fullListReviews, null);

        Review fullReview = new Review(1L, "thomas", "I liked it!", DateConverter.parseDate("2022-11-30"), 2, event, user);
        fullListReviews.add(fullReview);

        Review censoredReview = new Review(1L, "thomas", "I liked it!", DateConverter.parseDate("2022-11-30"), 2, event, null);
        censoredlistReviews.add(censoredReview);

        EventGetDto eventGetDto = new EventGetDto(1L, category, "bv", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, censoredlistReviews, null);

        Mockito.when(modelMapper.map(event, EventGetDto.class)).thenReturn(eventGetDto);

        assertEquals(eventGetDto,eventMapper.toDto(event));
        assertNull(eventMapper.toDto(event).getReviews().get(0).getAuthor());
        assertEquals("bv",eventMapper.toDto(event).getOrganizationName());
        assertEquals("Kermis",eventMapper.toDto(event).getName());
        assertEquals("Nijmegen",eventMapper.toDto(event).getLocation());
        assertEquals("Burchtstraat 1",eventMapper.toDto(event).getAddress());
        assertEquals(50.0000,eventMapper.toDto(event).getLatCoordinate());
        assertEquals(5.0000,eventMapper.toDto(event).getLongCoordinate());
        assertEquals("gezellige kermis",eventMapper.toDto(event).getTextDescription());
        assertEquals("5 euro",eventMapper.toDto(event).getEntryPrice());
        assertEquals(DateConverter.parseDate("2022-12-31"),eventMapper.toDto(event).getStartDate());
        assertEquals(DateConverter.parseDate("2023-01-01"),eventMapper.toDto(event).getEndDate());
        assertEquals(2,eventMapper.toDto(event).getStarRating());
    }

    @Test
    @WithMockUser(username = "jadey", roles="ORGANIZER")
    @DisplayName("Should map an EventDto to a Event and return it")
    void shouldReturnEvent() {

        EventMapper eventMapper = new EventMapper(modelMapper);

        Category category = new Category(CategoryOption.FAIR, null);
        EventPostDto eventPostDto = new EventPostDto("FAIR", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"));
        Event event = new Event(1L, category, "bv", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, null , null, null, null);

        Mockito.when(modelMapper.map(eventPostDto, Event.class)).thenReturn(event);

        assertEquals(event,eventMapper.toEntity(eventPostDto));
    }

}