package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.exception.RecordNotFoundException;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public ReviewService(ReviewRepository reviewRepos, EventRepository eventRepos, UserRepository userRepos, ReviewMapper mapper, AuthService currentUser, IdChecker idChecker) {

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

    public ResponseEntity<Object> createReview(ReviewPostDto dto, Long eventId) {

        Review newReview = mapper.toEntity(dto);
        User user = currentUser.authenticateUser();
        Event reviewedEvent = (Event) idChecker.checkID(eventId, eventRepos);

        for (int i = 0; i < reviewedEvent.getReviews().size(); i++) {

            if (reviewedEvent.getReviews().get(i).getAuthorName().equals(user.getUsername())) {

                return new ResponseEntity<>("You have already reviewed this event", HttpStatus.ALREADY_REPORTED);
            }
        }

        newReview.setEvent(reviewedEvent);
        newReview.setAuthor(user);
        newReview.setAuthorName(user.getUsername());

        reviewRepos.save(newReview);

        return new ResponseEntity<>("Review successfully posted!", HttpStatus.CREATED);
    }

    public List<ReviewGetDto> getReview(Long id) {

        Event toGet = (Event) idChecker.checkID(id, eventRepos);

        List<Review> eventReviews = toGet.getReviews();

        List<ReviewGetDto> mappedList = new ArrayList<>();

        for (int i = 0; i < eventReviews.size(); i++) {

            Review review = eventReviews.get(i);

            ReviewGetDto mappedReview = mapper.toDto(review);

            mappedList.add(mappedReview);
        }

        return mappedList;
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

        throw new RecordNotFoundException("The review was not deleted");
    }
}

