package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.dtos.UserFavoriteGetDto;
import com.example.EindOpdrachtBackend.dtos.UserGetDto;
import com.example.EindOpdrachtBackend.dtos.UserMyEventGetDto;
import com.example.EindOpdrachtBackend.dtos.UserPostDto;
import com.example.EindOpdrachtBackend.mappers.UserMapper;;
import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.models.Role;
import com.example.EindOpdrachtBackend.models.User;
import com.example.EindOpdrachtBackend.repositories.EventRepository;
import com.example.EindOpdrachtBackend.repositories.RoleRepository;
import com.example.EindOpdrachtBackend.repositories.UserRepository;
import com.example.EindOpdrachtBackend.security.JwtService;
import com.example.EindOpdrachtBackend.util.DateConverter;
import com.example.EindOpdrachtBackend.validation.IdChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(UserService.class)
class UserServiceTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtService jwtService;
    @MockBean
    UserRepository userRepos;
    @MockBean
    RoleRepository roleRepos;
    @MockBean
    UserMapper mapper;
    @MockBean
    PasswordEncoder encoder;
    @MockBean
    EventRepository eventRepos;
    @MockBean
    AuthService currentUser;
    @MockBean
    IdChecker idChecker;
    @MockBean
    ArgumentCaptor<User> userArgumentCaptor;

    @Test
    @WithMockUser(username="jadey", roles="VISITOR")
    @DisplayName("Should return a list of all Users")
    void shouldReturnAllUsers() {
        UserService userService = new UserService(userRepos, mapper, roleRepos, encoder, eventRepos, currentUser, idChecker);

        List<User> users = new ArrayList<>();

        assertEquals(users, userService.getAllUsers());
    }

    @Test
    @WithMockUser(username="jadey", roles="VISITOR")
    @DisplayName("Should create save and return User")
    void shouldCreateSaveAndReturnUser() {
        UserService userService = new UserService(userRepos, mapper, roleRepos, encoder, eventRepos, currentUser, idChecker);

        List<User> users = new ArrayList<>();
        String[] rolesStringArray = new String[1];
        List<Role> roles = new ArrayList<>();
        Role role = new Role("VISITOR", users);
        Optional<Role> or = roleRepos.findById("VISITOR");
        roles.add(role);
        rolesStringArray[0] = "VISITOR";

        User user = new User("jadey", "123", "Nijmegen", "bv",roles, null, null, null);
        users.add(user);
        UserPostDto userPostDto = new UserPostDto(rolesStringArray, "jadey", "123", "Nijmegen", "bv");

        roleRepos.save(role);

        Mockito.when(encoder.encode("123")).thenReturn("123");
        Mockito.when(roleRepos.findById("VISITOR")).thenReturn(or);
        or.ifPresent(roles::add);

        assertEquals(user, userService.createUser(userPostDto));
    }

    @Test
    @WithMockUser(username="jadey", roles="VISITOR")
    @DisplayName("Should return User")
    void shouldReturnUser() {
        UserService userService = new UserService(userRepos, mapper, roleRepos, encoder, eventRepos, currentUser, idChecker);

        User user = new User("jadey", "123", "Nijmegen", "bv",null , null, null, null);
        UserGetDto userGetDto = new UserGetDto("thomas", "Nijmegen", "ok bv", null , null, null, null);

        Mockito.when(currentUser.authenticateUser()).thenReturn(user);
        Mockito.when(mapper.mapUser(user)).thenReturn(userGetDto);

        assertEquals(userGetDto, userService.getUser());

    }

    @Test
    @WithMockUser(username="jadey", roles="VISITOR")
    @DisplayName("Should return User profile details only")
    void shouldReturnUserProfileDetailsOnly() {

        UserService userService = new UserService(userRepos, mapper, roleRepos, encoder, eventRepos, currentUser, idChecker);

        User user = new User("jadey", "123", "Nijmegen", "bv",null , null, null, null);
        UserGetDto userGetDto = new UserGetDto("thomas", "Nijmegen", "ok bv", null , null, null, null);

        Mockito.when(currentUser.authenticateUser()).thenReturn(user);
        Mockito.when(mapper.mapUser(user)).thenReturn(userGetDto);

        assertEquals(userGetDto, userService.getUser());
    }

    @Test
    @WithMockUser(username="jadey", roles="VISITOR")
    @DisplayName("Should return Users Favorite Events")
    void shouldReturnUserFavoriteEvents() {
        UserService userService = new UserService(userRepos, mapper, roleRepos, encoder, eventRepos, currentUser, idChecker);

        UserFavoriteGetDto favoritesDto = new UserFavoriteGetDto();
        User user = new User("jadey", "123", "Nijmegen", "bv",null , null, null, null);
        List<Object> favoriteEvents = new ArrayList<>();
        Event event = new Event(1L, null, "bv", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, null, null, null);
        favoriteEvents.add(event);
        favoritesDto.setMyFavoriteEvents(favoriteEvents);

        Mockito.when(currentUser.authenticateUser()).thenReturn(user);
        Mockito.when(mapper.mapMyFavorites(user)).thenReturn(favoritesDto);

        assertEquals(favoritesDto, userService.getMyFavoriteEvents());
    }

    @Test
    @WithMockUser(username="jadey", roles="VISITOR")
    @DisplayName("Should return User created events")
    void shouldReturnMyEvents() {

        UserService userService = new UserService(userRepos, mapper, roleRepos, encoder, eventRepos, currentUser, idChecker);

        UserMyEventGetDto myEventsDto = new UserMyEventGetDto();
        User user = new User("jadey", "123", "Nijmegen", "bv",null , null, null, null);
        List<Object> myEventsEvents = new ArrayList<>();
        Event event = new Event(1L, null, "bv", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, null, null, null);
        myEventsEvents.add(event);
        myEventsDto.setMyEvents(myEventsEvents);

        Mockito.when(currentUser.authenticateUser()).thenReturn(user);
        Mockito.when(mapper.mapMyEvents(user)).thenReturn(myEventsDto);

        assertEquals(myEventsDto, userService.getMyEvents());

    }
}