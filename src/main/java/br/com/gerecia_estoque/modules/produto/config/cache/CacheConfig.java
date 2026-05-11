package br.com.gerecia_estoque.modules.produto.config.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfig {

    private static final String PRODUTOS_CACHE = "produtosCache";

    private final CacheProperties cacheProperties;

    @Bean
    @Primary
    public CacheManager caffeineCacheManager() {
        log.info("Inicializando CacheManager com configurações: {}", cacheProperties);

        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCacheNames(List.of(PRODUTOS_CACHE));
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .maximumSize(cacheProperties.maximumSize())
                .expireAfterWrite(cacheProperties.expireAfterMinutes(), TimeUnit.MINUTES)
                .recordStats());
        cacheManager.setAllowNullValues(false);

        return cacheManager;
    }

    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory,
                                          @Qualifier("redisObjectMapper") ObjectMapper redisObjectMapper) {

//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        objectMapper.activateDefaultTyping(
//                objectMapper.getPolymorphicTypeValidator(),
//                ObjectMapper.DefaultTyping.EVERYTHING,
//                JsonTypeInfo.As.PROPERTY
//        );

        GenericJackson2JsonRedisSerializer serializer =
                new GenericJackson2JsonRedisSerializer(redisObjectMapper);

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10))
                .disableCachingNullValues()
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair
                                .fromSerializer(serializer)
                );

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(config)
                .build();
    }

//    @Bean
//    @Qualifier("redisObjectMapper")
//    public ObjectMapper redisObjectMapper() {
//        PolymorphicTypeValidator typeValidator = BasicPolymorphicTypeValidator.builder()
//                .allowIfBaseType(Object.class)
//                .allowIfSubType("java.util")
//                .allowIfSubType("java.math")
//                .allowIfSubType("br.com.gerecia_estoque")
//                .build();
//
//        // ObjectMapper configurado com suporte a LocalDateTime
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        objectMapper.activateDefaultTyping(
//                typeValidator,
//                ObjectMapper.DefaultTyping.EVERYTHING,
//                JsonTypeInfo.As.WRAPPER_ARRAY
//        );
//        return objectMapper;
//    }

    @Bean
    @Qualifier("redisObjectMapper")
    public ObjectMapper redisObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

}
