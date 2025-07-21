package br.com.gerecia_estoque.modules.produto.infrastructure.dtos;

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
public class ProdutoRequestDTO {

    @NotEmpty(message = "O nome do produto é obrigatório")
    private String nome;

    private String descricao;

    @NotEmpty(message = "O código de barras do produto é obrigatório")
    private String codigoBarras;

    @NotNull(message = "A quantidade em estoque do produto é obrigatória")
    @Min(value = 0, message = "Quantidade em estoque não pode ser negativa")
    private Integer quantidadeEstoque;

    @NotNull(message = "O preço do produto não pode ser nulo")
    private BigDecimal preco;

}