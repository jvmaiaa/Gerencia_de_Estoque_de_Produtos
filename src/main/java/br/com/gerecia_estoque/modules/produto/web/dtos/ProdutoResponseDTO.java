package br.com.gerecia_estoque.modules.produto.web.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class ProdutoResponseDTO {

    private UUID id;

    private String nome;

    private String descricao;

    private String codigoBarras;

    private Integer quantidadeEstoque;

    private BigDecimal preco;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataCadastro;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataUltimaAtualizacao;
}

