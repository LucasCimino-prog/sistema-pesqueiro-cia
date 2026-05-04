package Model;

public class Pix extends Pagamento{
    public Pix(double valor) {
        super(valor);
    }

    @Override
    public void processarPagamento() {
        double total = valor * 0.95;
        System.out.println("Pagamento via Pix: R$ " + total + " (com 5% de desconto)");
    }
}
