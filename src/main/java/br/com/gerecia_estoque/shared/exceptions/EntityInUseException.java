package br.com.gerecia_estoque.shared.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class EntityInUseException extends DataIntegrityViolationException {

    public EntityInUseException(String message) {
        super(message);
    }
}
