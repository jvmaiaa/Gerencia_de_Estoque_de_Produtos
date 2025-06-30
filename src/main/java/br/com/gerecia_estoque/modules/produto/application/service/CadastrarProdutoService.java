//package br.com.gerecia_estoque.modules.produto.application.service;
//
//import br.com.gerecia_estoque.modules.produto.domain.entity.Produto;
//import br.com.gerecia_estoque.modules.produto.domain.exception.ProdutoJaExisteException;
//import br.com.gerecia_estoque.modules.produto.domain.repository.ProdutoRepository;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//
//@Service
//public class CadastrarProdutoService {
//
//    private final ProdutoRepository produtoRepository;
//
//    public CadastrarProdutoService(ProdutoRepository produtoRepository) {
//        this.produtoRepository = produtoRepository;
//    }
//
//    public Produto executar(String nome, String descricao, BigDecimal preco) {
//        produtoRepository.buscarPorNome(nome).ifPresent(p -> {
//            throw new ProdutoJaExisteException(nome);
//        });
//
//        Produto novoProduto = new Produto(nome, descricao, preco);
//        return produtoRepository.salvar(novoProduto);
//    }
//}
