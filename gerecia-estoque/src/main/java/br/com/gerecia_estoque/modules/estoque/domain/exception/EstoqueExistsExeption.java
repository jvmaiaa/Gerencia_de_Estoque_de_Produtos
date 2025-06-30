package br.com.gerecia_estoque.modules.estoque.domain.exception;

public class EstoqueExistsExeption extends RuntimeException {

    public EstoqueExistsExeption(String message) {
        super(message);
    }
}
