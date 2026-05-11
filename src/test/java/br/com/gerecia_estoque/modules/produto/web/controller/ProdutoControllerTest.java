package br.com.gerecia_estoque.modules.produto.web.controller;

import br.com.gerecia_estoque.modules.produto.service.ProdutoService;
import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoRequestDTO;
import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.instancio.Select.field;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class ProdutoControllerTest {

    @InjectMocks
    ProdutoController produtoController;

    @Mock
    ProdutoService produtoService;

    private ObjectMapper objectMapper;

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(produtoController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();

        objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void deveCriarProdutoERetornar201ComLocation() throws Exception {
        // Arrange
        UUID id = UUID.randomUUID();

        ProdutoRequestDTO request = Instancio.of(ProdutoRequestDTO.class)
                .set(field(ProdutoRequestDTO::getQuantidadeEstoque), 10)
                .create();

        ProdutoResponseDTO response = Instancio.of(ProdutoResponseDTO.class)
                .set(field(ProdutoResponseDTO::getId), id)
                .set(field(ProdutoResponseDTO::getNome), request.getNome())
                .set(field(ProdutoResponseDTO::getDescricao), request.getDescricao())
                .set(field(ProdutoResponseDTO::getCodigoBarras), request.getCodigoBarras())
                .set(field(ProdutoResponseDTO::getPreco), request.getPreco())
                .set(field(ProdutoResponseDTO::getQuantidadeEstoque), request.getQuantidadeEstoque())
                .create();

        // Act
        when(produtoService.save(any(ProdutoRequestDTO.class)))
                .thenReturn(response);

        // Assert
        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/produtos/" + id)))
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.nome").value(request.getNome()))
                .andExpect(jsonPath("$.descricao").value(request.getDescricao()))
                .andExpect(jsonPath("$.codigoBarras").value(request.getCodigoBarras()))
                .andExpect(jsonPath("$.preco").value(request.getPreco().doubleValue()))
                .andExpect(jsonPath("$.quantidadeEstoque").value(request.getQuantidadeEstoque()));
    }


    @Test
    void deveRetornarPaginaDeProdutosERetornar200QuandoExistiremProdutos() throws Exception {
        // Arrange
        List<ProdutoResponseDTO> produtos = Instancio.ofList(ProdutoResponseDTO.class)
                .size(3)
                .create();
        Page<ProdutoResponseDTO> page = new PageImpl<>(produtos, PageRequest.of(0, 10), produtos.size());

        when(produtoService.findAllPaginated(any(Pageable.class))).thenReturn(page);

        // Act & Assert
        mockMvc.perform(get("/produtos")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(3));
    }

    @Test
    void deveRetornar204QuandoNaoExistiremProdutos() throws Exception {
        // Arrange
        Page<ProdutoResponseDTO> pageVazia = Page.empty();
        when(produtoService.findAllPaginated(any(Pageable.class))).thenReturn(pageVazia);

        // Act & Assert
        mockMvc.perform(get("/produtos")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveRetornarProdutoERetornar200QuandoIdExistir() throws Exception {
        // Arrange
        UUID id = UUID.randomUUID();
        ProdutoResponseDTO response = Instancio.of(ProdutoResponseDTO.class)
                .set(field(ProdutoResponseDTO::getId), id)
                .create();
        when(produtoService.findById(id)).thenReturn(response);

        // Act & Assert
        mockMvc.perform(get("/produtos/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()));
    }

    @Test
    void deveAtualizarProdutoERetornar200QuandoIdExistir() throws Exception {
        // Arrange
        UUID id = UUID.randomUUID();
        ProdutoRequestDTO request = Instancio.of(ProdutoRequestDTO.class)
                .set(field(ProdutoRequestDTO::getQuantidadeEstoque), 5)
                .create();
        ProdutoResponseDTO response = Instancio.of(ProdutoResponseDTO.class)
                .set(field(ProdutoResponseDTO::getId), id)
                .create();
        when(produtoService.update(eq(id), any(ProdutoRequestDTO.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(put("/produtos/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()));
    }

    @Test
    void deveDeletarProdutoERetornar204QuandoIdExistir() throws Exception {
        // Arrange
        UUID id = UUID.randomUUID();
        doNothing().when(produtoService).delete(id);

        // Act & Assert
        mockMvc.perform(delete("/produtos/{id}", id))
                .andExpect(status().isNoContent());

        verify(produtoService, times(1)).delete(id);
    }

}