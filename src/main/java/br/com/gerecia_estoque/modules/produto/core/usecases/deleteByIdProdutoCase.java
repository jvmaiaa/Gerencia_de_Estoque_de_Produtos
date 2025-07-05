package br.com.gerecia_estoque.modules.produto.core.usecases;

import java.util.UUID;

public interface deleteByIdProdutoCase {

    void execute(UUID uuid);
}
