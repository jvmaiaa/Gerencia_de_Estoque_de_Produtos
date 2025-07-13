package br.com.gerecia_estoque.modules.produto.application.usecases;

import br.com.gerecia_estoque.modules.produto.application.entities.Produto;

import java.util.List;

public interface findAllProdutoCase {
    List<Produto> execute();
}
