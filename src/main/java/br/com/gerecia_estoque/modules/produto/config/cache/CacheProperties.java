package br.com.gerecia_estoque.modules.produto.config.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.cache.caffeine")
public record CacheProperties (
        long maximumSize,
        long expireAfterMinutes
){}