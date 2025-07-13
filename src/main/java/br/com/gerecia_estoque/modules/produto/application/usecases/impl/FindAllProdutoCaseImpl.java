package br.com.gerecia_estoque.modules.produto.application.usecases.impl;

import br.com.gerecia_estoque.modules.produto.application.entities.Produto;
import br.com.gerecia_estoque.modules.produto.application.usecases.FindByIdProdutoCase;

import java.util.UUID;

public class FindAllProdutoCaseImpl implements FindByIdProdutoCase {

    @Override
    public Produto execute(UUID uuid) {
        return null;
    }
}
