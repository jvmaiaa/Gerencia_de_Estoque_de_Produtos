# Projeto Backend Java/Spring

## Stack
- Java 21
- Spring Boot 3.x
- Spring Data JPA + Hibernate
- PostgreSQL 17
- Docker + Docker Compose
- Maven

## Arquitetura

Camadas e responsabilidades:
- Controller: desserializa request, chama service, serializa response. Sem lógica.
- Service: regras de negócio. Sem lógica de infraestrutura.
- Repository: acesso a dados. Sem regras de negócio.
- Domain/Entity: estado e invariantes do domínio.

Regras:
- Composição > herança
- SOLID onde agrega valor — não como dogma
- Sem abstrações prematuras
- DTOs nunca viram entidades diretamente (sem @RequestBody na entidade)
- Validações de entrada no controller via Bean Validation (@Valid)
- Regras de negócio no service, nunca no controller

## Padrões de código

- Métodos com responsabilidade única e curtos
- Nomes que dispensam comentário (comentário = falha de nomenclatura)
- Sem classes utilitárias gigantes — quebrar em componentes coesos
- Sem programação funcional excessiva (streams aninhados, lambdas complexas)
- Nullability sempre tratada: usar Optional, nunca retornar null de service
- Exceptions explícitas: criar exceções de domínio (ex: EstoqueNotFoundException)
- Sem checked exceptions desnecessárias
- Records para DTOs imutáveis
- Sempre que uma nova feature for adicionada, adicionar testes correspondentes

## Banco e performance

- Evitar N+1: usar JOIN FETCH ou @EntityGraph explicitamente
- Lazy loading por padrão — Eager só quando provado necessário
- Sempre paginar listagens (Pageable)
- Queries complexas: JPQL ou @Query, nunca lógica em memória
- Índices nas colunas usadas em WHERE/JOIN frequentes
- Cache só com métrica que justifique — sem cache preventivo
- Considerar custo de memória em projeções: usar interfaces ou records como projection

## Testes

Padrão AAA obrigatório (Arrange / Act / Assert).

- Testar comportamento, não implementação
- Mocks apenas em dependências externas (repositórios, clientes HTTP)
- Sem mocks de classes de domínio ou services internos
- Nomes descritivos: `deve_lancar_excecao_quando_estoque_insuficiente()`
- Testes de integração com @SpringBootTest + banco real (Testcontainers)
- Evitar @InjectMocks em excesso — preferir instanciação direta quando possível

## Estilo de resposta

Direto. Técnico. Sem rodeios.
Sem artigos desnecessários, sem elogios, sem hedging.
Fragmentos são válidos. Explicações curtas e precisas.
Código sem alteração.
Formato padrão: [problema] → [causa] → [solução].