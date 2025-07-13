package br.com.gerecia_estoque.modules.produto.application.usecases;

import br.com.gerecia_estoque.modules.produto.application.entities.Produto;

public interface createProdutoCase {

    Produto execute(Produto produto);
}
