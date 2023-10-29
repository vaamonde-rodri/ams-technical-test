package es.amssolutions.technicaltest.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSimilarProductsFoundException extends RuntimeException {
    public NoSimilarProductsFoundException(String message) {
        super(message);
    }

}
