package com.example.EindOpdrachtBackend.mappers;

import com.example.EindOpdrachtBackend.models.Event;
import com.example.EindOpdrachtBackend.models.ImageData;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ImageDataMapper {


    public ImageData toEntity(MultipartFile image, Event event) throws IOException {

        ImageData imageData = new ImageData();

        imageData.setEvent(event);
        imageData.setName(image.getName());
        imageData.setType(image.getContentType());
        imageData.setImage(image.getBytes());

        return imageData;

    }

}
