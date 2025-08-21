package br.com.gerecia_estoque.modules.produto.web.mapper;

import br.com.gerecia_estoque.modules.produto.domain.entity.ProdutoEntity;
import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoRequestDTO;
import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR) // ignora caso o Objeto de saída não tenha algum campo mapeado
public interface ProdutoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "dataUltimaAtualizacao", ignore = true)
    ProdutoEntity requestToEntity(ProdutoRequestDTO produtoRequestDTO);

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "dataCadastro", ignore = true)
//    @Mapping(target = "dataAtualizacao", ignore = true)
//    ProdutoEntity requestDTOToDomain(ProdutoRequestDTO produtoRequestDTO);


    ProdutoResponseDTO entityToResponse(ProdutoEntity produtoEntity);
}
