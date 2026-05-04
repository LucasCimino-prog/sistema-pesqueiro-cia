package View;

import Model.*;
import DAO.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaDAO pessoaDAO = new PessoaDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();

        // 1. CARREGAMENTO E CADASTRO INICIAL (Persistência)
        Fornecedor fornecedor = new Fornecedor("12.345.678/000100", "Pesca Bruta LTDA", "(32) 99998888");
        Funcionario vendedor = new Funcionario("000.111.22233", "Marcos", "VEND01", "Vendedor", 5.0);

        // CONFIGURAÇÃO DO CONSUMIDOR FINAL
        Cliente consumidorFinal = pessoaDAO.buscarClientePorCpf("000.000.000-00");
        if (consumidorFinal == null) {
            consumidorFinal = new Cliente("000.000.000-00", "Consumidor Final", 0, "N/A");
            pessoaDAO.salvar(consumidorFinal);
        }

        // Cadastro de teste (Lucas)
        Cliente clientePadrao = pessoaDAO.buscarClientePorCpf("123.456.78900");
        if (clientePadrao == null) {
            clientePadrao = new Cliente("123.456.78900", "Lucas", 0, "lucas@email.com");
            pessoaDAO.salvar(clientePadrao);
        }

        pessoaDAO.salvar(fornecedor);
        pessoaDAO.salvar(vendedor);

        Produto varaPesca = produtoDAO.buscarPorCodigo("VAR001", fornecedor);
        if (varaPesca == null) {
            varaPesca = new Produto("VAR001", "Vara de Carbono 2.4m", 80.00, 150.00, 10, 3, fornecedor);
            produtoDAO.salvar(varaPesca);
        }

        int opcaoMenu = 0;
        while (opcaoMenu != 5) {
            System.out.println("\n=================================");
            System.out.println("   SISTEMA PESQUEIRO & CIA");
            System.out.println("=================================");
            System.out.println("1. Realizar Venda");
            System.out.println("2. Ver dados do Cliente Cadastrado");
            System.out.println("3. Reposição de Estoque");
            System.out.println("4. Ver Info Vendedor/Fornecedor");
            System.out.println("5. Sair");
            System.out.print("Escolha: ");
            opcaoMenu = scanner.nextInt();

            if (opcaoMenu == 1) {
                // SELEÇÃO DE CLIENTE (Identificado ou Consumidor Final)
                System.out.print("Identificar cliente por CPF? (S/N): ");
                String escolha = scanner.next();
                Cliente clienteDaVenda = null;

                if (escolha.equalsIgnoreCase("S")) {
                    System.out.print("Digite o CPF: ");
                    String cpf = scanner.next();
                    clienteDaVenda = pessoaDAO.buscarClientePorCpf(cpf);

                    if (clienteDaVenda == null) {
                        System.out.println("CPF não encontrado. Usando Consumidor Final.");
                        clienteDaVenda = consumidorFinal;
                    }
                } else {
                    clienteDaVenda = consumidorFinal;
                }

                Venda venda = new Venda(vendedor, clienteDaVenda);
                System.out.print("Quantidade (Estoque: " + varaPesca.getQuantidadeEstoque() + "): ");
                int qtd = scanner.nextInt();

                try {
                    varaPesca.reduzirEstoque(qtd);
                    venda.adicionarItem(varaPesca, qtd);

                    System.out.println("\nTotal: R$ " + venda.getValorTotal());
                    System.out.println("1. Pix | 2. Dinheiro | 3. Débito | 4. Crédito");
                    System.out.print("Opção: ");
                    int pg = scanner.nextInt();

                    Pagamento p = null;
                    if (pg == 1) p = new Pix(venda.getValorTotal());
                    else if (pg == 2) p = new Dinheiro(venda.getValorTotal());
                    else if (pg == 3) p = new CartaoDebito(venda.getValorTotal());
                    else if (pg == 4) {
                        System.out.print("Parcelas: ");
                        p = new CartaoCredito(venda.getValorTotal(), scanner.nextInt());
                    }

                    if (p != null) {
                        venda.finalizarVenda(p);

                        // PERSISTÊNCIA NO BANCO DE DADOS
                        new VendaDAO().registrarVenda(venda);
                        produtoDAO.atualizarEstoque(varaPesca);
                        pessoaDAO.atualizarPontos(clienteDaVenda);
                        System.out.println("Venda para " + clienteDaVenda.getNome() + " finalizada!");
                    }

                } catch (EstoqueInsuficienteException e) {
                    System.err.println(e.getMessage());
                }

            } else if (opcaoMenu == 2) {
                // Atualiza o objeto clientePadrao do banco para ver os pontos novos
                clientePadrao = pessoaDAO.buscarClientePorCpf("123.456.78900");
                clientePadrao.exibirDados();

            } else if (opcaoMenu == 3) {
                System.out.print("Qtd para adicionar: ");
                int qtdAdicional = scanner.nextInt();
                varaPesca.setQuantidadeEstoque(varaPesca.getQuantidadeEstoque() + qtdAdicional);
                produtoDAO.atualizarEstoque(varaPesca);
                System.out.println("Estoque atualizado!");

            } else if (opcaoMenu == 4) {
                vendedor.exibirDados();
                System.out.println("Fornecedor: " + fornecedor.getNomeFantasia());
            }
        }
        scanner.close();
    }
}