CREATE TABLE alunos(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    cpf VARCHAR(255),
    matricula VARCHAR(255) NOT NULL,
    curso VARCHAR(255),
    logradouro VARCHAR(255),
    numero VARCHAR(255),
    bairro VARCHAR(255),
    cidade VARCHAR(255),
    uf VARCHAR(2),
    cep VARCHAR(10),
    complemento VARCHAR(255)
);