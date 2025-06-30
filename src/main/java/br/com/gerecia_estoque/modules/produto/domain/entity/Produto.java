package br.com.gerecia_estoque.modules.produto.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Produto {
    private UUID id;
    private String nome;
    private String descricao;
    private String codigoBarras;
    private BigDecimal preco;
    private Integer quantidade;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;

    public Produto(String nome, String descricao, BigDecimal preco) {
    }
}
