package com.example.EindOpdrachtBackend.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class StringBuilderValidation {

    public static StringBuilderValidation stringBuilder = new StringBuilderValidation();

    public ResponseEntity<Object> validation(BindingResult br) {

        StringBuilder sb = new StringBuilder();
        for (FieldError fe : br.getFieldErrors()) {
            sb.append(fe.getField()).append(": ");
            sb.append(fe.getDefaultMessage());
            sb.append("\n");
        }

        return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
    }
}
