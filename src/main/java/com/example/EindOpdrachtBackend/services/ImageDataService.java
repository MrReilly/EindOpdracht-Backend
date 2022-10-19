package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.mappers.ImageDataMapper;
import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.models.ImageData;
import com.example.EindOpdrachtBackend.repositories.EventRepository;
import com.example.EindOpdrachtBackend.repositories.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {

    ImageRepository imageDataRepository;
    EventRepository eventRepository;
    ImageDataMapper mapper;

    ImageDataService(ImageRepository repos, EventRepository eventRepository, ImageDataMapper mapper){
        this.imageDataRepository = repos;
        this.eventRepository = eventRepository;
        this.mapper = mapper;
    }

    public String uploadImage(MultipartFile image, Long id) throws IOException {

        Optional<Event> oe = eventRepository.findById(id);

        if (oe.isPresent()) {

            Event event = oe.get();

            ImageData newImageData =  mapper.toEntity(image, event);

            event.setImageData(newImageData);

            imageDataRepository.save(newImageData);
            eventRepository.save(event);

            return "file uploaded successfully : " + image.getOriginalFilename();
        }

        return "Event was not found";
    }

        public Object downloadImage (Long imageId){

            Optional<ImageData> dbImageData = imageDataRepository.findById(imageId);
            if (dbImageData.isPresent()) {

                return dbImageData.get().getImage();
            }

            return "image not in the database";
        }
    }


