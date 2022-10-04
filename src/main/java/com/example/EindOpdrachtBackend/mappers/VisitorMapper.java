package com.example.EindOpdrachtBackend.mappers;

import com.example.EindOpdrachtBackend.dtos.VisitorGetDto;
import com.example.EindOpdrachtBackend.dtos.VisitorPostDto;
import com.example.EindOpdrachtBackend.models.Visitor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VisitorMapper {

        private final ModelMapper mapper;

        public VisitorMapper(ModelMapper mapper) {
            this.mapper = mapper;
        }

        public VisitorGetDto toDto(Visitor visitor) {
            return mapper.map(visitor, VisitorGetDto.class);
        }

        public Visitor toEntity(VisitorPostDto dto) {
            return mapper.map(dto, Visitor.class);
        }

        public Visitor updateEntity(VisitorPostDto dto, Visitor visitor){

            mapper.map(dto, visitor);
            return visitor;
        }
    }

