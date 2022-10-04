package com.example.EindOpdrachtBackend.models;

import lombok.*;
import javax.persistence.*;

    @Getter
    @Setter
    @RequiredArgsConstructor

    @MappedSuperclass
    public abstract class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id")
        private Long id;
        private String username;
        private String password;

    }

