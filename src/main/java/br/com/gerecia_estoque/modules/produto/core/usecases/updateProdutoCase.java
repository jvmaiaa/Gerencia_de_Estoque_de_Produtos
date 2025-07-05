package br.com.gerecia_estoque.modules.produto.core.usecases;

import br.com.gerecia_estoque.modules.produto.core.entities.Produto;

import java.util.UUID;

public interface updateProdutoCase {
    Produto execute(UUID uuid, Produto produto);
}
