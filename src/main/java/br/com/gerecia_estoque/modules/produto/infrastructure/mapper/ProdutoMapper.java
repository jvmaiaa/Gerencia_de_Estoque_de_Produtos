package br.com.gerecia_estoque.modules.produto.infrastructure.mapper;

import br.com.gerecia_estoque.modules.produto.domain.entity.Produto;
import br.com.gerecia_estoque.modules.produto.infrastructure.dtos.ProdutoRequestDTO;
import br.com.gerecia_estoque.modules.produto.infrastructure.dtos.ProdutoResponseDTO;
import br.com.gerecia_estoque.modules.produto.infrastructure.persistence.ProdutoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoEntity domainToEntity(Produto produtoDomainObj);

    Produto entityToDomain(ProdutoEntity produtoEntity);

    Produto requestDTOToDomain(ProdutoRequestDTO produtoRequestDTO);

    ProdutoResponseDTO domainToResponseDTO(Produto produtoRequestDTO);
}
