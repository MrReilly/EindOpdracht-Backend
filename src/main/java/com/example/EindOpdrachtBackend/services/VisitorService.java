package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.repositories.VisitorRepository;
import org.springframework.stereotype.Service;

@Service
public class VisitorService {

    private final VisitorRepository repos;

    public VisitorService(VisitorRepository repos) {this.repos = repos;}
}
