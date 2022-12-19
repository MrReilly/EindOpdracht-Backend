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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<Object> createUser(UserPostDto dto) {

        Optional<User> requestedNewUser = userRepos.findById(dto.getUsername());

        if (requestedNewUser.isPresent()) {

            return new ResponseEntity<>("Username already exists", HttpStatus.ALREADY_REPORTED);
        }

        User newUser = new User();

        newUser.setUsername(dto.getUsername());
        newUser.setPassword(encoder.encode(dto.getPassword()));
        newUser.setOrganizationName(dto.getOrganizationName());
        newUser.setDefaultLatCoordinate(dto.getDefaultLatCoordinate());
        newUser.setDefaultLongCoordinate(dto.getDefaultLongCoordinate());
        newUser.setDefaultLocationName(dto.getDefaultLocationName());

        Optional<Role> or = roleRepos.findById(RoleOption.valueOf(dto.getRole()));

        if (or.isPresent()) {

            Role newRole = or.get();

            newUser.setRole(newRole);
        }

        userRepos.save(newUser);

        return new ResponseEntity<>("Account created successfully for: " + newUser.getUsername(), HttpStatus.CREATED);
    }

    public UserGetDto getUser() {

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

        if (dto.getOrganizationName() == null) {
            assert true;
        } else {
            user.setOrganizationName(dto.getOrganizationName());
        }

        if (dto.getDefaultLocationName() == null) {
            assert true;
        } else {
            user.setDefaultLocationName(dto.getDefaultLocationName());
            user.setDefaultLatCoordinate(dto.getDefaultLatCoordinate());
            user.setDefaultLongCoordinate(dto.getDefaultLongCoordinate());
        }

        user.setDefaultLocationName(dto.getDefaultLocationName());

        if (dto.getPassword() == null) {
            assert true;
        } else {
            user.setPassword(encoder.encode(dto.getPassword()));
        }

        Optional<Role> or = roleRepos.findById(RoleOption.valueOf(dto.getRole()));

        if (or.isPresent()) {

            Role newRole = or.get();

            user.setRole(newRole);
        }

        User saved = userRepos.save(user);

        return saved.getUsername();
    }

    public ResponseEntity<Object> saveFavoriteEvent(Long eventId) {

        User user = currentUser.authenticateUser();

        Event event = ((Event) idChecker.checkID(eventId, eventRepos));

        List<Event> listToUpdate = user.getMyFavoriteEvents();

        if (!listToUpdate.contains(event)) {

            listToUpdate.add(event);

            user.setMyFavoriteEvents(listToUpdate);

            userRepos.save(user);

            return new ResponseEntity<>(event.getName() + " added to your favorites", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("This event is already in your favorite list!", HttpStatus.ALREADY_REPORTED);

    }

    public Long removeFavorite(Long id) {

        User user = currentUser.authenticateUser();

        Event eventToRemove = (Event) idChecker.checkID(id, eventRepos);

        List<Event> userListToUpdate = user.getMyFavoriteEvents();

        userListToUpdate.removeIf(event -> event.getId().equals(eventToRemove.getId()));

        user.setMyFavoriteEvents(userListToUpdate);

        userRepos.save(user);

        return id;
    }

}
