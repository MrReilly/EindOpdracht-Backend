package com.example.EindOpdrachtBackend.mappers;

import com.example.EindOpdrachtBackend.dtos.EventGetDto;
import com.example.EindOpdrachtBackend.dtos.EventPostDto;
import com.example.EindOpdrachtBackend.models.Event;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

        private final ModelMapper mapper;

        public EventMapper(ModelMapper mapper) {
            this.mapper = mapper;
        }

        public EventGetDto toDto(Event event) {
            return mapper.map(event, EventGetDto.class);
        }

        public Event toEntity(EventPostDto dto) {
            return mapper.map(dto, Event.class);
        }

        public Event updateEntity(EventPostDto dto, Event event){

            mapper.map(dto, event);
            return event;
        }
    }

