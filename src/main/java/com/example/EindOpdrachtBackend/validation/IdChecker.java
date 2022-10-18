package com.example.EindOpdrachtBackend.validation;

import com.example.EindOpdrachtBackend.exception.RecordNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public class IdChecker {

    public Object checkID(Long id, CrudRepository<?,Long> repos) {

        if (repos.findById(id).isPresent()){
            return repos.findById(id).get();
        }

        else {
            throw new RecordNotFoundException("ID not found");
        }
    }
}
