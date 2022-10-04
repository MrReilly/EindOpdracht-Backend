package com.example.EindOpdrachtBackend.repositories;

import com.example.EindOpdrachtBackend.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
}
