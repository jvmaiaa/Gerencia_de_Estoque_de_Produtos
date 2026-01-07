package br.com.gerecia_estoque.shared.exceptions;

import br.com.gerecia_estoque.modules.produto.domain.exception.ProdutoEmptyException;
import br.com.gerecia_estoque.modules.produto.domain.exception.ProdutoExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex,
                                                                          HttpServletRequest request) {
        ApiErrorResponse error = new ApiErrorResponse(
                NOT_FOUND.value(),
                NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(NOT_FOUND).body(error);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<ApiErrorResponse> handleEntityInUseException(EntityInUseException ex,
                                                                       HttpServletRequest request) {
        ApiErrorResponse error = new ApiErrorResponse(
                CONFLICT.value(),
                CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ProdutoEmptyException.class)
    public ResponseEntity<ApiErrorResponse> handleProdutoEmptyException(ProdutoEmptyException ex,
                                                                        HttpServletRequest request) {
        ApiErrorResponse error = new ApiErrorResponse(
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ProdutoExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleProdutoExistsException(ProdutoExistsException ex,
                                                                              HttpServletRequest request) {
        ApiErrorResponse error = new ApiErrorResponse(
                CONFLICT.value(),
                CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(CONFLICT).body(error);
    }



}