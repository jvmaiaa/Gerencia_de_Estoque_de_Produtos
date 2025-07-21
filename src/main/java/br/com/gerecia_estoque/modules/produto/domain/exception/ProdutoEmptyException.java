package br.com.gerecia_estoque.modules.produto.domain.exception;

public class ProdutoEmptyException extends RuntimeException {

    public ProdutoEmptyException(String message) {
        super(message);
    }
}
