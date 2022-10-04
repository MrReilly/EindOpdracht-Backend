package com.example.EindOpdrachtBackend.repositories;

import com.example.EindOpdrachtBackend.models.Visitor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends CrudRepository<Visitor, Long> {
}
