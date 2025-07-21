package br.com.gerecia_estoque.modules.produto.application.gateways;

import br.com.gerecia_estoque.modules.produto.domain.entity.Produto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoGateway {

    Produto save(Produto produtoDomainObj);

    Optional<Produto> findById(UUID uuid);

    List<Produto> findAll();

    Produto update(UUID uuid, Produto produtoDomainObj);

    void deleteById(UUID uuid);

//    Optional<Produto> findByName(String nome);
}
