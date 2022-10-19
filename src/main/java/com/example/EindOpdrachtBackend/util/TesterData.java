/*package com.example.EindOpdrachtBackend.util;

import com.example.EindOpdrachtBackend.dtos.*;
import com.example.EindOpdrachtBackend.models.*;;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TesterData {

     List<Role> listRoles = new ArrayList<>();
     List<Event> favoriteEvents = new ArrayList<>();
     List<Review> reviewList = new ArrayList<>();
     List<Event> myEvents = new ArrayList<>();
    List<EventGetDto> allEvents = new ArrayList<>();
    Collection<User> userList = new ArrayList<>();
    String[] rolesStringArray = new String[3];
    List<Object> objectFavoriteEvents = new ArrayList<>();
    List<Object> objectMyEvents = new ArrayList<>();
    Object[] objectRoles = new Object[3];

    Category category = new Category();
    User user = new User("thomas", "123", "Nijmegen", "bv",listRoles, favoriteEvents, reviewList, myEvents);
    Event event = new Event(1L, category, "bv", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, user, reviewList, userList);
    Review review = new Review(1L, "thomas", "I liked it!", DateConverter.parseDate("2022-11-30"), 2, event, user);

    CategoryGetDto categoryGetDto = new CategoryGetDto(allEvents);
    EventGetDto eventGetDto = new EventGetDto(1L, category, "bv", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"), 2, reviewList);
    EventPostDto eventPostDto = new EventPostDto("FAIR", "Kermis", "Nijmegen", "Burchtstraat 1", 50.0000, 5.0000, "5 euro", "gezellige kermis", DateConverter.parseDate("2022-12-31"), DateConverter.parseDate("2023-01-01"));
    ReviewGetDto reviewGetDto = new ReviewGetDto(1L, 1L, "thomas", "It was nice!", DateConverter.parseDate("2023-01-02"), 1);
    ReviewPostDto reviewPostDto = new ReviewPostDto("It was very nice!", DateConverter.parseDate("2022-12-03"), 3);
    RoleDto roleDto = new RoleDto("ORGANIZER");
    UserDetailsUpdateDto userDetailsUpdateDto = new UserDetailsUpdateDto(rolesStringArray, "thomas", "Nijmegen", "ok bv");
    UserFavoriteGetDto userFavoriteGetDto = new UserFavoriteGetDto(objectFavoriteEvents);
    UserGetDto userGetDto = new UserGetDto("thomas", "Nijmegen", "ok bv", objectRoles , objectFavoriteEvents, reviewList, objectMyEvents);
    UserMyEventGetDto userMyEventGetDto = new UserMyEventGetDto(objectMyEvents);
    UserPostDto userPostDto = new UserPostDto(rolesStringArray, "thomas", "123", "Nijmegen", "");

}
*/