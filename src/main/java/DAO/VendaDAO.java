package DAO;

import Model.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Padrão DAO: Isola os comandos de acesso ao banco de dados relacional
public class VendaDAO {

    public void registrarVenda(Venda venda) {
        String sql = "INSERT INTO vendas (cliente_cpf, funcionario_cpf, valor_total) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, venda.getCliente().getCpf());
            stmt.setString(2, venda.getVendedorLogado().getCpf());
            stmt.setDouble(3, venda.getValorTotal());

            stmt.executeUpdate();
            System.out.println("Venda registrada no banco de dados!");

        } catch (SQLException e) {
            System.out.println("Erro ao registrar venda: " + e.getMessage());
        }
    }
}