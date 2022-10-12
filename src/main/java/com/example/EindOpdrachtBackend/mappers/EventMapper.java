package com.example.EindOpdrachtBackend.mappers;

import com.example.EindOpdrachtBackend.dtos.EventGetDto;
import com.example.EindOpdrachtBackend.dtos.EventPostDto;
import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.models.Review;
import com.example.EindOpdrachtBackend.models.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.*;
//--------------------------------------------------------------------------------------------------------------------
@Component
public class EventMapper {

        private final ModelMapper mapper;

        public EventMapper(ModelMapper mapper) {
            this.mapper = mapper;
        }
//--------------------------------------------------------------------------------------------------------------------
        public EventGetDto toDto(Event event) {

           EventGetDto mapped = mapper.map(event, EventGetDto.class);

            List<Review> fullList = event.getReviews();

            List<Review> censoredReviewList = new ArrayList<>();

            Integer averageStarRating = 0;

            for (Review review : fullList) {

                Review censoredReview = new Review();

                censoredReview.setId(review.getId());
                censoredReview.setReviewDate(review.getReviewDate());
                censoredReview.setAuthorName(review.getAuthor().getUsername());
                censoredReview.setReviewText(review.getReviewText());
                censoredReview.setStarRating(review.getStarRating());


                censoredReviewList.add(censoredReview);

                averageStarRating += review.getStarRating();
            }

            mapped.setReviews(censoredReviewList);
            mapped.setStarRating(averageStarRating / mapped.getReviews().size());



            return mapped;
        }
//--------------------------------------------------------------------------------------------------------------------
        public Event toEntity(EventPostDto dto) {
            return mapper.map(dto, Event.class);
        }
//--------------------------------------------------------------------------------------------------------------------
            public Event updateEntity(EventPostDto dto, Event event){

            mapper.map(dto, event);
            return event;
        }
    }

