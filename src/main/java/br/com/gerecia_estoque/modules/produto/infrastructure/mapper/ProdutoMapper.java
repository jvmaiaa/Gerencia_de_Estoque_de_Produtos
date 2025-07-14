package br.com.gerecia_estoque.modules.produto.infrastructure.mapper;

import br.com.gerecia_estoque.modules.produto.domain.entity.Produto;
import br.com.gerecia_estoque.modules.produto.infrastructure.dtos.ProdutoRequestDTO;
import br.com.gerecia_estoque.modules.produto.infrastructure.dtos.ProdutoResponseDTO;
import br.com.gerecia_estoque.modules.produto.infrastructure.persistence.ProdutoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR) // dispara erro caso o Objeto de saída não tenha algum campo mapeado
public interface ProdutoMapper {

    ProdutoEntity domainToEntity(Produto produtoDomainObj);

    Produto entityToDomain(ProdutoEntity produtoEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    Produto requestDTOToDomain(ProdutoRequestDTO produtoRequestDTO);


    ProdutoResponseDTO domainToResponseDTO(Produto produtoDomainObj);
}
