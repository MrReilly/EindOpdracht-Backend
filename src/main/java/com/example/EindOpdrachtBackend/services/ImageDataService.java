package com.example.EindOpdrachtBackend.services;

import com.example.EindOpdrachtBackend.exception.RecordAlreadyExistsException;
import com.example.EindOpdrachtBackend.exception.RecordNotFoundException;
import com.example.EindOpdrachtBackend.mappers.ImageDataMapper;
import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.models.ImageData;
import com.example.EindOpdrachtBackend.repositories.EventRepository;
import com.example.EindOpdrachtBackend.repositories.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {

    ImageRepository imageDataRepository;
    EventRepository eventRepository;
    ImageDataMapper mapper;

    ImageDataService(ImageRepository repos, EventRepository eventRepository, ImageDataMapper mapper) {
        this.imageDataRepository = repos;
        this.eventRepository = eventRepository;
        this.mapper = mapper;
    }

    public ImageData uploadImage(MultipartFile image, Long id) throws IOException {

        Optional<Event> oe = eventRepository.findById(id);

        if (oe.isPresent()) {

            Event event = oe.get();

            if (event.getImageData() != null) {

                throw new RecordAlreadyExistsException("Event already has an image");
            }

            ImageData newImageData = mapper.toEntity(image);

            ImageData saved = imageDataRepository.save(newImageData);

            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/image/")
                    .path(saved.getId().toString())
                    .toUriString();

            saved.setDownloadUri(downloadUrl);
            imageDataRepository.save(saved);

            event.setImageData(saved);
            eventRepository.save(event);

            return saved;
        }

        throw new RecordNotFoundException("Event was not found");
    }

    public ImageData downloadImage(Long imageId) {

        Optional<ImageData> dbImageData = imageDataRepository.findById(imageId);
        if (dbImageData.isPresent()) {

            return dbImageData.get();
        }

        throw new RecordNotFoundException("image not in the database");
    }

}


