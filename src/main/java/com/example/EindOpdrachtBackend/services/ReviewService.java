package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.dtos.ReviewGetDto;
import com.example.EindOpdrachtBackend.dtos.ReviewPostDto;
import com.example.EindOpdrachtBackend.mappers.ReviewMapper;
import com.example.EindOpdrachtBackend.models.Review;
import com.example.EindOpdrachtBackend.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.EindOpdrachtBackend.validation.IdChecker.idChecker;

@Service
public class ReviewService {

    private final ReviewRepository repos;
    private final ReviewMapper mapper;

    public ReviewService(ReviewRepository repos, ReviewMapper mapper) {

        this.repos = repos;
        this.mapper = mapper;
    }

    public List<Review> getAllReviews(){

        return (List<Review>) repos.findAll();
    }

    public Long createReview(ReviewPostDto dto) {

        Review newReview = mapper.toEntity(dto);

        repos.save(newReview);

        return newReview.getId();
    }

    public ReviewGetDto getReview (Long id){

        Review toGet = (Review) idChecker.checkID(id, repos);

        return mapper.toDto(toGet);
    }

    public ReviewGetDto updateReview(ReviewPostDto dto, Long id) {

        Review toUpdate = (Review) idChecker.checkID(id, repos);

        Review updated = mapper.updateEntity(dto, toUpdate);

        Review saved = this.repos.save(updated);

        return mapper.toDto(saved);
    }

    public Long deleteReview(Long id) {

        Review toDelete = (Review) idChecker.checkID(id, repos);

        this.repos.deleteById(toDelete.getId());

        return id;
    }

}
