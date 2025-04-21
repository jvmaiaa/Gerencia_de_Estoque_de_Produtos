package br.com.gerecia_estoque.repository;

import br.com.gerecia_estoque.modules.produto.infrastructure.ProdutoEntity;
import br.com.gerecia_estoque.modules.produto.infrastructure.ProdutoJpaRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
@ComponentScan(basePackages = {
        "br.com.gerecia_estoque.modules.produto",
        "br.com.gerecia_estoque.modules.produto.infrastructure.persistence"
})
public class ProdutoJpaRepositoryTest {

    @Autowired
    private ProdutoJpaRepository produtoJpaRepository;

    @Autowired
    private EntityManager entityManager;
//
//    @Test
//    void deleteShouldDeleteObjectWhenIdExists() {
//        // Arrange
//        UUID existingId = UUID.randomUUID();
//
//        // Act
//        produtoRepository.deleteById(existingId);
//
//        // Assert
//        Optional<ProdutoEntity> result = produtoRepository.findById(existingId);
//        Assertions.assertFalse(result.isPresent());
//    }

//    @Test
//    void deleteShouldThrowEntityNotFoundExceptionWhenIdDoesNotExist() {
//        // Arrange
//        UUID nonExistingId = UUID.randomUUID();
//
//        // Act & Assert
//        Assertions.assertThrows(EntityNotFoundException.class, () -> {
//            produtoRepository.deleteById(nonExistingId);
//        });
//    }

    @Test
    @DisplayName("Should get ProdutoEntity successfully from DB")
    public void findByNome_ShouldReturnProdutoEntity_WhenNomeExists(){
        // Arrange
        String nome = "mouse";
        this.createProdutoEntity(nome);

        // Act
        Optional<ProdutoEntity> result = this.produtoJpaRepository.findByNome(nome);

        // Assert
        Assertions.assertTrue(result.isPresent());
        ProdutoEntity foundProduto = result.get();
        Assertions.assertEquals(nome, foundProduto.getNome());
    }

    @Test
    @DisplayName("Should not get ProdutoEntity from DB when ProdutoEntity does not exist")
    public void findByNome_ShouldReturnFail_WhenNomeNotExists(){
        // Arrange
        String nome = "mouse";

        // Act
        Optional<ProdutoEntity> result = this.produtoJpaRepository.findByNome(nome);

        // Assert
        Assertions.assertFalse(result.isPresent());
    }

    private ProdutoEntity createProdutoEntity(String nome) {
        ProdutoEntity produto = new ProdutoEntity();
        LocalDateTime fixedNow = LocalDateTime.of(
                2023,
                10,
                1,
                12,
                0
        );
//        produto.setId(UUID.randomUUID());
        produto.setNome(nome);
        produto.setDescricao("Descrição do Produto Teste");
        produto.setCodigoBarras("30948ahdfjkahfd");
        produto.setPreco(BigDecimal.valueOf(100.00));
        produto.setQuantidade(10);
        produto.setDataCadastro(fixedNow);
        produto.setDataAtualizacao(fixedNow);
        entityManager.persist(produto);
        entityManager.flush();
        return produto;
    }

}