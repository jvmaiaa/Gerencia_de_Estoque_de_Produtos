package br.com.gerecia_estoque.modules.produto.service.impl;

import br.com.gerecia_estoque.modules.produto.domain.entity.ProdutoEntity;
import br.com.gerecia_estoque.modules.produto.domain.repository.ProdutoRepository;
import br.com.gerecia_estoque.modules.produto.service.ProdutoService;
import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoRequestDTO;
import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoResponseDTO;
import br.com.gerecia_estoque.modules.produto.web.mapper.ProdutoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoResponseDTO saveProduto(ProdutoRequestDTO produtoRequestDTO) {
        ProdutoEntity produtoSaved = produtoMapper.requestToEntity(produtoRequestDTO);
        produtoSaved.setDataCadastro(LocalDateTime.now());
        produtoRepository.save(produtoSaved);
        return produtoMapper.entityToResponse(produtoSaved);
    }
}
