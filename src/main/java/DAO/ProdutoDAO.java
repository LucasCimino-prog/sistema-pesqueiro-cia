package DAO;

import Model.*;
import java.sql.*;

public class ProdutoDAO {
    public void salvar(Produto produto) {
        String sql = "INSERT IGNORE INTO produtos (codigo, descricao, preco_custo, preco_venda, estoque, estoque_minimo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getProdutoId());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPrecoCusto());
            stmt.setDouble(4, produto.getPrecoVenda());
            stmt.setInt(5, produto.getQuantidadeEstoque());
            stmt.setInt(6, produto.getQuantidadeMinima());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao salvar produto: " + e.getMessage());
        }
    }

    public void atualizarEstoque(Produto produto) {
        String sql = "UPDATE produtos SET estoque = ? WHERE codigo = ?";
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, produto.getQuantidadeEstoque());
            stmt.setString(2, produto.getProdutoId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar estoque: " + e.getMessage());
        }
    }

    public Produto buscarPorCodigo(String codigo, Fornecedor fornecedor) {
        String sql = "SELECT * FROM produtos WHERE codigo = ?";
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Produto(
                        rs.getString("codigo"),
                        rs.getString("descricao"),
                        rs.getDouble("preco_custo"),
                        rs.getDouble("preco_venda"),
                        rs.getInt("estoque"),
                        rs.getInt("estoque_minimo"),
                        fornecedor
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar produto: " + e.getMessage());
        }
        return null;
    }
}