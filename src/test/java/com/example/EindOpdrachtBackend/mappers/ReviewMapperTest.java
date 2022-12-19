package com.example.EindOpdrachtBackend.mappers;

import com.example.EindOpdrachtBackend.dtos.ReviewGetDto;
import com.example.EindOpdrachtBackend.dtos.ReviewPostDto;
import com.example.EindOpdrachtBackend.models.Category;
import com.example.EindOpdrachtBackend.models.CategoryOption;
import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.models.Review;
import com.example.EindOpdrachtBackend.security.JwtService;
import com.example.EindOpdrachtBackend.util.DateConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ReviewMapper.class)
class ReviewMapperTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtService jwtService;
    @MockBean
    ModelMapper modelMapper;

    @Test
    @WithMockUser(username = "jadey", roles = "ORGANIZER")
    @DisplayName("Should map a Review to a ReviewDto and return it")
    void shouldReturnReviewDto() {

        Category category = new Category(CategoryOption.FAIR, null);
        ReviewMapper reviewMapper = new ReviewMapper(modelMapper);
        Event event = new Event(1L, category, "bv", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, null, null, null, null);
        Review review = new Review(1L, "thomas", "I liked it!", DateConverter.parseDate("2022-11-30"), 2, event, null);
        ReviewGetDto reviewGetDto = new ReviewGetDto(1L, 1L, "thomas", "It was nice!", DateConverter.parseDate("2023-01-02"), 1);

        Mockito.when(modelMapper.map(review, ReviewGetDto.class)).thenReturn(reviewGetDto);

        assertEquals(reviewGetDto, reviewMapper.toDto(review));
    }

    @Test
    @WithMockUser(username = "jadey", roles = "ORGANIZER")
    @DisplayName("Should map a ReviewDto to a Review and return it")
    void shouldReturnReview() {

        ReviewMapper reviewtMapper = new ReviewMapper(modelMapper);

        Review review = new Review(1L, "thomas", "I liked it!", DateConverter.parseDate("2022-11-30"), 2, null, null);
        ReviewPostDto reviewPostDto = new ReviewPostDto("It was very nice!", DateConverter.parseDate("2022-12-03"), 3);

        Mockito.when(modelMapper.map(reviewPostDto, Review.class)).thenReturn(review);

        assertEquals(review, reviewtMapper.toEntity(reviewPostDto));

    }
}