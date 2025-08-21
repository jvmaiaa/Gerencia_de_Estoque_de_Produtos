package br.com.gerecia_estoque.modules.produto.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_produto")
@NoArgsConstructor
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private String descricao;

    private String codigoBarras;

    private Integer quantidadeEstoque;

    private BigDecimal preco;

    private LocalDateTime dataCadastro;

    private LocalDateTime dataUltimaAtualizacao;
}
