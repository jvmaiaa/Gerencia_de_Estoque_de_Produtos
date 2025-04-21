package br.com.gerecia_estoque.modules.produto.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProdutoJpaRepository extends JpaRepository<ProdutoEntity, UUID> {

    Optional<ProdutoEntity> findByNome(String nome);
}
