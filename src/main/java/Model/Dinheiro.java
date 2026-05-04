package Model;

public class Dinheiro extends Pagamento{
    public Dinheiro(double valor) {
        super(valor);
    }

    @Override
    public void processarPagamento() {
        double total = valor * 0.95;
        System.out.println("Pagamento via Dinheiro: R$ " + total + " (com 5% de desconto)");
    }
}
