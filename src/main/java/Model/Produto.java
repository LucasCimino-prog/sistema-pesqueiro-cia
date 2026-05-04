package Model;

public class Produto {
    private String produtoId;
    private String descricao;
    private double precoCusto;
    private double precoVenda;
    private int quantidadeEstoque;
    private int quantidadeMinima;
    private Fornecedor fornecedorPreferencial;

    public Produto(String produtoId, String descricao, double precoCusto, double precoVenda, int quantidadeEstoque, int quantidadeMinima, Fornecedor fornecedorPreferencial) {
        this.produtoId = produtoId;
        this.descricao = descricao;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeMinima = quantidadeMinima;
        this.fornecedorPreferencial = fornecedorPreferencial;
    }

    public void registrarVenda(int quantidadeVendida) {
        if (quantidadeVendida > this.quantidadeEstoque) {
            System.out.println("Erro: Estoque insuficiente para " + this.descricao);
            return;
        }

        this.quantidadeEstoque -= quantidadeVendida;
        System.out.println(quantidadeVendida + "x " + this.descricao + " vendido(s). Estoque atual: " + this.quantidadeEstoque);

        verificarNecessidadeReposicao();
    }

    private void verificarNecessidadeReposicao() {
            if (this.quantidadeEstoque <= this.quantidadeMinima) {
                System.out.println("ALERTA DO SISTEMA: O produto [" + this.produtoId + " - " + this.descricao +
                        "] atingiu o estoque mínimo! Restam apenas " + this.quantidadeEstoque + " unidades.");
            }
        }

    // Lançamento de Exceção Personalizada
    public void reduzirEstoque(int quantidadeVenda) throws EstoqueInsuficienteException {
        if (quantidadeVenda > this.quantidadeEstoque) {
            // Lança a exceção se não tiver estoque suficiente
            throw new EstoqueInsuficienteException("Erro: Estoque insuficiente para o produto " + this.descricao);
        }
        this.quantidadeEstoque -= quantidadeVenda;

        verificarNecessidadeReposicao();
    }

    public String getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(String produtoId) {
        this.produtoId = produtoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public Fornecedor getFornecedorPreferencial() {
        return fornecedorPreferencial;
    }

    public void setFornecedorPreferencial(Fornecedor fornecedorPreferencial) {
        this.fornecedorPreferencial = fornecedorPreferencial;
    }
}
