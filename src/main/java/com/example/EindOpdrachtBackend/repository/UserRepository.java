package com.example.EindOpdrachtBackend.repository;

import com.example.EindOpdrachtBackend.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepository extends CrudRepository<User, Long> {
}
