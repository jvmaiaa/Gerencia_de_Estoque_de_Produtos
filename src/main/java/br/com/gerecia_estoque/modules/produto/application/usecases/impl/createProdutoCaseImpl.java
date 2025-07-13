package br.com.gerecia_estoque.modules.produto.application.usecases.impl;

import br.com.gerecia_estoque.modules.produto.application.entities.Produto;
import br.com.gerecia_estoque.modules.produto.application.gateways.ProdutoGateway;
import br.com.gerecia_estoque.modules.produto.application.usecases.createProdutoCase;

public class createProdutoCaseImpl implements createProdutoCase {

    private final ProdutoGateway produtoGateway;

    public createProdutoCaseImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public Produto execute(Produto produto) {
//        produtoGateway.findByName(produto.getNome())
//                .ifPresent(item -> {
//                    throw new ProdutoExistsException("O produto com nome: " + produto.getNome() + " já existe.");
//                });
        return produtoGateway.save(produto);
    }
}
