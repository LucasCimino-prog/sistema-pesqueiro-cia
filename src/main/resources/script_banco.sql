-- 1. CRIAÇÃO DO BANCO DE DADOS
CREATE DATABASE IF NOT EXISTS pesqueiro_cia;
USE pesqueiro_cia;

-- 2. TABELA DE FORNECEDORES
-- Armazena o CNPJ, nome fantasia e o contato do representante.
CREATE TABLE IF NOT EXISTS fornecedor (
    cnpj VARCHAR(20) PRIMARY KEY,
    nome_fantasia VARCHAR(100) NOT NULL,
    contato VARCHAR(50)
    );

-- 3. TABELA DE CLIENTES
-- Registra CPF, nome, e-mail e o histórico de pontos de fidelidade.
CREATE TABLE IF NOT EXISTS cliente (
    cpf VARCHAR(14) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    pontos_fidelidade INT DEFAULT 0
    );

-- 4. TABELA DE FUNCIONÁRIOS
-- Controla a matrícula, cargo e percentual de comissão sobre as vendas.
CREATE TABLE IF NOT EXISTS funcionario (
    cpf VARCHAR(14) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    matricula VARCHAR(20) UNIQUE,
    cargo VARCHAR(50),
    percentual_vendas DOUBLE
    );

-- 5. TABELA DE PRODUTOS
-- Possui código, descrição, preços, estoque e vínculo com fornecedor.
CREATE TABLE IF NOT EXISTS produtos (
    codigo VARCHAR(20) PRIMARY KEY,
    descricao VARCHAR(150) NOT NULL,
    preco_custo DOUBLE NOT NULL,
    preco_venda DOUBLE NOT NULL,
    estoque INT NOT NULL,
    estoque_minimo INT NOT NULL,
    cnpj_fornecedor VARCHAR(20),
    FOREIGN KEY (cnpj_fornecedor) REFERENCES fornecedor(cnpj)
    );

-- 6. TABELA DE VENDAS (CABEÇALHO)
-- Registra a operação de venda vinculando vendedor e cliente.
CREATE TABLE IF NOT EXISTS vendas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_venda TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    vendedor_cpf VARCHAR(14),
    cliente_cpf VARCHAR(14),
    valor_total DOUBLE NOT NULL,
    FOREIGN KEY (vendedor_cpf) REFERENCES funcionario(cpf),
    FOREIGN KEY (cliente_cpf) REFERENCES cliente(cpf)
    );

-- 7. TABELA DE ITENS DA VENDA
-- Permite a inserção de vários itens em uma única venda.
CREATE TABLE IF NOT EXISTS itens_venda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    venda_id INT,
    produto_codigo VARCHAR(20),
    quantidade INT NOT NULL,
    subtotal DOUBLE NOT NULL,
    FOREIGN KEY (venda_id) REFERENCES vendas(id),
    FOREIGN KEY (produto_codigo) REFERENCES produtos(codigo)
    );

-- 8. DADOS INICIAIS OBRIGATÓRIOS
-- Inserção do "Consumidor Final" genérico para vendas não identificadas.
INSERT IGNORE INTO cliente (cpf, nome, email, pontos_fidelidade)
VALUES ('000.000.000-00', 'Consumidor Final', 'N/A', 0);