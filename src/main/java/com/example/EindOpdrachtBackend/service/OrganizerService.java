package com.example.EindOpdrachtBackend.service;

import com.example.EindOpdrachtBackend.repository.OrganizerRepository;
import org.springframework.stereotype.Service;

@Service
public class OrganizerService implements UserService{

    private final OrganizerRepository repos;

    public OrganizerService(OrganizerRepository repos) {this.repos = repos;}

}
