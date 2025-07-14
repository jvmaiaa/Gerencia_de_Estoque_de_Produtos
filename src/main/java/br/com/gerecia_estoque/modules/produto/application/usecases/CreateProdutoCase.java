package br.com.gerecia_estoque.modules.produto.application.usecases;

import br.com.gerecia_estoque.modules.produto.domain.entity.Produto;

public interface CreateProdutoCase {

    Produto execute(Produto produtoDomainObj);
}
