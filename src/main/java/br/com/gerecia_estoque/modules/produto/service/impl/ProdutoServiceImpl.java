package br.com.gerecia_estoque.modules.produto.service.impl;

import static br.com.gerecia_estoque.shared.exceptions.ExceptionMessages.CODIGO_BARRAS_NAO_PODE_SER_ALTERADO;
import static br.com.gerecia_estoque.shared.exceptions.ExceptionMessages.PRODUTO_JA_EXISTE;
import static br.com.gerecia_estoque.shared.exceptions.ExceptionMessages.PRODUTO_NAO_ENCONTRADO;
import static java.util.Objects.nonNull;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gerecia_estoque.modules.produto.domain.entity.ProdutoEntity;
import br.com.gerecia_estoque.modules.produto.domain.exception.ProdutoExistsException;
import br.com.gerecia_estoque.modules.produto.domain.repository.ProdutoRepository;
import br.com.gerecia_estoque.modules.produto.service.ProdutoService;
import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoRequestDTO;
import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoResponseDTO;
import br.com.gerecia_estoque.modules.produto.web.mapper.ProdutoMapper;
import br.com.gerecia_estoque.shared.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoResponseDTO save(ProdutoRequestDTO produtoRequestDTO) {
        ProdutoEntity produtoSaved = produtoMapper.requestToEntity(produtoRequestDTO);
        produtoSaved.setDataCadastro(LocalDateTime.now());

        produtoRepository.save(produtoSaved);

        return produtoMapper.entityToResponse(produtoSaved);
    }

    @Override
    public Page<ProdutoResponseDTO> findAllPaginated(Pageable pageable) {
        return produtoRepository.findAll(pageable).map(produtoMapper::entityToResponse);
    }

    @Override
    public ProdutoResponseDTO findById(UUID uuid) {
        ProdutoEntity produtoEntity = produtoRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(PRODUTO_NAO_ENCONTRADO));
        return produtoMapper.entityToResponse(produtoEntity);
    }

    @Override
    @Transactional
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
