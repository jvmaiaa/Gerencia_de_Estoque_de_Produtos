package br.com.gerecia_estoque.modules.produto.application.usecases.impl;

import br.com.gerecia_estoque.modules.produto.application.gateways.ProdutoGateway;
import br.com.gerecia_estoque.modules.produto.application.usecases.CreateProdutoCase;
import br.com.gerecia_estoque.modules.produto.domain.entity.Produto;
import org.springframework.stereotype.Service;

@Service
public class CreateProdutoCaseImpl implements CreateProdutoCase {

    private final ProdutoGateway produtoGateway;

    public CreateProdutoCaseImpl(ProdutoGateway produtoGateway) {
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
