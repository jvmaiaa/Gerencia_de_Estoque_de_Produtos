package br.com.gerecia_estoque.modules.produto.domain.entity;

import br.com.gerecia_estoque.modules.produto.domain.exception.ProdutoEmptyException;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoEntityTest {

//    @Test
//    public void constructor_ShouldReturnException_WhenNomeIsEmpty() {
//        ProdutoEntity produtoEntity = Instancio.create(ProdutoEntity.class);
//        produtoEntity.setNome("");
//
//        ProdutoEmptyException ex = assertThrows(ProdutoEmptyException.class,
//                () -> new ProdutoEntity(
//                        produtoEntity.getId(),
//                        produtoEntity.getNome(),
//                        produtoEntity.getDescricao(),
//                        produtoEntity.getCodigoBarras(),
//                        produtoEntity.getQuantidadeEstoque(),
//                        produtoEntity.getPreco(),
//                        produtoEntity.getDataCadastro(),
//                        produtoEntity.getDataAtualizacao()
//                ));
//
//        assertEquals("Nome não pode ser vazio", ex.getMessage());
//    }
//
//    @Test
//    public void constructor_ShouldReturnSucess_WhenNomeIsValid() {
//        ProdutoEntity produtoEntity = Instancio.create(ProdutoEntity.class);
//
//        assertDoesNotThrow(() -> new ProdutoEntity(
//                produtoEntity.getId(),
//                produtoEntity.getNome(),
//                produtoEntity.getDescricao(),
//                produtoEntity.getCodigoBarras(),
//                produtoEntity.getQuantidadeEstoque(),
//                produtoEntity.getPreco(),
//                produtoEntity.getDataCadastro(),
//                produtoEntity.getDataAtualizacao()
//        ));
//
//        // Verifica se os campos não são nulos
//        assertNotNull(produtoEntity.getId());
//        assertNotNull(produtoEntity.getNome());
//        assertNotNull(produtoEntity.getDescricao());
//        assertNotNull(produtoEntity.getCodigoBarras());
//        assertNotNull(produtoEntity.getQuantidadeEstoque());
//        assertNotNull(produtoEntity.getPreco());
//        assertNotNull(produtoEntity.getDataCadastro());
//        assertNotNull(produtoEntity.getDataAtualizacao());
//    }
}