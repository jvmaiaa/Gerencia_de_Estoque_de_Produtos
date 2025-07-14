package br.com.gerecia_estoque.modules.produto.infrastructure.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseDTO {

    private UUID id;

    private String nome;

    private String descricao;

    private String codigoBarras;

    private Integer quantidadeEstoque;

    private Double preco;

    private LocalDateTime dataCadastro;

    private LocalDateTime dataAtualizacao;
}

