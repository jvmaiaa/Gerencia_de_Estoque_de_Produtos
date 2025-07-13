package br.com.gerecia_estoque.modules.produto.application.usecases;

import java.util.UUID;

public interface DeleteByIdProdutoCase {

    void execute(UUID uuid);
}
