package br.com.gerecia_estoque.modules.produto.core.usecases;

import br.com.gerecia_estoque.modules.produto.core.entities.Produto;

import java.util.UUID;

public interface findByIdProdutoCase {
    Produto execute(UUID uuid);
}
