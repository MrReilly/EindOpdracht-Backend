package com.example.EindOpdrachtBackend.repositories;

import com.example.EindOpdrachtBackend.models.Role;
import com.example.EindOpdrachtBackend.models.RoleOption;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Qualifier("roles")
@Repository
public interface RoleRepository extends CrudRepository<Role, RoleOption> {
}
