package br.com.gerecia_estoque.shared.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {

    private int statusCode;
    private String error;
    private String message;
    private String path;
    private LocalDateTime timestamp;

}
