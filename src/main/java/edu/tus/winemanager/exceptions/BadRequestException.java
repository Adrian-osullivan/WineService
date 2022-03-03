package edu.tus.winemanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception Handler class for Bad Requests
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception {
    public BadRequestException(String message)
    {
        super(message);
    }
}
