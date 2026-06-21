<p align="center">
  <h1 align="center">Gerência de Estoque de Produtos</h1>
</p>

<p align="center">
  <img alt="Java 21" src="https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white" />
  <img alt="Spring Boot 3.4.4" src="https://img.shields.io/badge/Spring_Boot-3.4.4-6DB33F?logo=springboot&logoColor=white" />
  <img alt="PostgreSQL 17" src="https://img.shields.io/badge/PostgreSQL-17-4169E1?logo=postgresql&logoColor=white" />
  <!-- <img alt="CI/CD" src="https://img.shields.io/github/actions/workflow/status/jvmaiaa/gerecia-estoque/dev.yml?branch=main&logo=github&label=CI%2FCD" /> -->
  <img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-green.svg" />
</p>

---

## Sobre o Projeto

Sistema de gerenciamento de estoque de produtos construído com **Java 21** e **Spring Boot 3.4.4**. Fornece uma API RESTful para operações de CRUD sobre produtos, com cache em duas camadas (Caffeine + Redis), upload de arquivos para o Amazon S3, documentação interativa via Swagger/OpenAPI e migrações de banco com Flyway.

**Autor:** [João Victor Maia](https://github.com/jvmaiaa)

---

## Stack Tecnológica

| Tecnologia | Versão | Finalidade |
|---|---|---|
| Java | 21 | Linguagem |
| Spring Boot | 3.4.4 | Framework |
| Spring Data JPA | (managed) | ORM / acesso a dados |
| Spring Web | (managed) | Endpoints REST |
| Spring HATEOAS | (managed) | Links HATEOAS |
| Spring Validation | (managed) | Bean Validation |
| Spring Boot Actuator | (managed) | Monitoramento |
| Spring Boot Cache | (managed) | Abstração de cache |
| Spring Data Redis | (managed) | Cache distribuído |
| PostgreSQL | 42.7.7 (driver) | Banco de produção |
| H2 | (managed, test) | Banco em memória para testes |
| Flyway | (managed) | Migrações de banco |
| Lombok | 1.18.36 | Redução de boilerplate |
| MapStruct | 1.5.5.Final | Mapeamento DTO ↔ Entity |
| AWS S3 SDK | 2.42.13 | Upload de arquivos |
| Caffeine | (managed) | Cache local em memória |
| Jackson JSR310 | (managed) | Serialização de datas Java 8 |
| SpringDoc OpenAPI | 2.8.6 | Swagger UI / docs da API |
| Instancio | 5.4.0 | Geração de dados aleatórios para testes |
| JaCoCo | 0.8.14 | Cobertura de testes |
| JUnit 5 + Mockito | (managed) | Testes |

---

## Arquitetura

O projeto segue o padrão **Modular Monolith** com arquitetura em camadas:

```
┌─────────────────────────────────────────────────────┐
│                   Controller (web)                   │
│   Desserializa request, chama service, serializa    │
│   response. Sem lógica de negócio.                  │
├─────────────────────────────────────────────────────┤
│                    Service (service)                 │
│   Regras de negócio. Sem lógica de infraestrutura.  │
├─────────────────────────────────────────────────────┤
│                  Repository (domain)                 │
│   Acesso a dados via JPA. Sem regras de negócio.    │
├─────────────────────────────────────────────────────┤
│                   Entity (domain)                    │
│   Estado e invariantes do domínio.                  │
└─────────────────────────────────────────────────────┘
         ↕                        ↕
    Shared (cross-cutting)    Config (infraestrutura)
    Exceções, Swagger,       Cache, S3, Properties
    Paginação, etc.
```

**Princípios adotados:**
- Composição > herança
- SOLID onde agrega valor — não como dogma
- Sem abstrações prematuras
- DTOs nunca viram entidades diretamente (sem `@RequestBody` na entidade)
- Validações de entrada no controller via Bean Validation (`@Valid`)
- Regras de negócio no service, nunca no controller

---

## Estrutura de Pacotes

```
src/main/java/br/com/gerecia_estoque/
├── GerenciaEstoqueApplication.java          # Entry point da aplicação
├── modules/
│   ├── produto/                             # Módulo de Produtos
│   │   ├── config/
│   │   │   ├── Properties.java              # Habilita CacheProperties
│   │   │   ├── aws/S3Configuration.java     # Cliente S3 + upload
│   │   │   ├── cache/CacheConfig.java       # Caffeine + Redis cache managers
│   │   │   ├── cache/CacheProperties.java   # Config do Caffeine (record)
│   │   │   └── security/SecurityConfig.java # Placeholder para segurança
│   │   ├── domain/
│   │   │   ├── entity/ProdutoEntity.java    # Entidade JPA (tb_produto)
│   │   │   ├── enums/TipoProduto.java       # Enum placeholder (vazio)
│   │   │   ├── exception/
│   │   │   │   ├── ProdutoEmptyException.java
│   │   │   │   └── ProdutoExistsException.java
│   │   │   └── repository/ProdutoRepository.java  # JpaRepository<ProdutoEntity, UUID>
│   │   ├── service/
│   │   │   ├── ProdutoService.java         # Interface do service
│   │   │   └── impl/ProdutoServiceImpl.java # Implementação com cache
│   │   └── web/
│   │       ├── controller/ProdutoController.java  # REST Controller
│   │       ├── dtos/
│   │       │   ├── ProdutoRequestDTO.java   # DTO de entrada
│   │       │   └── ProdutoResponseDTO.java  # DTO de saída
│   │       └── mapper/ProdutoMapper.java    # MapStruct interface
│   └── pagamento/                           # Módulo futuro (placeholder)
└── shared/                                  # Cross-cutting concerns
    ├── exceptions/
    │   ├── ApiErrorResponse.java            # DTO de erro padrão
    │   ├── EntityNotFoundException.java
    │   ├── EntityInUseException.java
    │   ├── ExceptionMessages.java           # Constantes de mensagem
    │   ├── GlobalExceptionHandler.java      # @RestControllerAdvice
    │   └── S3UploadException.java
    ├── config/
    │   ├── pagination/PageConfig.java
    │   ├── properties/S3Properties.java     # Propriedades S3
    │   └── swagger/
    │       ├── SwaggerConfiguration.java     # OpenAPI metadata
    │       └── Interface/ProdutoControllerOpenApi.java  # Contrato Swagger
    └── security/SecurityConfiguration.java  # Placeholder segurança
```

---

## Pré-requisitos

| Requisito | Versão | Obrigatório? |
|---|---|---|
| JDK (Temurin ou equivalente) | 21 | Sim |
| Maven (ou use o `mvnw` incluso) | 3.9+ | Sim |
| PostgreSQL | 17 | Sim (profile `dev`) |
| Redis | 7+ | Recomendado (cache distribuído) |
| Credenciais AWS S3 | — | Opcional (apenas para upload) |

---

## Instalação e Execução

### 1. Clone o repositório

```bash
git clone https://github.com/jvmaiaa/gerecia-estoque.git
```
```bash
cd Gerencia_de_Estoque_de_Produtos 
```

### 2. Configure as variáveis de ambiente

Crie um `.env` ou exporte as variáveis antes de rodar a aplicação com o profile `dev`:

```bash
export POSTGRES_USER=postgres
export POSTGRES_PASSWORD=root
export BUCKET_NAME=seu-bucket-s3          # opcional
export ACCESS_KEY=sua-access-key          # opcional
export SECRET_KEY=sua-secret-key          # opcional
```

> **Nota:** Se as variáveis não forem definidas, a aplicação usará os valores padrão definidos em `application-dev.yml` (`postgres`/`root` para o banco e valores placeholder para S3).

### 3. Suba o PostgreSQL e Redis

```bash
# PostgreSQL
docker run -d --name postgres -p 5432:5432 \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=root \
  -e POSTGRES_DB=gerencia_estoque \
  postgres:17

# Redis (opcional — sem ele, operações que usam redisCacheManager falharão)
docker run -d --name redis -p 6379:6379 redis:7
```

### 4. Build e execução

```bash
# Compilar
./mvnw clean compile -DskipTests

# Rodar testes
./mvnw test

# Rodar a aplicação (profile dev)
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

# Ou gerar o JAR e rodar diretamente
./mvnw clean package -DskipTests
java -jar target/gerecia-estoque-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

A aplicação sobe em: **http://localhost:8080/api/v1**

---

## Profiles Spring

| Profile | Banco | Flyway | Lazy Init | Uso |
|---|---|---|---|---|
| **dev** | PostgreSQL (`localhost:5432/gerencia_estoque`) | Habilitado | Sim | Desenvolvimento local |
| **test** | H2 em memória (`jdbc:h2:mem:testdb`) | Desabilitado | — | Execução de testes |
| **prod** | Não configurado (arquivo comentado) | — | — | A configurar |

> **Importante:** Para rodar os testes, o profile `test` é ativado automaticamente via `SPRING_PROFILES_ACTIVE=test` (configurado no CI e no `application-test.yml`).

---

## API Reference

### Base URL

```
http://localhost:8080/api/v1
```

### Endpoints de Produtos

| Método | Rota | Descrição | Status Codes |
|---|---|---|---|
| `POST` | `/produtos` | Cria um novo produto | `201` Created, `400` Bad Request, `409` Conflict |
| `GET` | `/produtos` | Lista produtos paginados | `200` OK, `204` No Content |
| `GET` | `/produtos/all` | Lista todos os produtos (sem paginação) | `200` OK, `204` No Content |
| `GET` | `/produtos/{id}` | Busca produto por UUID | `200` OK, `404` Not Found |
| `PUT` | `/produtos/{id}` | Atualiza produto por UUID | `200` OK, `400` Bad Request, `404` Not Found |
| `DELETE` | `/produtos/{id}` | Remove produto por UUID | `204` No Content, `404` Not Found |
| `POST` | `/produtos/cria-bucket` | Upload de arquivo para o S3 | `200` OK (retorna URL), `500` Internal Error |

### Exemplo — Criar Produto

**Request:**

```http
POST /api/v1/produtos
Content-Type: application/json

{
  "nome": "Notebook Dell",
  "descricao": "Notebook Dell Inspiron 15 8GB RAM",
  "codigoBarras": "7891234567890",
  "preco": 3499.99,
  "quantidadeEstoque": 25
}
```

**Response (201 Created):**

```json
{
  "id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
  "nome": "Notebook Dell",
  "descricao": "Notebook Dell Inspiron 15 8GB RAM",
  "codigoBarras": "7891234567890",
  "preco": 3499.99,
  "quantidadeEstoque": 25,
  "dataCadastro": "2026-06-21 14:30",
  "dataUltimaAtualizacao": null
}
```

> Header `Location` retornado: `/api/v1/produtos/{id}`

### Exemplo — Listar Paginado

```http
GET /api/v1/produtos?page=0&size=10&sort=nome,asc
```

Parâmetros de paginação (Spring `Pageable`):

| Parâmetro | Descrição | Default |
|---|---|---|
| `page` | Número da página (0-based) | `0` |
| `size` | Tamanho da página | `20` |
| `sort` | Campo,direção (ex: `nome,asc`) | — |

### Exemplo — Upload de Arquivo

```http
POST /api/v1/produtos/cria-bucket
Content-Type: multipart/form-data

file: <arquivo>
```

**Response (200 OK):**

```
https://nome_padrao.s3.us-east-1.amazonaws.com/nome-do-arquivo.pdf
```

> Limite máximo: **10MB** por arquivo (configurado em `application.yml`).

---

## DTOs

### ProdutoRequestDTO (entrada)

| Campo | Tipo | Validação | Descrição |
|---|---|---|---|
| `nome` | `String` | `@NotEmpty` | Nome do produto (único) |
| `descricao` | `String` | — | Descrição do produto |
| `codigoBarras` | `String` | `@NotEmpty` | Código de barras (EAN, único, imutável após criação) |
| `preco` | `BigDecimal` | `@NotNull` | Preço unitário |
| `quantidadeEstoque` | `Integer` | `@NotNull`, `@Min(0)` | Quantidade em estoque (não negativo) |

### ProdutoResponseDTO (saída)

| Campo | Tipo | Formato | Descrição |
|---|---|---|---|
| `id` | `UUID` | — | Identificador único |
| `nome` | `String` | — | Nome do produto |
| `descricao` | `String` | — | Descrição |
| `codigoBarras` | `String` | — | Código de barras |
| `preco` | `BigDecimal` | — | Preço unitário |
| `quantidadeEstoque` | `Integer` | — | Quantidade em estoque |
| `dataCadastro` | `LocalDateTime` | `yyyy-MM-dd HH:mm` | Data de criação |
| `dataUltimaAtualizacao` | `LocalDateTime` | `yyyy-MM-dd HH:mm` | Data da última atualização |

> Campos `null` são omitidos do JSON (`@JsonInclude(NON_NULL)`).

### ApiErrorResponse (erro)

| Campo | Tipo | Descrição |
|---|---|---|
| `statusCode` | `int` | Código HTTP do erro |
| `error` | `String` | Tipo do erro |
| `message` | `String` | Mensagem descritiva |
| `path` | `String` | Path da requisição |
| `timestamp` | `LocalDateTime` | Momento do erro |

---

## Cache

O projeto implementa uma **estratégia de cache em duas camadas**:

| Cache Manager | Tipo | TTL | Capacidade | Uso |
|---|---|---|---|---|
| **Caffeine** (`@Primary`) | Local (em memória) | 1 minuto | 1000 entradas | `findById()` — busca por ID |
| **Redis** | Distribuído | 10 minutos | — | `findAll()` — listagem completa |

### Comportamento por operação

| Operação | Cache | Comportamento |
|---|---|---|
| `findAll()` | Redis | `@Cacheable(cacheManager = "redisCacheManager")` — resultado em cache por 10min |
| `findAllPaginated()` | Nenhum | Sem cache — consulta direta ao banco |
| `findById()` | Caffeine | `@Cacheable(key = "#uuid")` — resultado em cache por 1min |
| `save()` | — | `@CacheEvict(allEntries = true)` — invalida todo o cache |
| `update()` | — | `@CacheEvict(key = "#uuid")` — invalida cache do produto atualizado |
| `delete()` | — | `@CacheEvict(key = "#uuid")` — invalida cache do produto removido |

> **Redis é obrigatório em produção** — sem ele, a operação `findAll()` falhará. Para rodar localmente sem Redis, comente a dependência `spring-boot-starter-data-redis` ou suba uma instância (veja seção de instalação).

---

## Banco de Dados

### PostgreSQL — Tabela `tb_produto`

Schema criado pela migration Flyway (`V1__create_table_produto.sql`):

```sql
CREATE TABLE tb_produto (
    id                    UUID PRIMARY KEY,
    nome                  VARCHAR(100) NOT NULL UNIQUE,
    descricao             TEXT,
    codigo_barras         VARCHAR(50) NOT NULL UNIQUE,
    preco                 NUMERIC(19, 2) NOT NULL,
    quantidade_estoque    INT NOT NULL CHECK (quantidade_estoque >= 0),
    data_cadastro         TIMESTAMP NOT NULL DEFAULT now(),
    data_atualizacao      TIMESTAMP
);
```

### Flyway

- **Versão atual:** `V1` (create table)
- **Localização:** `src/main/resources/db/migration/`
- **Profile dev:** Flyway habilitado — roda migrations automaticamente no boot
- **Profile test:** Flyway desabilitado — H2 cria o schema via `ddl-auto: create-drop`

> **Nota:** O `ddl-auto: update` (profile dev) e o Flyway operam juntos. O Flyway roda primeiro e gerencia o schema; o `ddl-auto` pode criar colunas extras se houver divergência entre entity e migration.

---

## Armazenamento S3

O upload de arquivos é feito via `S3Configuration`, que cria um cliente AWS S3 com credenciais estáticas:

```yaml
aws:
  s3:
    bucket-name: ${BUCKET_NAME:nome_padrao}
    access-key:  ${ACCESS_KEY:acess_padrao}
    secret-key:  ${SECRET_KEY:secret_padrao}
    region:      us-east-1
```

**Fluxo:**
1. `POST /produtos/cria-bucket` com `multipart/form-data` (campo `file`)
2. O controller repassa os bytes para `S3Configuration.uploadFile()`
3. O arquivo é enviado ao bucket S3 com a key = nome original do arquivo
4. Retorna a URL pública: `https://{bucket}.s3.{region}.amazonaws.com/{fileName}`

---

## Swagger / OpenAPI

A documentação interativa da API está disponível com a aplicação em execução:

| Recurso | URL |
|---|---|
| **Swagger UI** | [http://localhost:8080/api/v1/swagger-ui.html](http://localhost:8080/api/v1/swagger-ui.html) |
| **OpenAPI JSON** | [http://localhost:8080/api/v1/v3/api-docs](http://localhost:8080/api/v1/v3/api-docs) |

O controller implementa a interface `ProdutoControllerOpenApi`, que fornece anotações `@Operation`, `@ApiResponses` e `@Tag` para cada endpoint — garantindo documentação sempre atualizada.

---

## Actuator (Monitoramento)

Endpoints de monitoramento expostos:

| Endpoint | URL |
|---|---|
| Info | `GET /api/v1/actuator/info` |
| Caches | `GET /api/v1/actuator/caches` |
| Metrics | `GET /api/v1/actuator/metrics` |

---

## Testes

### Stack de Testes

| Ferramenta | Versão | Uso |
|---|---|---|
| JUnit 5 (Jupiter) | (managed) | Framework de testes |
| Mockito | (managed) | Mocks de dependências |
| Spring Test (`MockMvc`) | (managed) | Testes de controller |
| Instancio | 5.4.0 | Geração de dados aleatórios |
| H2 | (managed) | Banco em memória para testes de integração |
| JaCoCo | 0.8.14 | Relatório de cobertura |

### Comandos

```bash
# Rodar todos os testes
./mvnw test

# Rodar testes + relatório JaCoCo + empacotamento (fase verify completa)
./mvnw verify

# Relatório de cobertura (após verify)
# Abra: target/site/jacoco/index.html
```

### Testes Existentes

Os testes ativos estão em `ProdutoControllerTest` — testes unitários do controller com `MockMvc` standalone:

| Teste | Endpoint | Esperado |
|---|---|---|
| `deveCriarProdutoERetornar201ComLocation` | `POST /produtos` | 201 + header Location |
| `deveRetornarPaginaDeProdutosERetornar200QuandoExistiremProdutos` | `GET /produtos` | 200 com página |
| `deveRetornar204QuandoNaoExistiremProdutos` | `GET /produtos` | 204 No Content |
| `deveRetornarProdutoERetornar200QuandoIdExistir` | `GET /produtos/{id}` | 200 com produto |
| `deveAtualizarProdutoERetornar200QuandoIdExistir` | `PUT /produtos/{id}` | 200 com produto atualizado |
| `deveDeletarProdutoERetornar204QuandoIdExistir` | `DELETE /produtos/{id}` | 204 No Content |

### Cobertura (JaCoCo)

- **Meta:** 80% de cobertura de linhas e branches (thresholds definidos no `pom.xml`)
- **Exclusões:** `exception/`, `response/`, `enums/`, `config/`, `shared/`, `mapper/`, classe main
- **Status atual:** Os thresholds de 80% estão comentados no `pom.xml` — a pipeline não falha por cobertura baixa

### Convenções de Teste

- Padrão **AAA** obrigatório (Arrange / Act / Assert)
- Nomes descritivos: `deve_lancar_excecao_quando_estoque_insuficiente()`
- Mocks apenas em dependências externas (repositórios, clientes HTTP)
- Testes de integração: `@SpringBootTest` + banco real (Testcontainers recomendado)

---

## CI/CD (GitHub Actions)

A pipeline está definida em `.github/workflows/dev.yml`:

**Gatilhos:**
- `push` nas branches `main` e `feature/*`
- `pull_request` contra a branch `main`

**Etapas:**

```
Checkout → JDK 21 (Temurin) → Cache Maven (~/.m2) → Compile → Verify (testes + JaCoCo + package) → Upload .jar
```

- **Profile de teste:** `SPRING_PROFILES_ACTIVE=test` (H2 em memória)
- **Artefato:** o `.jar` gerado fica disponível para download por 7 dias
- **Se qualquer etapa falhar**, o Pull Request não poderá ser mergeado

---

## Convenções de Código

Resumo das regras definidas em `.github/instructions/architecture-instructions.md`:

- Métodos com responsabilidade única e curtos
- Nomes que dispensam comentário (comentário = falha de nomenclatura)
- Sem classes utilitárias gigantes — quebrar em componentes coesos
- Nullability sempre tratada: usar `Optional`, nunca retornar `null` de service
- Exceptions explícitas: criar exceções de domínio (ex: `ProdutoExistsException`)
- Records para DTOs imutáveis
- Sempre que uma nova feature for adicionada, adicionar testes correspondentes
- Evitar N+1: usar `JOIN FETCH` ou `@EntityGraph`
- Lazy loading por padrão — Eager só quando provado necessário
- Sempre paginar listagens (`Pageable`)
- Cache só com métrica que justifique — sem cache preventivo

---

## Tratamento de Erros

O `GlobalExceptionHandler` (`@RestControllerAdvice`) centraliza o tratamento de exceções:

| Exceção | HTTP Status | Quando ocorre |
|---|---|---|
| `EntityNotFoundException` | `404 NOT_FOUND` | Produto não encontrado por ID |
| `ProdutoExistsException` | `409 CONFLICT` | Nome duplicado ao criar/atualizar |
| `ProdutoEmptyException` | `400 BAD_REQUEST` | Lista de produtos vazia |
| `EntityInUseException` | `400 BAD_REQUEST` | Entidade com vínculo em uso |
| `S3UploadException` | `500` (não tratado pelo handler) | Falha no upload S3 |

### Mensagens de erro constantes

| Constante | Valor |
|---|---|
| `PRODUTO_JA_EXISTE` | `"Produto com nome %s ja existe"` |
| `PRODUTO_NAO_ENCONTRADO` | `"O produto nao foi encontrado"` |
| `CODIGO_BARRAS_NAO_PODE_SER_ALTERADO` | `"O produto nao pode ter o codigo de barras alterado"` |

---

## Módulos Futuros

### Pagamento (`modules/pagamento/`)

O diretório `modules/pagamento/` já existe como placeholder (contém apenas `.gitignore`). Este módulo está previsto para implementar funcionalidades de pagamento no futuro.

### Spring Security

As dependências `spring-boot-starter-security` e `spring-security-test` estão declaradas no `pom.xml` mas **comentadas**. As classes de configuração (`SecurityConfiguration` e `SecurityConfig`) existem como placeholders vazios. Quando a autenticação/autorização for necessária, basta descomentar as dependências e implementar as configurações.

---

## Mapeamento (MapStruct)

O `ProdutoMapper` é uma interface MapStruct com `componentModel = "spring"` (gerada como bean Spring):

| Método | Origem → Destino | Descrição |
|---|---|---|
| `requestToEntity(ProdutoRequestDTO)` | Request → Entity | Ignora `id`, `dataCadastro`, `dataUltimaAtualizacao` |
| `entityToResponse(ProdutoEntity)` | Entity → Response | Mapeamento completo |
| `updateEntityFromRequestDTO(ProdutoRequestDTO, @MappingTarget ProdutoEntity)` | Request → Entity (partial) | Ignora campos `null` (`nullValuePropertyMappingStrategy = IGNORE`), ignora `id`, `dataCadastro`, `dataUltimaAtualizacao` |

> A implementação é gerada automaticamente pelo MapStruct durante o build em `target/generated-sources/annotations/`.
