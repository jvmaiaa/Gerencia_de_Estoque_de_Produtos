package br.com.gerecia_estoque.modules.produto.domain.exception;

public class ProdutoJaExisteException extends RuntimeException {

    public ProdutoJaExisteException(String message) {
        super(message);
    }
}
