package com.example.EindOpdrachtBackend.repositories;

import com.example.EindOpdrachtBackend.models.Organizer;
import com.example.EindOpdrachtBackend.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepository extends CrudRepository<Organizer, Long> {
}
