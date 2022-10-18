package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.repositories.EventRepository;
import com.example.EindOpdrachtBackend.repositories.UserRepository;
import com.example.EindOpdrachtBackend.dtos.ReviewGetDto;
import com.example.EindOpdrachtBackend.dtos.ReviewPostDto;
import com.example.EindOpdrachtBackend.mappers.ReviewMapper;
import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.models.Review;
import com.example.EindOpdrachtBackend.repositories.ReviewRepository;
import com.example.EindOpdrachtBackend.models.User;
import com.example.EindOpdrachtBackend.validation.IdChecker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepos;
    private final EventRepository eventRepos;
    private final UserRepository userRepos;
    private final ReviewMapper mapper;
    private final AuthService currentUser;
    private final IdChecker idChecker;

    public ReviewService(@Qualifier("reviews")
                         ReviewRepository reviewRepos, EventRepository eventRepos, UserRepository userRepos, ReviewMapper mapper, AuthService currentUser, IdChecker idChecker) {

        this.reviewRepos = reviewRepos;
        this.eventRepos = eventRepos;
        this.userRepos = userRepos;
        this.mapper = mapper;
        this.currentUser = currentUser;
        this.idChecker = idChecker;
    }

    public List<Review> getAllReviews() {

        return (List<Review>) reviewRepos.findAll();
    }

    public Review createReview(ReviewPostDto dto, Long eventId) {

        Review newReview = mapper.toEntity(dto);
        User user = currentUser.authenticateUser();


        Event reviewedEvent = (Event)idChecker.checkID(eventId, eventRepos);

            newReview.setEvent(reviewedEvent);
            newReview.setAuthor(user);
            newReview.setAuthorName(user.getUsername());

            reviewRepos.save(newReview);

            return newReview;
        }

    public ReviewGetDto getReview(Long id) {

        Review toGet = (Review) idChecker.checkID(id, reviewRepos);

        return mapper.toDto(toGet);
    }

    public Object deleteReview(Long id) {

        Review reviewToDelete = (Review) idChecker.checkID(id, reviewRepos);

        User user = currentUser.authenticateUser();

        Event eventToUpdate = (Event) idChecker.checkID((reviewToDelete.getEvent()).getId(), eventRepos);

        User userToUpdate = reviewToDelete.getAuthor();

        List<Review> eventListToUpdate = eventToUpdate.getReviews();

        List<Review> userListToUpdate = userToUpdate.getMyReviews();

        if (user.equals(reviewToDelete.getAuthor())) {

            eventListToUpdate.removeIf(review -> review.getId().equals(reviewToDelete.getId()));

            userListToUpdate.removeIf(review -> review.getId().equals(reviewToDelete.getId()));

            eventToUpdate.setReviews(eventListToUpdate);

            userToUpdate.setMyReviews(userListToUpdate);

            userRepos.save(userToUpdate);

            eventRepos.save(eventToUpdate);

            reviewRepos.deleteById(reviewToDelete.getId());

            return id;
        }

        return "The review was not deleted";
    }
    }

