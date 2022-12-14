package com.example.EindOpdrachtBackend.controllers;

import com.example.EindOpdrachtBackend.dtos.UserPostDto;
import com.example.EindOpdrachtBackend.models.*;
import com.example.EindOpdrachtBackend.repositories.RoleRepository;
import com.example.EindOpdrachtBackend.security.JwtService;
import com.example.EindOpdrachtBackend.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtService jwtService;
    @MockBean
    UserService userService;

    @MockBean
    RoleRepository roleRepos;

    @BeforeEach()
    void setup() {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser(username="jadey", roles="ORGANIZER")
    @DisplayName("Should return all Users")
    void shouldReturnAllUsers() throws Exception{

        List<User> users = new ArrayList<>();

        Mockito.when(userService.getAllUsers()).thenReturn(users);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/users"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", is(users)));
    }


    @Test
    @WithMockUser(username="jadey", roles="ORGANIZER")
    @DisplayName("Should create, save and return user")
    void shouldCreateAndSaveUser() throws  Exception {

        Role role = new Role(RoleOption.ORGANIZER, null);
        List<Event> favoriteEvents = new ArrayList<>();
        List<Review> reviewList = new ArrayList<>();
        List<Event> myEvents = new ArrayList<>();
        Collection<User> users = new ArrayList<>();

        User user = new User("jadey", "123", "bv",null, null, role, favoriteEvents, reviewList, myEvents);
        users.add(user);
        role.setUsers(users);

        UserPostDto userPostDto = new UserPostDto("ORGANIZER", "jadey", "123", "", null, null);

        Mockito.when(userService.createUser(userPostDto)).thenReturn( new ResponseEntity<>("Account created successfully for: " + "jadey", HttpStatus.CREATED));

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/user")
                .content(asJsonString(new UserPostDto("ORGANIZER", "jadey", "123",  "", null, null)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}