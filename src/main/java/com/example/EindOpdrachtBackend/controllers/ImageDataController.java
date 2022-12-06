package com.example.EindOpdrachtBackend.controllers;

import com.example.EindOpdrachtBackend.dtos.ImageGetDto;
import com.example.EindOpdrachtBackend.models.ImageData;
import com.example.EindOpdrachtBackend.services.ImageDataService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;


@RestController
public class ImageDataController {

    private final ImageDataService service;

    ImageDataController(ImageDataService service) {
        this.service = service;
    }

    @PostMapping("/image/{eventId}")
    public ResponseEntity<?> uploadImage(@Valid @PathVariable Long eventId, @RequestParam("image") MultipartFile image) throws IOException {

        ImageData uploadedImage = service.uploadImage(image, eventId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadedImage.getDownloadUri());
    }

    @GetMapping("/image/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) {

        ImageData downloadedImage = service.downloadImage(imageId);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.parseMediaType(downloadedImage.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "downloadedImage; name=\"" + downloadedImage.getName() + "\"")
                .body(new ByteArrayResource(downloadedImage.getImage()));
    }
}
