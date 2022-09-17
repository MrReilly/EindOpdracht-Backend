package com.example.EindOpdrachtBackend.repositories;

import com.example.EindOpdrachtBackend.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
