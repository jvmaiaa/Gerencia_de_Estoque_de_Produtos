package br.com.gerecia_estoque.modules.produto.service.impl;

import br.com.gerecia_estoque.modules.produto.domain.entity.ProdutoEntity;
import br.com.gerecia_estoque.modules.produto.domain.exception.ProdutoExistsException;
import br.com.gerecia_estoque.modules.produto.domain.repository.ProdutoRepository;
import br.com.gerecia_estoque.modules.produto.service.ProdutoService;
import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoRequestDTO;
import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoResponseDTO;
import br.com.gerecia_estoque.modules.produto.web.mapper.ProdutoMapper;
import br.com.gerecia_estoque.shared.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static br.com.gerecia_estoque.shared.exceptions.ExceptionMessages.*;
import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class ProdutoServiceImpl implements ProdutoService {

    private static final String PRODUTOS_CACHE = "produtosCache";

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    @CacheEvict(cacheNames = PRODUTOS_CACHE, allEntries = true)
    public ProdutoResponseDTO save(ProdutoRequestDTO produtoRequestDTO) {
        ProdutoEntity produtoSaved = produtoMapper.requestToEntity(produtoRequestDTO);
        produtoSaved.setDataCadastro(LocalDateTime.now());

        produtoRepository.save(produtoSaved);

        return produtoMapper.entityToResponse(produtoSaved);
    }

    @Override
    @Cacheable(cacheNames = PRODUTOS_CACHE, cacheManager = "redisCacheManager")
    public List<ProdutoResponseDTO> findAll() {
        return produtoRepository.findAll()
                .stream()
                .map(produtoMapper::entityToResponse)
                .toList();
    }

    @Override
    public Page<ProdutoResponseDTO> findAllPaginated(Pageable pageable) {
        return produtoRepository.findAll(pageable).map(produtoMapper::entityToResponse);
    }

    @Override
    @Cacheable(cacheNames = PRODUTOS_CACHE, key = "#uuid")
    public ProdutoResponseDTO findById(UUID uuid) {
        ProdutoEntity produtoEntity = produtoRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(PRODUTO_NAO_ENCONTRADO));
        return produtoMapper.entityToResponse(produtoEntity);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = PRODUTOS_CACHE, key = "#uuid")
    public ProdutoResponseDTO update(UUID uuid, ProdutoRequestDTO produtoRequestDTO) {
        validaNomeECodigoDeBarras(produtoRequestDTO);

        ProdutoEntity produtoEntity = produtoRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(PRODUTO_NAO_ENCONTRADO));

        produtoMapper.updateEntityFromRequestDTO(produtoRequestDTO, produtoEntity);
        produtoEntity.setDataUltimaAtualizacao(LocalDateTime.now());
        produtoRepository.save(produtoEntity);

        return produtoMapper.entityToResponse(produtoEntity);
    }

    @Override
    @CacheEvict(cacheNames = PRODUTOS_CACHE, key = "#uuid")
    public void delete(UUID uuid) {
        ProdutoEntity produtoEntity = produtoRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(PRODUTO_NAO_ENCONTRADO));

        produtoRepository.deleteById(uuid);
    }

    private void validaNomeECodigoDeBarras(ProdutoRequestDTO produtoRequestDTO) {
        if (nonNull(produtoRequestDTO.getCodigoBarras())) {
            throw new ProdutoExistsException(CODIGO_BARRAS_NAO_PODE_SER_ALTERADO);
        }
        produtoRepository.findByNome(produtoRequestDTO.getNome()).ifPresent(produto -> {
            throw new ProdutoExistsException(String.format(PRODUTO_JA_EXISTE, produtoRequestDTO.getNome()));
        });
    }
    

}
