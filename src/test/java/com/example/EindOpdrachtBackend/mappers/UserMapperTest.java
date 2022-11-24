package com.example.EindOpdrachtBackend.mappers;

import com.example.EindOpdrachtBackend.dtos.UserFavoriteGetDto;
import com.example.EindOpdrachtBackend.dtos.UserGetDto;
import com.example.EindOpdrachtBackend.dtos.UserMyEventGetDto;
import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.models.Role;
import com.example.EindOpdrachtBackend.models.RoleOption;
import com.example.EindOpdrachtBackend.models.User;
import com.example.EindOpdrachtBackend.security.JwtService;
import com.example.EindOpdrachtBackend.services.EventService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(UserMapper.class)
class UserMapperTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtService jwtService;
    @MockBean
    ModelMapper modelMapper;
    @MockBean
    EventService eventService;

    @Test
    @WithMockUser(username = "jadey", roles = "ORGANIZER")
    @DisplayName("Should map a User to a UserDto and return it")
    void shouldReturnUser() {

        UserMapper userMapper = new UserMapper(modelMapper, eventService);

        Role role = new Role(RoleOption.ORGANIZER, null);
        String stringRole = role.toString();


        User user = new User("thomas", "123", "bv",role, null, null, null);
        UserGetDto userGetDto = new UserGetDto("thomas",  "ok bv", stringRole , null, null, null);

        Mockito.when(modelMapper.map(user, UserGetDto.class)).thenReturn(userGetDto);

        assertEquals(userGetDto, userMapper.mapUser(user));
    }

    @Test
    @WithMockUser(username = "jadey", roles = "ORGANIZER")
    @DisplayName("Should map a User to a UserProfileDto and return it")
    void shouldReturnUserProfile() {

        UserMapper userMapper = new UserMapper(modelMapper, eventService);

        Role role = new Role(RoleOption.ORGANIZER, null);
        String stringRole = role.toString();

        User user = new User("thomas", "123", "bv", role, null, null, null);
        UserGetDto profileDetails = new UserGetDto("thomas",  "ok bv", stringRole , null, null, null);

        Mockito.when(modelMapper.map(user, UserGetDto.class)).thenReturn(profileDetails);

        assertEquals(profileDetails, userMapper.mapUser(user));

    }

    @Test
    @WithMockUser(username = "jadey", roles = "ORGANIZER")
    @DisplayName("Should map myFavorites to myFavoritesDto and return it")
    void shouldMapMyFavoriteEventsToDtoAndReturnIt() {

        UserMapper userMapper = new UserMapper(modelMapper, eventService);

        Role role = new Role(RoleOption.ORGANIZER, null);
        List<Event> favoriteEvents = new ArrayList<>();
        List<Object> objectFavoriteEvents = new ArrayList<>();

        UserFavoriteGetDto userFavoriteGetDto = new UserFavoriteGetDto(objectFavoriteEvents);
        User user = new User("thomas", "123", "bv", role, favoriteEvents, null, null);


        assertEquals(userFavoriteGetDto, userMapper.mapMyFavorites(user));

    }

    @Test
    @WithMockUser(username = "jadey", roles = "ORGANIZER")
    @DisplayName("Should map myEvents to myEventsDto and return it")
    void shouldMapMyEventsToDtoAndReturnIt() {

        UserMapper userMapper = new UserMapper(modelMapper, eventService);

        Role role = new Role(RoleOption.ORGANIZER, null);
        List<Event> myEvents = new ArrayList<>();
        List<Object> objectMyEvents = new ArrayList<>();

        UserMyEventGetDto userMyEventGetDto = new UserMyEventGetDto(objectMyEvents);
        User user = new User("thomas", "123", "bv", role, null, null, myEvents);


        assertEquals(userMyEventGetDto, userMapper.mapMyEvents(user));
    }
}