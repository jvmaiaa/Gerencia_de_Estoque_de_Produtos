package br.com.gerecia_estoque.modules.produto.core.usecases;

import br.com.gerecia_estoque.modules.produto.core.entities.Produto;

public interface createProdutoCase {

    Produto execute(Produto produto);
}
