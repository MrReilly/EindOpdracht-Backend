package com.example.EindOpdrachtBackend.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImageGetDto {

    private String name;
    private String type;
    private String downloadUri;


}
