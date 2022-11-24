package com.example.EindOpdrachtBackend.mappers;

import com.example.EindOpdrachtBackend.dtos.UserDetailsUpdateDto;
import com.example.EindOpdrachtBackend.dtos.UserFavoriteGetDto;
import com.example.EindOpdrachtBackend.dtos.UserGetDto;
import com.example.EindOpdrachtBackend.dtos.UserMyEventGetDto;
import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.models.User;
import com.example.EindOpdrachtBackend.services.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
        private final ModelMapper mapper;
        private final EventService eventService;

        public UserMapper(ModelMapper mapper, EventService eventService) {
            this.mapper = mapper;
            this.eventService = eventService;
        }

        public UserFavoriteGetDto mapMyFavorites(User user) {

           UserFavoriteGetDto dto = new UserFavoriteGetDto();

           List<Event> fullList = user.getMyFavoriteEvents();

           List<Object> censoredList = new ArrayList<>();

            for (Event event : fullList) {

               censoredList.add(eventService.getEvent((event.getId())));
            }

            dto.setMyFavoriteEvents(censoredList);

            return dto;
        }

    public UserMyEventGetDto mapMyEvents(User user) {

        UserMyEventGetDto dto = new UserMyEventGetDto();

        List<Event> fullList = user.getMyEvents();

        List<Object> censoredList = new ArrayList<>();

        for (Event event : fullList) {
            censoredList.add(eventService.getEvent(event.getId()));
        }

        dto.setMyEvents(censoredList);

        return dto;
    }

    public UserGetDto mapUser(User user){

            return mapper.map(user, UserGetDto.class);
    }

    public UserGetDto mapUserProfile(User user) {

         UserGetDto profileDetails = new UserGetDto();

         profileDetails.setUsername(user.getUsername());
         profileDetails.setOrganizationName(user.getOrganizationName());
         profileDetails.setRole(user.getRole().getRolename().toString());

        return profileDetails;
    }

    }

