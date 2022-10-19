package com.example.EindOpdrachtBackend.mappers;

import com.example.EindOpdrachtBackend.dtos.CategoryGetDto;
import com.example.EindOpdrachtBackend.dtos.EventGetDto;
import com.example.EindOpdrachtBackend.models.Category;
import com.example.EindOpdrachtBackend.models.CategoryOption;
import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.security.JwtService;
import com.example.EindOpdrachtBackend.util.DateConverter;
import org.hibernate.validator.constraints.ModCheck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
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

@WebMvcTest(CategoryMapper.class)
class CategoryMapperTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtService jwtService;
    @MockBean
    EventMapper mapper;

    @Test
    @WithMockUser(username="jadey", roles="ORGANIZER")
    @DisplayName("Should map Category and return CategoryDto")
    void shouldReturnCategoryGetDto() {

        CategoryMapper categoryMapper = new CategoryMapper(mapper);

        List<Event> eventList = new ArrayList<>();
        Category category = new Category(CategoryOption.FAIR, eventList);
        Event event = new Event(1L, category, "bv", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, null, null, null, null);
        eventList.add(event);
        EventGetDto eventGetDto = new EventGetDto(1L, category, "bv", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, null, null);
        CategoryGetDto categoryGetDto = new CategoryGetDto();
        List<EventGetDto> eventGetDtoList = new ArrayList<>();
        eventGetDtoList.add(eventGetDto);
        categoryGetDto.setEvents(eventGetDtoList);

        Mockito.when(mapper.toDto(event)).thenReturn(eventGetDto);

        assertEquals(categoryGetDto, categoryMapper.CategoryGetDto(category));
    }
}