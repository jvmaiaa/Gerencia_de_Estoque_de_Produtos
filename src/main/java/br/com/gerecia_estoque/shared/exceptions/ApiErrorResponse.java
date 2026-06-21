package br.com.gerecia_estoque.shared.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Resposta padrão de erro da API")
public class ApiErrorResponse {

    @Schema(description = "Código do status HTTP", example = "404")
    private int statusCode;

    @Schema(description = "Descrição do status HTTP", example = "Not Found")
    private String error;

    @Schema(description = "Mensagem detalhada do erro", example = "Produto não encontrado com o ID fornecido")
    private String message;

    @Schema(description = "Caminho da requisição que gerou o erro", example = "/api/v1/produtos/550e8400-e29b-41d4-a716-446655440000")
    private String path;

    @Schema(description = "Data e hora em que o erro ocorreu", example = "2026-06-06T10:30:00")
    private LocalDateTime timestamp;

}
