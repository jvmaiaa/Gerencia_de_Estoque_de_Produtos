package br.com.gerecia_estoque.modules.produto.web.mapper;

import br.com.gerecia_estoque.modules.produto.domain.entity.ProdutoEntity;
import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoRequestDTO;
import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoResponseDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR) // ignora caso o Objeto de saída não tenha algum campo mapeado
public interface ProdutoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "dataUltimaAtualizacao", ignore = true)
    ProdutoEntity requestToEntity(ProdutoRequestDTO produtoRequestDTO);

    ProdutoResponseDTO entityToResponse(ProdutoEntity produtoEntity);

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "dataCadastro", ignore = true)
//    @Mapping(target = "dataAtualizacao", ignore = true)
//    ProdutoEntity requestDTOToDomain(ProdutoRequestDTO produtoRequestDTO);

    // informa ao mapper para ignorar campos nulos no DTO de requisição
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequestDTO(ProdutoRequestDTO produtoRequestDTO,
                                    @MappingTarget ProdutoEntity produtoEntity);
}
