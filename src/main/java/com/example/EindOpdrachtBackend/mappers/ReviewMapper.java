package com.example.EindOpdrachtBackend.mappers;

import com.example.EindOpdrachtBackend.dtos.ReviewGetDto;
import com.example.EindOpdrachtBackend.dtos.ReviewPostDto;
import com.example.EindOpdrachtBackend.models.Review;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    private final ModelMapper mapper;

    public ReviewMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ReviewGetDto toDto(Review review) {

       ReviewGetDto mapped = mapper.map(review, ReviewGetDto.class);

       mapped.setEventId( review.getEvent().getId());

       return mapped;

    }

    public Review toEntity(ReviewPostDto dto) {
        return mapper.map(dto, Review.class);
    }

    }


