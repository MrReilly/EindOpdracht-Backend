package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.repositories.OrganizerRepository;
import org.springframework.stereotype.Service;

@Service
public class OrganizerService {

    private final OrganizerRepository repos;

    public OrganizerService(OrganizerRepository repos) {this.repos = repos;}

}
