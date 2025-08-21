package br.com.gerecia_estoque.modules.produto.domain.repository;

import br.com.gerecia_estoque.modules.produto.domain.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, UUID> {

    Optional<ProdutoEntity> findByNome(String nome);
}
