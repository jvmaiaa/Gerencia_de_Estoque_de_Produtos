package br.com.gerecia_estoque.modules.produto.web.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados de entrada para criar ou atualizar um produto")
public class ProdutoRequestDTO {

    @Schema(description = "Nome do produto", example = "Notebook Dell Inspiron 15", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "O nome do produto é obrigatório")
    private String nome;

    @Schema(description = "Descrição detalhada do produto", example = "Notebook com processador Intel i7, 16GB RAM e 512GB SSD")
    private String descricao;

    @Schema(description = "Código de barras do produto (EAN)", example = "7891234567890", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "O código de barras do produto é obrigatório")
    private String codigoBarras;

    @Schema(description = "Preço unitário do produto", example = "4599.99", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "O preço do produto não pode ser nulo")
    private BigDecimal preco;

    @Schema(description = "Quantidade disponível em estoque", example = "50", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "A quantidade em estoque do produto é obrigatória")
    @Min(value = 0, message = "Quantidade em estoque não pode ser negativa")
    private Integer quantidadeEstoque;

}
