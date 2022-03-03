package edu.tus.winemanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception Handler class for Resource Not Found Requests
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String message) {super(message);}
}
