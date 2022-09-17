package com.example.EindOpdrachtBackend.repositories;

import com.example.EindOpdrachtBackend.models.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository <Event, Long>{
}
