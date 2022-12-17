package com.example.EindOpdrachtBackend.mappers;

import com.example.EindOpdrachtBackend.models.ImageData;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Component
public class ImageDataMapper {


    public ImageData toEntity(MultipartFile image) throws IOException {
        String filename = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));

        ImageData imageData = new ImageData();

        imageData.setName(filename);
        imageData.setType(image.getContentType());
        imageData.setImage(image.getBytes());


        return imageData;

    }

}
