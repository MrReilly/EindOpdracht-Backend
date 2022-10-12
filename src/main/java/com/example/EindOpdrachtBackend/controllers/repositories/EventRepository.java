package com.example.EindOpdrachtBackend.controllers.repositories;

import com.example.EindOpdrachtBackend.models.Event;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Qualifier("events")
@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
}
