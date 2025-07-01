package br.com.gerecia_estoque.modules.produto.infrastructure;

import br.com.gerecia_estoque.modules.produto.domain.entity.Produto;
import br.com.gerecia_estoque.modules.produto.domain.repository.ProdutoRepository;
import br.com.gerecia_estoque.modules.produto.interfaces.mapper.ProdutoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final ProdutoJpaRepository jpaRepository;
    private final ProdutoMapper produtoMapper;

    @Override
    public Optional<Produto> buscarPorNome(String nome) {
        return jpaRepository.findByNome(nome).map(ProdutoEntity::toDomain);
    }

    @Override
    public Produto salvar(Produto produto) {
        return jpaRepository.save(ProdutoEntity.fromDomain(produto)).toDomain();
    }
}
