package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.dtos.ReviewGetDto;
import com.example.EindOpdrachtBackend.dtos.ReviewPostDto;
import com.example.EindOpdrachtBackend.mappers.EventMapper;
import com.example.EindOpdrachtBackend.mappers.ReviewMapper;
import com.example.EindOpdrachtBackend.models.*;
import com.example.EindOpdrachtBackend.repositories.EventRepository;
import com.example.EindOpdrachtBackend.repositories.ReviewRepository;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ReviewService.class)
class ReviewServiceTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtService jwtService;
    @MockBean
    private ReviewRepository reviewRepos;
    @MockBean
    private EventRepository eventRepos;
    @MockBean
    private UserRepository userRepos;
    @MockBean
    private ReviewMapper mapper;
    @MockBean
    private AuthService currentUser;
    @MockBean
    private IdChecker idChecker;
    @Captor
    private ArgumentCaptor<Review> reviewArgumentCaptor;

    @Test
    @WithMockUser(username="jadey", roles="VISITOR")
    @DisplayName("Should return a list of all Reviews")
    void shouldReturnAllReviews() {
        ReviewService reviewService = new ReviewService(reviewRepos, eventRepos, userRepos, mapper, currentUser, idChecker);

        List<Review> reviewList = new ArrayList<>();

        Mockito.when(reviewRepos.findAll()).thenReturn(reviewList);

        assertEquals(reviewList, reviewService.getAllReviews());
    }

    @Test
    @WithMockUser(username="jadey", roles="VISITOR")
    @DisplayName("Should create a review")
    void shouldCreateReview() {
        ReviewService reviewService = new ReviewService(reviewRepos, eventRepos, userRepos, mapper, currentUser, idChecker);

        Category category = new Category(CategoryOption.FAIR, null);
        Collection<Role> roles = new ArrayList<>();

        Role role = new Role(RoleOption.ORGANIZER, null);
        roles.add(role);

        User user = new User("jadey", "123", "Nijmegen", "bv", roles, null, null, null);

        Event event = new Event(1L, category, "bv", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, null, null, null, null);

        Review review = new Review(1L, "thomas", "I liked it!", DateConverter.parseDate("2022-11-30"), 2, event, user);
        ReviewPostDto reviewPostDto = new ReviewPostDto("It was very nice!", DateConverter.parseDate("2022-12-03"), 3);

        Mockito.when(mapper.toEntity(reviewPostDto)).thenReturn(review);
        Mockito.when(currentUser.authenticateUser()).thenReturn(user);

        Review returned = reviewService.createReview(reviewPostDto, 1L);

        eventRepos.save(event);

        reviewRepos.save(returned);

        Mockito.verify(reviewRepos, Mockito.times(2)).save(reviewArgumentCaptor.capture());

        assertEquals(review, reviewService.createReview(reviewPostDto, 1L));
    }

    @Test
    @WithMockUser(username="jadey", roles="VISITOR")
    @DisplayName("Should return a review")
    void shouldReturnReview() {
        ReviewService reviewService = new ReviewService(reviewRepos, eventRepos, userRepos, mapper, currentUser, idChecker);

        ReviewGetDto reviewGetDto = new ReviewGetDto(1L, 1L, "thomas", "It was nice!", DateConverter.parseDate("2023-01-02"), 1);

        Mockito.when(reviewService.getReview(1L)).thenReturn(reviewGetDto);

        assertEquals(reviewGetDto, reviewService.getReview(1L));
    }

    @Test
    @WithMockUser(username="jadey", roles="VISITOR")
    @DisplayName("Should delete a review")
    void shouldDeleteReview() {
        ReviewService reviewService = new ReviewService(reviewRepos, eventRepos, userRepos, mapper, currentUser, idChecker);

        Category category = new Category(CategoryOption.FAIR, null);
        Collection<Role> roles = new ArrayList<>();

        Role role = new Role(RoleOption.ORGANIZER, null);
        roles.add(role);

        List<Review> eventReviewList = new ArrayList<>();
        List<Review> userReviewList = new ArrayList<>();

        User user = new User("jadey", "123", "Nijmegen", "bv", roles, null, null, null);

        Event event = new Event(1L, category, "bv", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, null, null, null, null);

        Review review = new Review(1L, "thomas", "I liked it!", DateConverter.parseDate("2022-11-30"), 2, event, user);

        eventReviewList.add(review);
        userReviewList.add(review);

        user.setMyReviews(userReviewList);
        event.setReviews(eventReviewList);

        Mockito.when(currentUser.authenticateUser()).thenReturn(user);
        Mockito.when(idChecker.checkID(1L, eventRepos)).thenReturn(event);
        Mockito.when(idChecker.checkID(1L, reviewRepos)).thenReturn(review);

        assertEquals(1L, reviewService.deleteReview(1L));
    }
}