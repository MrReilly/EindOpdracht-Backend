package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.dtos.VisitorGetDto;
import com.example.EindOpdrachtBackend.dtos.VisitorPostDto;
import com.example.EindOpdrachtBackend.mappers.VisitorMapper;
import com.example.EindOpdrachtBackend.models.Visitor;
import com.example.EindOpdrachtBackend.repositories.VisitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.EindOpdrachtBackend.validation.IdChecker.idChecker;

@Service
public class VisitorService {

    private final VisitorRepository repos;
    private final VisitorMapper mapper;

    public VisitorService(VisitorRepository repos, VisitorMapper mapper) {

        this.repos = repos;
        this.mapper = mapper;
    }

    public List<Visitor> getAllVisitors(){

        return (List<Visitor>) repos.findAll();
    }

    public Long createVisitor(VisitorPostDto dto) {

        Visitor newVisitor = mapper.toEntity(dto);

        repos.save(newVisitor);

        return newVisitor.getId();
    }

    public VisitorGetDto getVisitor (Long id){

        Visitor toGet = (Visitor) idChecker.checkID(id, repos);

        return mapper.toDto(toGet);
    }

    public VisitorGetDto updateVisitor(VisitorPostDto dto, Long id) {

        Visitor toUpdate = (Visitor) idChecker.checkID(id, repos);

        Visitor updated = mapper.updateEntity(dto, toUpdate);

        Visitor saved = this.repos.save(updated);

        return mapper.toDto(saved);
    }

    public Long deleteVisitor(Long id) {

        Visitor toDelete = (Visitor) idChecker.checkID(id, repos);

        this.repos.deleteById(toDelete.getId());

        return id;
    }

}
