package DAO;

import Model.Pessoa;
import Model.Cliente;
import Model.Funcionario;
import Model.Fornecedor;
import java.sql.*;

// Aplicação do Padrão DAO e uso de Interface
public class PessoaDAO implements ICrud {

    @Override
    public void salvar(Object obj) {
        if (obj instanceof Cliente cliente) {
            salvarCliente(cliente);
        } else if (obj instanceof Funcionario funcionario) {
            salvarFuncionario(funcionario);
        } else if (obj instanceof Fornecedor fornecedor) {
            salvarFornecedor(fornecedor);
        }
    }

    // Busca o cliente para não perder os pontos ao reiniciar o programa
    public Cliente buscarClientePorCpf(String cpf) {
        String sql = "SELECT * FROM cliente WHERE cpf = ?";
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getInt("pontos_fidelidade"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente: " + e.getMessage());
        }
        return null;
    }

    public void atualizarPontos(Cliente cliente) {
        String sql = "UPDATE cliente SET pontos_fidelidade = ? WHERE cpf = ?";
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getPontosFidelidade());
            stmt.setString(2, cliente.getCpf());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar pontos: " + e.getMessage());
        }
    }

    private void salvarCliente(Cliente cliente) {
        String sql = "INSERT IGNORE INTO cliente (cpf, nome, email, pontos_fidelidade) VALUES (?,?,?,?)";
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEmail());
            stmt.setInt(4, cliente.getPontosFidelidade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void salvarFuncionario(Funcionario funcionario) {
        String sql = "INSERT IGNORE INTO funcionario (cpf, nome, matricula, cargo, percentual_vendas) VALUES (?,?,?,?,?)";
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getCpf());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getMatricula());
            stmt.setString(4, funcionario.getCargo());
            stmt.setDouble(5, funcionario.getPercentualVendas());
            stmt.executeUpdate();
            System.out.println("Funcionario salvo com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar funcionario: " + e.getMessage());
        }
    }

    private void salvarFornecedor(Fornecedor fornecedor) {
        String sql = "INSERT IGNORE INTO fornecedor (cnpj, nome_fantasia, contato) VALUES (?,?,?)";
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fornecedor.getCnpj());
            stmt.setString(2, fornecedor.getNomeFantasia());
            stmt.setString(3, fornecedor.getContato());
            stmt.executeUpdate();
            System.out.println("Fornecedor salvo com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar fornecedor: " + e.getMessage());
        }
    }
}