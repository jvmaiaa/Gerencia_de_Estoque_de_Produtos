package br.com.gerecia_estoque.shared.config.swagger.Interface;

import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoRequestDTO;
import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoResponseDTO;
import br.com.gerecia_estoque.shared.exceptions.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Tag(name = "Produtos", description = "Endpoints para gerenciamento de produtos no estoque")
public interface ProdutoControllerOpenApi {

    @Operation(summary = "Criar um novo produto", description = "Cria um novo produto e o adiciona ao estoque")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Produto já existente (código de barras duplicado)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<ProdutoResponseDTO> create(ProdutoRequestDTO produto);

    @Operation(summary = "Listar produtos paginados", description = "Retorna uma página com todos os produtos cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum produto encontrado")
    })
    ResponseEntity<Page<ProdutoResponseDTO>> getAllPaginated(Pageable pageable);

    @Operation(summary = "Listar todos os produtos", description = "Retorna uma lista com todos os produtos cadastrados sem paginação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum produto encontrado")
    })
    ResponseEntity<List<ProdutoResponseDTO>> getAll();

    @Operation(summary = "Buscar produto por ID", description = "Retorna os dados de um produto específico pelo seu UUID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<ProdutoResponseDTO> getById(
            @Parameter(description = "UUID do produto", required = true) UUID id);

    @Operation(summary = "Atualizar produto", description = "Atualiza os dados de um produto existente pelo seu UUID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<ProdutoResponseDTO> update(
            @Parameter(description = "UUID do produto", required = true) UUID id,
            ProdutoRequestDTO produtoRequestDTO);

    @Operation(summary = "Remover produto", description = "Remove um produto do estoque pelo seu UUID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Produto removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ResponseEntity<Void> delete(
            @Parameter(description = "UUID do produto", required = true) UUID id);

    @Operation(summary = "Upload de arquivo para o S3", description = "Realiza o upload de um arquivo para o bucket do Amazon S3")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Arquivo enviado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar upload do arquivo")
    })
    String criaBucket(
            @Parameter(description = "Arquivo a ser enviado", required = true) MultipartFile multipartFile) throws Exception;

}
