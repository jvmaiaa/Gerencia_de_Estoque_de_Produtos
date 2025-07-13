package br.com.gerecia_estoque.modules.produto.application.usecases;

import java.util.UUID;

public interface deleteByIdProdutoCase {

    void execute(UUID uuid);
}
