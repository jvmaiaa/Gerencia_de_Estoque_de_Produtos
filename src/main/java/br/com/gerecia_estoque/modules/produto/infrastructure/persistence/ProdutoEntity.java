package br.com.gerecia_estoque.modules.produto.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private String descricao;

    private String codigoBarras;

    private BigDecimal preco;

    private Integer quantidadeEstoque;

    private LocalDateTime dataCadastro;

    private LocalDateTime dataAtualizacao;
    // poderia receber um ENUM da camada Application, já que a camada de infrastructure conhece a camada Application.
}
