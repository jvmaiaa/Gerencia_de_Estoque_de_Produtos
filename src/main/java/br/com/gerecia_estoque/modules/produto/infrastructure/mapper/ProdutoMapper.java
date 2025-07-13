package br.com.gerecia_estoque.modules.produto.infrastructure.mapper;

import br.com.gerecia_estoque.modules.produto.application.entities.Produto;
import br.com.gerecia_estoque.modules.produto.infrastructure.dtos.ProdutoRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    Produto toEntity(ProdutoRequestDTO produtoRequestDTO);
}
