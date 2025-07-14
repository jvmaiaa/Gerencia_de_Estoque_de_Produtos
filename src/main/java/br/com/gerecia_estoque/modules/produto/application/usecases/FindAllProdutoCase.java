package br.com.gerecia_estoque.modules.produto.application.usecases;

import br.com.gerecia_estoque.modules.produto.domain.entity.Produto;

import java.util.List;

public interface FindAllProdutoCase {
    List<Produto> execute();
}
