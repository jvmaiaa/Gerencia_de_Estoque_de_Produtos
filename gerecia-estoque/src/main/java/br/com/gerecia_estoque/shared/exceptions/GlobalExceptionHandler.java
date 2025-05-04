package br.com.gerecia_estoque.shared.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        ApiErrorResponse error = new ApiErrorResponse(
                NOT_FOUND.value(),
                ex.getMessage(),
                ex.getLocalizedMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(NOT_FOUND).body(error);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<ApiErrorResponse> handleEntityInUseException(EntityInUseException ex) {
        ApiErrorResponse error = new ApiErrorResponse(
                CONFLICT.value(),
                ex.getMessage(),
                ex.getLocalizedMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(BAD_REQUEST).body(error);
    }

}