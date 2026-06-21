package br.com.gerecia_estoque.shared.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI gerenciaEstoqueOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gerência de Estoque de Produtos API")
                        .description("API REST para gerenciamento do estoque de produtos. "
                                + "Permite criar, consultar, atualizar e remover produtos, "
                                + "além de realizar upload de arquivos para o Amazon S3.")
                        .version("v1")
                        .contact(new Contact()
                                .name("João Victor Maia")
                                .url("https://github.com/jvmaiaa"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }

}
