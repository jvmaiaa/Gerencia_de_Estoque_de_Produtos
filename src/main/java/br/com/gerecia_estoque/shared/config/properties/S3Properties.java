package br.com.gerecia_estoque.shared.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

@ConfigurationProperties(prefix = "aws.s3")
public record S3Properties(
        String bucketName,
        String accessKey,
        String secretKey,
        String region
) {
    public S3Properties {
        Objects.requireNonNull(bucketName, "aws.s3.bucket-name não pode ser nulo");
        Objects.requireNonNull(accessKey, "aws.s3.access-key não pode ser nulo");
        Objects.requireNonNull(secretKey, "aws.s3.secret-key não pode ser nulo");
        Objects.requireNonNull(region, "aws.s3.region não pode ser nulo");
    }
}
