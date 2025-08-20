CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    login VARCHAR(60) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,  -- aumentado p/ armazenar hash de senha
    cpf VARCHAR(11) UNIQUE NOT NULL,
    telefone VARCHAR(11) NOT NULL,
    perfil VARCHAR(10) NOT NULL CHECK (
        perfil IN ('CLIENTE', 'GERENTE', 'ADMIN')
    ),
    status VARCHAR(10) NOT NULL CHECK (
        status IN (
            'ATIVO',
            'INATIVO',
            'PENDENTE',
            'BLOQUEADO'
        )
    )
);

CREATE TABLE administrador (
    id BIGINT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES usuario (id) ON DELETE CASCADE
);

CREATE TABLE gerente (
    id BIGINT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES usuario (id) ON DELETE CASCADE
);

CREATE TABLE endereco (
    id BIGSERIAL PRIMARY KEY,
    rua VARCHAR(100) NOT NULL,  
    bairro VARCHAR(60) NOT NULL,
    numero VARCHAR(10) NOT NULL,  
    complemento VARCHAR(100),
    cep VARCHAR(8) NOT NULL,
    cidade VARCHAR(60) NOT NULL,
    uf VARCHAR(2) NOT NULL
);

CREATE TABLE cliente (
    id BIGINT PRIMARY KEY,
    endereco_id BIGINT UNIQUE,
    salario DOUBLE PRECISION,
    FOREIGN KEY (id) REFERENCES usuario (id) ON DELETE CASCADE,
    FOREIGN KEY (endereco_id) REFERENCES endereco (id) ON DELETE SET NULL
);

CREATE TABLE conta (
    id BIGSERIAL PRIMARY KEY,
    numero_conta VARCHAR(20) UNIQUE NOT NULL,
    data_criacao DATE NOT NULL,
    limite DOUBLE PRECISION NOT NULL,
    status_conta VARCHAR(10) NOT NULL CHECK (
        status_conta IN (
            'APROVADA',
            'PENDENTE',
            'REJEITADA'
        )
    ),
    cliente_id BIGINT UNIQUE,
    gerente_id BIGINT,
    FOREIGN KEY (cliente_id) REFERENCES cliente (id) ON DELETE SET NULL,
    FOREIGN KEY (gerente_id) REFERENCES gerente (id) ON DELETE SET NULL
);

CREATE TABLE transacao (
    id BIGSERIAL PRIMARY KEY,
    data_hora TIMESTAMP NOT NULL,
    valor DOUBLE PRECISION NOT NULL,
    conta_destino VARCHAR(20),  -- Para transferências
    tipo VARCHAR(12) NOT NULL CHECK (
        tipo IN (
            'DEPOSITO',
            'SAQUE',
            'TRANSFERENCIA'
        )
    ),
    conta_id BIGINT NOT NULL,
    FOREIGN KEY (conta_id) REFERENCES conta (id) ON DELETE CASCADE
);

CREATE TABLE saldo (
    id BIGSERIAL PRIMARY KEY,
    data DATE NOT NULL,
    valor DOUBLE PRECISION NOT NULL,
    conta_id BIGINT NOT NULL,
    FOREIGN KEY (conta_id) REFERENCES conta (id) ON DELETE CASCADE
);