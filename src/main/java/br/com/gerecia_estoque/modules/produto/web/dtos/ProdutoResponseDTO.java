package br.com.gerecia_estoque.modules.produto.web.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Dados de resposta de um produto")
public class ProdutoResponseDTO {

    @Schema(description = "Identificador único do produto", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @Schema(description = "Nome do produto", example = "Notebook Dell Inspiron 15")
    private String nome;

    @Schema(description = "Descrição detalhada do produto", example = "Notebook com processador Intel i7, 16GB RAM e 512GB SSD")
    private String descricao;

    @Schema(description = "Código de barras do produto (EAN)", example = "7891234567890")
    private String codigoBarras;

    @Schema(description = "Preço unitário do produto", example = "4599.99")
    private BigDecimal preco;

    @Schema(description = "Quantidade disponível em estoque", example = "50")
    private Integer quantidadeEstoque;

    @Schema(description = "Data e hora de cadastro do produto", example = "2026-01-15 10:30")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataCadastro;

    @Schema(description = "Data e hora da última atualização do produto", example = "2026-06-01 14:45")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataUltimaAtualizacao;
}
