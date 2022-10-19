package com.example.EindOpdrachtBackend.mappers;

import com.example.EindOpdrachtBackend.dtos.UserGetDto;
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

        List<Role> listRoles = new ArrayList<>();
        Object[] objectRoles = listRoles.toArray(new Role[0]);
        Role role = new Role(RoleOption.ORGANIZER, null);
        listRoles.add(role);

        User user = new User("thomas", "123", "Nijmegen", "bv",listRoles, null, null, null);
        UserGetDto userGetDto = new UserGetDto("thomas", "Nijmegen", "ok bv", objectRoles , null, null, null);

        Mockito.when(modelMapper.map(user, UserGetDto.class)).thenReturn(userGetDto);

        assertEquals(userGetDto, userMapper.mapUser(user));
    }

    @Test
    @WithMockUser(username = "jadey", roles = "ORGANIZER")
    @DisplayName("Should map a User to a UserProfileDto and return it")
    void shouldReturnUserProfile() {

        UserMapper userMapper = new UserMapper(modelMapper, eventService);

        List<Role> listRoles = new ArrayList<>();
        Object[] objectRoles = listRoles.toArray(new Role[0]);
        Role role = new Role(RoleOption.ORGANIZER, null);
        listRoles.add(role);

        User user = new User("thomas", "123", "Nijmegen", "bv",listRoles, null, null, null);
        UserGetDto profileDetails = new UserGetDto("thomas", "Nijmegen", "ok bv", objectRoles , null, null, null);

        Mockito.when(modelMapper.map(user, UserGetDto.class)).thenReturn(profileDetails);

        assertEquals(profileDetails, userMapper.mapUser(user));

    }

}