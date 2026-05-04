package Model;

import java.util.ArrayList;
import java.util.List;

public class ItemVenda {
    private Produto produto;
    private int quantidade;
    private double subtotal;

    public ItemVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.subtotal = produto.getPrecoVenda() * quantidade;
    }

    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public double getSubtotal() { return subtotal; }
}

