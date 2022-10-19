package com.example.EindOpdrachtBackend.dtos;

import com.example.EindOpdrachtBackend.models.Event;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImageDataDto {

    private String name;
    private String type;
    private Event event;
    private byte[] image;

}
