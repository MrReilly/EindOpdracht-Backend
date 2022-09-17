package com.example.EindOpdrachtBackend.repositories;

import com.example.EindOpdrachtBackend.models.FavoriteEvent;
import org.springframework.data.repository.CrudRepository;

public interface FavoriteEventRepository extends CrudRepository<FavoriteEvent, Long> {
}
