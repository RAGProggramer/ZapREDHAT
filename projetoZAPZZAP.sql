-- Crie o banco de dados Dovemif
CREATE DATABASE IF NOT EXISTS PortDoHomen;

-- Use o banco de dados Dovemif
USE PortDoHomen;

-- Crie a tabela usuário
CREATE TABLE IF NOT EXISTS Usuarios (
    usuario_id INT AUTO_INCREMENT PRIMARY KEY,
    tema VARCHAR(255),
    imagemPerfil LONGBLOB,
    login VARCHAR(15) NOT NULL,
    senha VARCHAR(25) NOT NULL,
    telefone VARCHAR(15),
    email VARCHAR(255),
    bio TEXT
);

-- Crie a tabela conversas
CREATE TABLE IF NOT EXISTS Conversas (
    conversa_id INT AUTO_INCREMENT PRIMARY KEY,
    usuario1_id INT,
    usuario2_id INT,
    apelido VARCHAR(255),
    FOREIGN KEY (usuario1_id) REFERENCES Usuarios(usuario_id),
    FOREIGN KEY (usuario2_id) REFERENCES Usuarios(usuario_id)
);

-- Crie a tabela mensagens
CREATE TABLE IF NOT EXISTS Mensagens (
    mensagem_id INT AUTO_INCREMENT PRIMARY KEY,
    data DATE,
    hora TIME,
    tipo VARCHAR(50),
    conteudo TEXT,
    status VARCHAR(50),
    conversa_id INT,
    remetente_id INT,
    FOREIGN KEY (conversa_id) REFERENCES Conversas(conversa_id),
    FOREIGN KEY (remetente_id) REFERENCES Usuarios(usuario_id)
);
