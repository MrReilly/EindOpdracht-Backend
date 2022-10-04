package com.example.EindOpdrachtBackend.mappers;

import com.example.EindOpdrachtBackend.dtos.OrganizerGetDto;
import com.example.EindOpdrachtBackend.dtos.OrganizerPostDto;
import com.example.EindOpdrachtBackend.models.Organizer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrganizerMapper {

    private final ModelMapper mapper;

    public OrganizerMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public OrganizerGetDto toDto(Organizer organizer) {
        return mapper.map(organizer, OrganizerGetDto.class);
    }

    public Organizer toEntity(OrganizerPostDto dto) {
        return mapper.map(dto, Organizer.class);
    }

    public Organizer updateEntity(OrganizerPostDto dto, Organizer organizer){

         mapper.map(dto, organizer);
        return organizer;
    }
}
