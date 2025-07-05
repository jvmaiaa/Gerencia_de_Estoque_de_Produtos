package br.com.gerecia_estoque.modules.produto.core.usecases.impl;

import br.com.gerecia_estoque.modules.produto.core.entities.Produto;
import br.com.gerecia_estoque.modules.produto.core.usecases.findByIdProdutoCase;

import java.util.UUID;

public class findAllProdutoCaseImpl implements findByIdProdutoCase {

    @Override
    public Produto execute(UUID uuid) {
        return null;
    }
}
