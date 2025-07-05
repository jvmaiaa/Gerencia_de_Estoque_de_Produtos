package br.com.gerecia_estoque.modules.produto.core.usecases;

import br.com.gerecia_estoque.modules.produto.core.entities.Produto;

import java.util.List;

public interface findAllProdutoCase {
    List<Produto> execute();
}
