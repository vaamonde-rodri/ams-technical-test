package es.amssolutions.technicaltest.application.exceptions;

import es.amssolutions.technicaltest.application.models.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerExceptionHandlerTest {

    @InjectMocks
    private ControllerExceptionHandler controllerExceptionHandler;

    @BeforeEach
    public void setUp() {
        controllerExceptionHandler = new ControllerExceptionHandler();
    }

    @Test
    public void handleGenericException_shouldReturnInternalServerError() {
        RuntimeException e = new RuntimeException("Error genérico");

        ResponseEntity<ErrorResponse> responseEntity = controllerExceptionHandler.handleGenericException(e);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Error genérico", responseEntity.getBody().getMessage());
    }

    @Test
    public void handleNoSimilarProductsFoundException_shouldReturnNotFound() {
        NoSimilarProductsFoundException e = new NoSimilarProductsFoundException("No se encontraron productos similares");

        ResponseEntity<ErrorResponse> responseEntity = controllerExceptionHandler.handleNoSimilarProductsFoundException(e);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No se encontraron productos similares", responseEntity.getBody().getMessage());
    }
}
