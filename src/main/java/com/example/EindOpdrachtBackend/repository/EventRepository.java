package com.example.EindOpdrachtBackend.repository;

import com.example.EindOpdrachtBackend.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository <Event, Long>{
}
