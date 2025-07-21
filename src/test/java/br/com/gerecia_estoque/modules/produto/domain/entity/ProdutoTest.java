package br.com.gerecia_estoque.modules.produto.domain.entity;

import br.com.gerecia_estoque.modules.produto.domain.exception.ProdutoEmptyException;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    public void constructor_ShouldReturnException_WhenNomeIsEmpty() {
        Produto produto = Instancio.create(Produto.class);
        produto.setNome("");

        ProdutoEmptyException ex = assertThrows(ProdutoEmptyException.class,
                () -> new Produto(
                        produto.getId(),
                        produto.getNome(),
                        produto.getDescricao(),
                        produto.getCodigoBarras(),
                        produto.getQuantidadeEstoque(),
                        produto.getPreco(),
                        produto.getDataCadastro(),
                        produto.getDataAtualizacao()
                ));

        assertEquals("Nome não pode ser vazio", ex.getMessage());
    }

    @Test
    public void constructor_ShouldReturnSucess_WhenNomeIsValid() {
        Produto produto = Instancio.create(Produto.class);

        assertDoesNotThrow(() -> new Produto(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getCodigoBarras(),
                produto.getQuantidadeEstoque(),
                produto.getPreco(),
                produto.getDataCadastro(),
                produto.getDataAtualizacao()
        ));
    }
}