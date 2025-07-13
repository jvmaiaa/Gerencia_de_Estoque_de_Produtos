package br.com.gerecia_estoque.modules.produto.application.usecases;

import br.com.gerecia_estoque.modules.produto.application.entities.Produto;

import java.util.UUID;

public interface updateProdutoCase {
    Produto execute(UUID uuid, Produto produtoDomainObj);
}
