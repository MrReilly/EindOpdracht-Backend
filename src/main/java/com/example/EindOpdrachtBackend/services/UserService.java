package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.models.RoleOption;
import com.example.EindOpdrachtBackend.repositories.EventRepository;
import com.example.EindOpdrachtBackend.dtos.UserDetailsUpdateDto;
import com.example.EindOpdrachtBackend.dtos.UserGetDto;
import com.example.EindOpdrachtBackend.dtos.UserPostDto;
import com.example.EindOpdrachtBackend.mappers.UserMapper;
import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.models.Role;
import com.example.EindOpdrachtBackend.models.User;
import com.example.EindOpdrachtBackend.repositories.RoleRepository;
import com.example.EindOpdrachtBackend.repositories.UserRepository;
import com.example.EindOpdrachtBackend.validation.IdChecker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepos;
    private final RoleRepository roleRepos;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    private final EventRepository eventRepos;
    private final AuthService currentUser;
    private final IdChecker idChecker;

    public UserService(@Qualifier("users") UserRepository userRepos, UserMapper mapper, @Qualifier("roles") RoleRepository roleRepos, PasswordEncoder encoder, EventRepository eventRepos, AuthService currentUser, IdChecker idChecker) {

        this.roleRepos = roleRepos;
        this.userRepos = userRepos;
        this.mapper = mapper;
        this.encoder = encoder;
        this.eventRepos = eventRepos;
        this.currentUser = currentUser;
        this.idChecker = idChecker;
    }

    public List<User> getAllUsers() {

        return (List<User>) userRepos.findAll();
    }

    public String createUser(UserPostDto dto) {

        User newUser = new User();

        newUser.setUsername(dto.getUsername());
        newUser.setPassword(encoder.encode(dto.getPassword()));
        newUser.setOrganizationName(dto.getOrganizationName());

            Optional<Role> or = roleRepos.findById(RoleOption.valueOf(dto.getRole()));

            if(or.isPresent()){

                Role newRole = or.get();

                newUser.setRole(newRole);
            }

        userRepos.save(newUser);

        return newUser.getUsername();
    }

    public UserGetDto getUser(){

        User user = currentUser.authenticateUser();

        return mapper.mapUser(user);
    }

    public Object getUserProfile() {

        User user = currentUser.authenticateUser();

            return mapper.mapUserProfile(user);
        }

    public Object getMyFavoriteEvents() {

        User user = currentUser.authenticateUser();

        return mapper.mapMyFavorites(user);
    }

    public Object getMyEvents() {

        User user = currentUser.authenticateUser();

        return mapper.mapMyEvents(user);
    }

    public String updateUser(UserDetailsUpdateDto dto) {

        User user = currentUser.authenticateUser();

        user.setOrganizationName(dto.getOrganizationName());

        if(dto.getPassword() == null){
            assert true;
        }
        else {
        user.setPassword(encoder.encode(dto.getPassword()));
        }

            Optional<Role> or = roleRepos.findById(RoleOption.valueOf(dto.getRole()));

            if(or.isPresent()) {

                Role newRole = or.get();

                user.setRole(newRole);
            }

                User saved = userRepos.save(user);

                return saved.getUsername();
            }

    public String saveFavoriteEvent (Long eventId) {

        User user = currentUser.authenticateUser();

        Event event = ((Event) idChecker.checkID(eventId, eventRepos));

        List<Event> listToUpdate = user.getMyFavoriteEvents();

        if (!listToUpdate.contains(event)) {

            listToUpdate.add(event);

            user.setMyFavoriteEvents(listToUpdate);

            userRepos.save(user);

            return (event.getName() + " added to favorites of user " + user.getUsername());
        }

            throw new DuplicateFormatFlagsException("This event is already in your favorite list!");
    }

    public String deleteUser(String username) {

        User cUser = currentUser.authenticateUser();

        User toDeleteUser = (User)idChecker.checkUsername(username, userRepos);

        if(cUser.equals(toDeleteUser)) {

            this.userRepos.deleteById(cUser.getUsername());
            return cUser.getUsername();
        }

        throw new IllegalArgumentException("you are not allowed to delete this user!");
    }

    public Long removeFavorite(Long id){

        User user = currentUser.authenticateUser();

        Event eventToRemove = (Event)idChecker.checkID(id,eventRepos);

        List<Event> userListToUpdate = user.getMyFavoriteEvents();

            userListToUpdate.removeIf(event -> event.getId().equals(eventToRemove.getId()));

            user.setMyFavoriteEvents(userListToUpdate);

            userRepos.save(user);

            return id;
    }

}
