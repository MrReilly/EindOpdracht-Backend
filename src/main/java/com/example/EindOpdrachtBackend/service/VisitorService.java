package com.example.EindOpdrachtBackend.service;

import com.example.EindOpdrachtBackend.repository.VisitorRepository;
import org.springframework.stereotype.Service;

@Service
public class VisitorService implements UserService{

    private final VisitorRepository repos;

    public VisitorService(VisitorRepository repos) {this.repos = repos;}
}
