package br.com.gerecia_estoque.modules.produto.infrastructure.presentation;

import br.com.gerecia_estoque.modules.produto.application.entities.Produto;
import br.com.gerecia_estoque.modules.produto.application.usecases.CreateProdutoCase;
import br.com.gerecia_estoque.modules.produto.infrastructure.dtos.ProdutoRequestDTO;
import br.com.gerecia_estoque.modules.produto.infrastructure.dtos.ProdutoResponseDTO;
import br.com.gerecia_estoque.modules.produto.infrastructure.mapper.ProdutoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final CreateProdutoCase createProdutoCase;
    private final ProdutoMapper produtoMapper;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> create(@RequestBody @Valid ProdutoRequestDTO produto) {
        Produto produtoDomain = produtoMapper.requestToDomain(produto);
        Produto produtoSaved = createProdutoCase.execute(produtoDomain);
        ProdutoResponseDTO produtoResponse = produtoMapper.domainToResponseDTO(produtoSaved);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(produtoSaved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(produtoResponse); // Define status 201
    }
}
