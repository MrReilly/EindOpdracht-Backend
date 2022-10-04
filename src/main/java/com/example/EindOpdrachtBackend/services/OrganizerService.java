package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.mappers.OrganizerMapper;
import com.example.EindOpdrachtBackend.dtos.OrganizerGetDto;
import com.example.EindOpdrachtBackend.dtos.OrganizerPostDto;
import com.example.EindOpdrachtBackend.models.Organizer;
import com.example.EindOpdrachtBackend.repositories.OrganizerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.EindOpdrachtBackend.validation.IdChecker.idChecker;

@Service
public class OrganizerService {

    private final OrganizerRepository repos;
    private final OrganizerMapper mapper;

    public OrganizerService(OrganizerRepository repos, OrganizerMapper mapper) {

        this.repos = repos;
        this.mapper = mapper;

    }

    public List<Organizer> getAllOrganizers(){

        return (List<Organizer>) repos.findAll();
    }

    public Long createOrganizer(OrganizerPostDto dto) {

        Organizer newOrganizer = mapper.toEntity(dto);

        repos.save(newOrganizer);

        return newOrganizer.getId();
    }

    public OrganizerGetDto getOrganizer (Long id){

        Organizer toGet = (Organizer) idChecker.checkID(id, repos);

        return mapper.toDto(toGet);
    }

    public OrganizerGetDto updateOrganizer(OrganizerPostDto dto, Long id) {

        Organizer toUpdate = (Organizer) idChecker.checkID(id, repos);

        Organizer updated = mapper.updateEntity(dto, toUpdate);

        Organizer saved = this.repos.save(updated);

        return mapper.toDto(saved);
    }

    public Long deleteOrganizer(Long id) {

        Organizer toDelete = (Organizer) idChecker.checkID(id, repos);

        this.repos.deleteById(toDelete.getId());

        return id;
    }

}



