# Sistema de Gestão - Pesqueiro & Cia

## Tecnologias Utilizadas

Linguagem: Java (Orientação a Objetos)

Banco de Dados: MySQL

Integração: JDBC (Padrão DAO)

Arquitetura: MVC (Model-View-Controller simplificado)

## Funcionalidades Principais

Gestão de Estoque: Controle de quantidade e alerta automático quando o produto atinge o estoque mínimo.

Vendas Complexas: Permite múltiplos itens por venda e vincula a transação a um funcionário logado.

Regras de Pagamento: Aplicação automática de 5% de desconto para pagamentos via PIX ou Dinheiro. Opção de parcelamento no Cartão de Crédito.

Programa de Fidelidade: Sistema de acúmulo de pontos para clientes cadastrados.

## Como Executar o Projeto

Clone este repositório.

No seu servidor MySQL, execute o script script_banco.sql localizado na pasta resources para criar as tabelas necessárias.

Configure as credenciais do banco (URL, usuário e senha) na classe ConexaoBanco.java.

Execute a classe Main.java (View) para abrir o menu interativo no console.