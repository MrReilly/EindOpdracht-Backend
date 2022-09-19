package com.example.EindOpdrachtBackend.mapper;

import com.example.EindOpdrachtBackend.dto.EventDto;
import com.example.EindOpdrachtBackend.model.Event;

public class EventMapper {

    public EventDto toDto (Event entity){

        EventDto dto = new EventDto();

        dto.setId(entity.getId());
        dto.setOrganizer(entity.getOrganizer());
        dto.setReview(entity.getReview());
        dto.setVisitorsFavorites(entity.getVisitorsFavorites());
        dto.setCategory(entity.getCategory());
        dto.setOrganizationInfo(entity.getOrganizationInfo());
        dto.setName(entity.getName());
        dto.setLocation(entity.getLocation());
        dto.setAddress(entity.getAddress());
        dto.setLatCoordinate(entity.getLatCoordinate());
        dto.setLongCoordinate(entity.getLongCoordinate());
        dto.setEntryPrice(entity.getEntryPrice());
        dto.setTextDescription(entity.getTextDescription());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());

        return dto;
    }
    public Event toEntity (EventDto inputDto, Event event) {


        if (!event.getOrganizer().equals(inputDto.getOrganizer())) event.setOrganizer(inputDto.getOrganizer());
        if (!event.getReview().equals(inputDto.getReview())) event.setReview(inputDto.getReview());
        if (!event.getVisitorsFavorites().equals(inputDto.getVisitorsFavorites()))
            event.setVisitorsFavorites(inputDto.getVisitorsFavorites());
        if (!event.getCategory().equals(inputDto.getCategory())) event.setCategory(inputDto.getCategory());
        if (!event.getOrganizationInfo().equals(inputDto.getOrganizationInfo()))
            event.setOrganizationInfo(inputDto.getOrganizationInfo());
        if (!event.getName().equals(inputDto.getName())) event.setName(inputDto.getName());
        if (!event.getLocation().equals(inputDto.getLocation())) event.setLocation(inputDto.getLocation());
        if (!event.getAddress().equals(inputDto.getAddress())) event.setAddress(inputDto.getAddress());
        if (!event.getLatCoordinate().equals(inputDto.getLatCoordinate()))
            event.setLatCoordinate(inputDto.getLatCoordinate());
        if (!event.getLongCoordinate().equals(inputDto.getLongCoordinate()))
            event.setLongCoordinate(inputDto.getLongCoordinate());
        if (!event.getTextDescription().equals(inputDto.getTextDescription()))
            event.setTextDescription(inputDto.getTextDescription());
        if (!event.getStartDate().equals(inputDto.getStartDate())) event.setStartDate(inputDto.getStartDate());
        if (!event.getEndDate().equals(inputDto.getEndDate())) event.setEndDate(inputDto.getEndDate());


        return event;
    }

}
