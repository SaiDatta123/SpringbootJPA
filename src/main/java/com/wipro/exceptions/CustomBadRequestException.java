package com.wipro.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Setter
@Getter
public class CustomBadRequestException extends RuntimeException{

    private String message;

    public CustomBadRequestException(String message) {
        super(message);
    }

}
