package Model;

import java.util.ArrayList;
import java.util.List;

public class Venda {
    private Funcionario vendedorLogado;
    private Cliente cliente;
    private List<ItemVenda> itens;
    private double valorTotal;

    public Venda(Funcionario vendedor, Cliente cliente) {
        this.vendedorLogado = vendedor;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.valorTotal = 0.0;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        ItemVenda novoItem = new ItemVenda(produto, quantidade);
        itens.add(novoItem);
        this.valorTotal += novoItem.getSubtotal();

    }

    public void finalizarVenda(Pagamento pagamentoEscolhido) {
        System.out.println("--- FINALIZANDO VENDA ---");
        System.out.println("Vendedor: " + vendedorLogado.getNome());
        System.out.println("Subtotal da Venda: R$ " + this.valorTotal);

        pagamentoEscolhido.processarPagamento();

        if (cliente != null) {
            cliente.adicionarPontos((int) (this.valorTotal / 10));
        }
    }

    public Funcionario getVendedorLogado() {
        return vendedorLogado;
    }

    public void setVendedorLogado(Funcionario vendedorLogado) {
        this.vendedorLogado = vendedorLogado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}