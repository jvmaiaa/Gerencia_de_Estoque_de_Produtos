package br.com.gerecia_estoque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EntityScan(basePackages = "br.com.gerecia_estoque.modules.produto.infrastructure.persistence")
//@EnableJpaRepositories(basePackages = "br.com.gerecia_estoque.modules.produto.infrastructure.persistence")
//@ComponentScan(basePackages = {
//		"br.com.gerecia_estoque.modules.produto"
//})
public class GerenciaEstoqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciaEstoqueApplication.class, args);
	}

}
