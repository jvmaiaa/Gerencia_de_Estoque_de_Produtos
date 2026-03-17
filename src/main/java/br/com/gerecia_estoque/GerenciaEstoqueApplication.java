package br.com.gerecia_estoque;

import br.com.gerecia_estoque.shared.config.properties.S3Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(S3Properties.class)
public class GerenciaEstoqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciaEstoqueApplication.class, args);
	}

}
