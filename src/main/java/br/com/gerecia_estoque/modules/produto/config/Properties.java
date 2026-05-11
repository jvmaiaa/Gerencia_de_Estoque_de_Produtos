package br.com.gerecia_estoque.modules.produto.config;

import br.com.gerecia_estoque.modules.produto.config.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        CacheProperties.class
})
public class Properties {
}
