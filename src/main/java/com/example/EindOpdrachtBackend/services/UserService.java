package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repos;

    public UserService(UserRepository repos) {this.repos = repos;}
}
