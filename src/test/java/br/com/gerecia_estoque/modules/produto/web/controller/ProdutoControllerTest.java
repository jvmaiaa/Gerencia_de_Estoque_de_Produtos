package br.com.gerecia_estoque.modules.produto.web.controller;

import br.com.gerecia_estoque.modules.produto.service.ProdutoService;
import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoRequestDTO;
import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.instancio.Select.field;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
                .build();

        objectMapper = new ObjectMapper();
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
//                .andExpect(header().string("Location",
//                        "http://localhost/api/v1/produtos/" + id))
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.nome").value(request.getNome()))
                .andExpect(jsonPath("$.descricao").value(request.getDescricao()))
                .andExpect(jsonPath("$.codigoBarras").value(request.getCodigoBarras()))
                .andExpect(jsonPath("$.preco").value(request.getPreco().doubleValue()))
                .andExpect(jsonPath("$.quantidadeEstoque").value(request.getQuantidadeEstoque()));
    }

    @Test
    void deveRetornar400QuandoIdForNull() throws Exception {

        ProdutoRequestDTO request = Instancio.of(ProdutoRequestDTO.class)
                .set(field(ProdutoRequestDTO::getQuantidadeEstoque), 10)
                .create();

        ProdutoResponseDTO responseComIdNull = Instancio.of(ProdutoResponseDTO.class)
                .set(field(ProdutoResponseDTO::getId), null)
                .create();

        given(produtoService.save(any(ProdutoRequestDTO.class)))
                .willReturn(responseComIdNull);

        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""));
    }

}