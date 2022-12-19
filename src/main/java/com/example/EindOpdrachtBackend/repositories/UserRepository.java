package com.example.EindOpdrachtBackend.repositories;

import com.example.EindOpdrachtBackend.models.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Qualifier("users")
@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
