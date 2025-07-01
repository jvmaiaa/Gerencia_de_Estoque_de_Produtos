package br.com.gerecia_estoque.modules.produto.core.usecases;

import br.com.gerecia_estoque.modules.produto.domain.entity.Produto;

public interface createProdutoCase {

    public Produto execute(Produto produto);
}
