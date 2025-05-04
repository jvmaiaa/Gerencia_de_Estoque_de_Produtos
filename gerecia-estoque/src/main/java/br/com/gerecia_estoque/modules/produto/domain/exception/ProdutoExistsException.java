package br.com.gerecia_estoque.modules.produto.domain.exception;

public class ProdutoExistsException extends RuntimeException {

    public ProdutoExistsException(String message) {
        super(message);
    }
}
