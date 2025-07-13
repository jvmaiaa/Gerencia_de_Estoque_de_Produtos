package br.com.gerecia_estoque.modules.produto.application.gateways;

import br.com.gerecia_estoque.modules.produto.application.entities.Produto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoGateway {

    Produto save(Produto produto);

    Optional<Produto> findById(UUID uuid);

    List<Produto> findAll(UUID uuid);

    void deleteById(UUID uuid);

    Optional<Produto> findByName(String nome);
}
