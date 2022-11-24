package com.example.EindOpdrachtBackend.controllers;

import com.example.EindOpdrachtBackend.dtos.ReviewGetDto;
import com.example.EindOpdrachtBackend.models.Review;
import com.example.EindOpdrachtBackend.security.JwtService;
import com.example.EindOpdrachtBackend.services.EventService;
import com.example.EindOpdrachtBackend.services.ReviewService;
import com.example.EindOpdrachtBackend.util.DateConverter;
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
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {


    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtService jwtService;
    @MockBean
    ReviewService reviewService;

    @Test
    @WithMockUser(username="jadey", roles="VISITOR")
    @DisplayName("Should return All reviews")
    void shouldReturnAllReviews() throws Exception {

        List<Review> reviewList = new ArrayList<>();

        Mockito.when(reviewService.getAllReviews()).thenReturn(reviewList);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/reviews"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", is(reviewList)));
    }

    @Test
    @WithMockUser(username="jadey", roles="ORGANIZER")
    @DisplayName("Should return reviewDto")
    void shouldReturnReviewDtoList() throws  Exception{

        ReviewGetDto reviewGetDto = new ReviewGetDto(1L, 1L, "jadey", "It was nice!", DateConverter.parseDate("2023-01-02"), 1);
        List<ReviewGetDto> reviewList = new ArrayList<>();

        reviewList.add(reviewGetDto);

        Mockito.when(reviewService.getReview(1L)).thenReturn(reviewList);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/review/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].authorName", is("jadey")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].reviewText", is("It was nice!")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].eventId", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].starRating", is(1)));
    }

}
