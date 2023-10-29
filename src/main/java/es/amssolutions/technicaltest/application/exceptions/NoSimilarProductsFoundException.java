package es.amssolutions.technicaltest.application.exceptions;

public class NoSimilarProductsFoundException extends RuntimeException {
    public NoSimilarProductsFoundException(String message) {
        super(message);
    }

}
