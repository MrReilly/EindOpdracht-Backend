package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.dtos.CategoryGetDto;
import com.example.EindOpdrachtBackend.dtos.EventGetDto;
import com.example.EindOpdrachtBackend.mappers.CategoryMapper;
import com.example.EindOpdrachtBackend.models.Category;
import com.example.EindOpdrachtBackend.models.CategoryOption;
import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.repositories.CategoryRepository;
import com.example.EindOpdrachtBackend.security.JwtService;
import com.example.EindOpdrachtBackend.util.DateConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CategoryService.class)
public class CategoryServiceTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtService jwtService;
    @MockBean
    private CategoryRepository repos;
    @MockBean
    private CategoryMapper mapper;

    @Test
    @WithMockUser(username="jadey", roles="ORGANIZER")
    @DisplayName("Should return a CategoryGetDto")
    void shouldReturnCategoryDto() {

        CategoryService categoryService = new CategoryService(mapper, repos);

        List<Event> eventList = new ArrayList<>();
        List<EventGetDto> listEventGetDto = new ArrayList<>();
        CategoryGetDto categoryGetDto = new CategoryGetDto();
        Category category = new Category(CategoryOption.FAIR, eventList);

        Event event = new Event(1L, category, "bv", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, null,null,  null, null);
        EventGetDto eventGetDto = new EventGetDto(1L, category, "bv", "Kermis", "Nijmegen", "Burchtstraat", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2,null,  null);

        eventList.add(event);
        listEventGetDto.add(eventGetDto);
        categoryGetDto.setEvents(listEventGetDto);

        Mockito.when(repos.findById(CategoryOption.FAIR)).thenReturn(Optional.of((category)));

        Mockito.when(mapper.CategoryGetDto(Mockito.any(Category.class))).thenReturn(categoryGetDto);

        assertEquals(categoryGetDto, categoryService.getCategory(CategoryOption.FAIR));
    }

    @Test
    @WithMockUser(username="jadey", roles="ORGANIZER")
    @DisplayName("Should return a error message, Category not found")
    void shouldReturnErrorMessage() {

        CategoryService categoryService = new CategoryService(mapper, repos);

        List<Event> eventList = new ArrayList<>();
        List<EventGetDto> listEventGetDto = new ArrayList<>();
        CategoryGetDto categoryGetDto = new CategoryGetDto();
        Category category = new Category(CategoryOption.FAIR, eventList);

        Event event = new Event(1L, category, "bv", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, null, null, null, null);
        EventGetDto eventGetDto = new EventGetDto(1L, category, "bv", "Kermis", "Nijmegen", "Burchtstraat", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, null, null);

        eventList.add(event);
        listEventGetDto.add(eventGetDto);
        categoryGetDto.setEvents(listEventGetDto);

        Mockito.when(repos.findById(CategoryOption.CIRCUS)).thenReturn(Optional.of((category)));

        Mockito.when(mapper.CategoryGetDto(Mockito.any(Category.class))).thenReturn(categoryGetDto);

        assertEquals("Category not found", categoryService.getCategory(CategoryOption.FAIR) );
    }
}