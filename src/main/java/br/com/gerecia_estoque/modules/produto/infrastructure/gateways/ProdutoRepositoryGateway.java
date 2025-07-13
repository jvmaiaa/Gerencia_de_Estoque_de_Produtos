package br.com.gerecia_estoque.modules.produto.infrastructure.gateways;

import br.com.gerecia_estoque.modules.produto.application.entities.Produto;
import br.com.gerecia_estoque.modules.produto.application.gateways.ProdutoGateway;
import br.com.gerecia_estoque.modules.produto.infrastructure.mapper.ProdutoMapper;
import br.com.gerecia_estoque.modules.produto.infrastructure.persistence.ProdutoEntity;
import br.com.gerecia_estoque.modules.produto.infrastructure.persistence.ProdutoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProdutoRepositoryGateway implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoRepositoryGateway(ProdutoRepository produtoRepository,
                                    ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }


    @Override
    public Produto save(Produto produtoDomainObj) {
        ProdutoEntity produtoEntity = produtoRepository
                .save(produtoMapper.domainToEntity(produtoDomainObj));
        return produtoMapper.entityToDomain(produtoEntity);
    }

    @Override
    public Optional<Produto> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public List<Produto> findAll(UUID uuid) {
        return List.of();
    }

    @Override
    public Produto update(UUID uuid, Produto produtoDomainObj) {
        return null;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public Optional<Produto> findByName(String nome) {
        return Optional.empty();
    }
}
