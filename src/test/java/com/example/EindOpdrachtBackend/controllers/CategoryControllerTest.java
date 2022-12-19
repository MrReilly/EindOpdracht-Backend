package com.example.EindOpdrachtBackend.controllers;

import com.example.EindOpdrachtBackend.dtos.CategoryGetDto;
import com.example.EindOpdrachtBackend.dtos.EventGetDto;
import com.example.EindOpdrachtBackend.models.CategoryOption;
import com.example.EindOpdrachtBackend.security.JwtService;
import com.example.EindOpdrachtBackend.services.CategoryService;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtService jwtService;
    @MockBean
    CategoryService categoryService;

    @Test
    @WithMockUser(username = "jadey", roles = "ORGANIZER")
    @DisplayName("Should return a list of Events in the specified category")
    void shouldReturnListOfEventsInCategory() throws Exception {

        List<EventGetDto> events = new ArrayList<>();
        CategoryGetDto dto = new CategoryGetDto(events);

        Mockito.when(categoryService.getCategory(CategoryOption.valueOf("FAIR"))).thenReturn(dto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/category/FAIR"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.events", is(events)));


    }
}


