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

            if (event.getImageData() != null) {

                throw new RecordAlreadyExistsException("Event already has an image");
            }

                ImageData newImageData = mapper.toEntity(image, event);

                event.setImageData(newImageData);

                imageDataRepository.save(newImageData);
                eventRepository.save(event);

                return "file uploaded successfully : " + image.getOriginalFilename();
            }

            throw new RecordNotFoundException("Event was not found");
        }

        public Object downloadImage (Long imageId){

            Optional<ImageData> dbImageData = imageDataRepository.findById(imageId);
            if (dbImageData.isPresent()) {

                return dbImageData.get().getImage();
            }

            throw new RecordNotFoundException("image not in the database");
        }

        public  Object removeImage (Long eventId){

        Optional <Event> oe = eventRepository.findById(eventId);

            if (oe.isPresent()) {
                Event event = oe.get();

                ImageData image = event.getImageData();

                if (image == null) {
                    throw new RecordNotFoundException("This Event does not  have an Image yet");
                }

                Optional<ImageData> oi = imageDataRepository.findById(event.getImageData().getId());

                ImageData imageDataToRemove = null;

                if (oi.isPresent()) {

                    imageDataToRemove = oi.get();

                    imageDataToRemove.setEvent(null);
                }

                event.setImageData(null);

                if(imageDataToRemove == null){

                    throw new RecordNotFoundException("Image not in database");
                }

                imageDataRepository.delete(imageDataToRemove);

                eventRepository.save(event);
            }

            return "Image of Event " + eventId + " was removed  successfully";
        }

    }


