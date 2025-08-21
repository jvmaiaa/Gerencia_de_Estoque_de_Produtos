package br.com.gerecia_estoque.modules.produto.web.controller;

import br.com.gerecia_estoque.modules.produto.service.ProdutoService;
import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoRequestDTO;
import br.com.gerecia_estoque.modules.produto.web.dtos.ProdutoResponseDTO;
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

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> create(@RequestBody @Valid ProdutoRequestDTO produto) {
        ProdutoResponseDTO produtoSaved = produtoService.saveProduto(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(produtoSaved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(produtoSaved);
    }
}
