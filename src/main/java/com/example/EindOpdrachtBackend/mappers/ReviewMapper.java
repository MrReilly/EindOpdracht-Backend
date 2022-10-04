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
        return mapper.map(review, ReviewGetDto.class);
    }

    public Review toEntity(ReviewPostDto dto) {
        return mapper.map(dto, Review.class);
    }

    public Review updateEntity(ReviewPostDto dto, Review review){

        mapper.map(dto, review);
        return review;
    }
}

