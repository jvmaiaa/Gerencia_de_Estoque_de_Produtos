CREATE TABLE tb_produto (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao TEXT,
    codigo_barras VARCHAR(50) NOT NULL UNIQUE,
    preco NUMERIC(19, 2) NOT NULL,
    quantidade_estoque INT NOT NULL CHECK ( quantidade_estoque >= 0 ),
    data_cadastro TIMESTAMP NOT NULL DEFAULT now(),
    data_atualizacao TIMESTAMP NOT NULL
);