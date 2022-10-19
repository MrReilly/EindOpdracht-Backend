package com.example.EindOpdrachtBackend.controllers;

import com.example.EindOpdrachtBackend.services.ImageDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
public class ImageDataController {

    private final ImageDataService service;

    ImageDataController(ImageDataService service){
        this.service = service;
    }

    @PostMapping("/image/{eventId}")
    public ResponseEntity<?> uploadImage(@Valid @PathVariable Long eventId, @RequestParam("image")MultipartFile image) throws IOException {

        String uploadImage = service.uploadImage(image, eventId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/image/{imageId}")
    public ResponseEntity<?> downloadImage(@PathVariable Long imageId){

        byte[] image = (byte[])service.downloadImage(imageId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);

    }

}
