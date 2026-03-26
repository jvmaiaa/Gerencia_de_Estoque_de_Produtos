package br.com.gerecia_estoque.modules.produto.config.aws;

import br.com.gerecia_estoque.shared.config.properties.S3Properties;
import br.com.gerecia_estoque.shared.exceptions.S3UploadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.util.Objects;

@Slf4j
@Configuration
@EnableConfigurationProperties(S3Properties.class)
public class S3Configuration {

    private final S3Properties s3Properties;
    private final S3Client s3Client;

    public S3Configuration(S3Properties s3Properties) {
        this.s3Properties = s3Properties;
        this.s3Client = initializeS3Client();
    }

    public String uploadFile(byte[] fileData, String fileName, String contentType) {
        Objects.requireNonNull(fileData, "Os dados do arquivo não podem ser nulos");
        Objects.requireNonNull(fileName, "O nome do arquivo não pode ser nulo");
        Objects.requireNonNull(contentType, "O tipo de conteúdo não pode ser nulo");

        if (fileData.length == 0) {
            throw new IllegalArgumentException("Os dados do arquivo não podem estar vazios");
        }

        try {
            log.debug("Enviando arquivo '{}' para o S3 com tipo de conteúdo: {}", fileName, contentType);

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(s3Properties.bucketName())
                    .key(fileName)
                    .contentType(contentType)
                    .build();

            PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromBytes(fileData));

            String fileUrl = buildFileUrl(fileName);
            log.info("Arquivo '{}' enviado com sucesso para o S3. ETag: {}", fileName, response.eTag());

            return fileUrl;
        } catch (Exception e) {
            log.error("Erro ao enviar arquivo '{}' para o S3", fileName, e);
            throw new S3UploadException(String.format("Falha ao fazer upload do arquivo '%s'", fileName), e);
        }
    }

    private String buildFileUrl(String fileName) {
        return String.format("https://%s.s3.%s.amazonaws.com/%s",
                s3Properties.bucketName(),
                s3Properties.region(),
                fileName);
    }

    public void close() {
        if (s3Client != null) {
            log.info("Fechando cliente S3");
            s3Client.close();
        }
    }

    private S3Client initializeS3Client() {
        log.info("Inicializando cliente S3 para a região: {} com bucket: {}",
                s3Properties.region(), s3Properties.bucketName());

        var credentials = AwsBasicCredentials.create(
                s3Properties.accessKey(),
                s3Properties.secretKey()
        );

        return S3Client.builder()
                .region(Region.of(s3Properties.region()))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

}
