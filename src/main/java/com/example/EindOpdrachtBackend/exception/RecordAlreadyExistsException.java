package com.example.EindOpdrachtBackend.exception;

import java.io.Serial;

public class RecordAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    public RecordAlreadyExistsException() {
        super();
    }
    public RecordAlreadyExistsException(String message) {
        super(message);
    }
}


