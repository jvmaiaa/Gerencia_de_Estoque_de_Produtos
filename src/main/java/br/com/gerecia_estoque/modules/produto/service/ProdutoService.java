package br.com.gerecia_estoque.modules.produto.service;

import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoRequestDTO;
import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoResponseDTO;

public interface ProdutoService {

    ProdutoResponseDTO saveProduto(ProdutoRequestDTO produtoRequestDTO);
}
