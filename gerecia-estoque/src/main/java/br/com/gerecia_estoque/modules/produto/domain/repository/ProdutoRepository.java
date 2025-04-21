package br.com.gerecia_estoque.modules.produto.domain.repository;

import br.com.gerecia_estoque.modules.produto.domain.entity.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {

    Produto salvar(Produto produto);
    Optional<Produto> buscarPorNome(String nome);
    List<Produto> listar();
    Optional<Produto> buscarPorId(Long id);

}
