package com.example.EindOpdrachtBackend.mappers;

import com.example.EindOpdrachtBackend.dtos.CategoryGetDto;
import com.example.EindOpdrachtBackend.dtos.EventGetDto;
import com.example.EindOpdrachtBackend.models.Category;
import com.example.EindOpdrachtBackend.models.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {
    EventMapper eventMapper;

    CategoryMapper(EventMapper eventMapper) {

        this.eventMapper = eventMapper;
    }

    public CategoryGetDto CategoryGetDto(Category category) {

        CategoryGetDto dto = new CategoryGetDto();

        List<Event> fullList = category.getEvents();

        List<EventGetDto> censoredEventList = new ArrayList<>();

        for (Event value : fullList) {
            EventGetDto event = eventMapper.toDto(value);
            censoredEventList.add(event);
        }

        dto.setEvents(censoredEventList);

        return dto;
    }

}
