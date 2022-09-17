package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.repositories.FavoriteEventRepository;
import org.springframework.stereotype.Service;

@Service
public class FavoriteEventService {

    private final FavoriteEventRepository repos;

    public FavoriteEventService(FavoriteEventRepository repos) {this.repos = repos;}

}
