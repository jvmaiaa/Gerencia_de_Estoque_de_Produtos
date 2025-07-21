package br.com.gerecia_estoque.modules.produto.main.config;

import br.com.gerecia_estoque.modules.produto.application.gateways.ProdutoGateway;
import br.com.gerecia_estoque.modules.produto.application.usecases.CreateProdutoCase;
import br.com.gerecia_estoque.modules.produto.application.usecases.impl.CreateProdutoCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoConfig {

//    private final ProdutoGateway produtoGateway;
//
//    public ProdutoConfig(ProdutoGateway produtoGateway) {
//        this.produtoGateway = produtoGateway;
//    }

    /**
     * Creates a bean for the CreateProdutoCase use case.
     *
     * @param produtoGateway The gateway for produto operations
     * @return An implementation of CreateProdutoCase
     */
    @Bean // Qual implementação o Spring deve usar quando injetar CreateProdutoCase? nesse caso: CreateProdutoCaseImpl
    public CreateProdutoCase createProdutoCase(ProdutoGateway produtoGateway) {
        return new CreateProdutoCaseImpl(produtoGateway);
    }
}