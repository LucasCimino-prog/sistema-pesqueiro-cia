package Model;

public class CartaoDebito extends Pagamento{
    public CartaoDebito(double valor) {
        super(valor);
    }

    @Override
    public void processarPagamento() {
        System.out.println("Pagamento via Cartao de Debito: R$ " + valor + " (valor integral!)");
    }
}
