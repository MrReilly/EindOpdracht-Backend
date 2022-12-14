package com.example.EindOpdrachtBackend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class EindOpdrachtBackendApplication {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setSkipNullEnabled(true);

        return modelMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(EindOpdrachtBackendApplication.class, args);
    }

}
