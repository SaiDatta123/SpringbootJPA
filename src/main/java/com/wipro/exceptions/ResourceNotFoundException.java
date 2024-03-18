package com.wipro.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException{

    private String message;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
