package br.com.gerecia_estoque.modules.produto.service;

import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoRequestDTO;
import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProdutoService {

    ProdutoResponseDTO save(ProdutoRequestDTO produtoRequestDTO);

    Page<ProdutoResponseDTO> findAllPaginated(Pageable pageable);

    ProdutoResponseDTO findById(UUID uuid);

    ProdutoResponseDTO update(UUID uuid, ProdutoRequestDTO produtoRequestDTO);

    void delete(UUID uuid);
}
