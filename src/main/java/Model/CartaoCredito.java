package Model;

public class CartaoCredito extends Pagamento{
    private int quantidadeParcelas;

    public CartaoCredito(double valor, int quantidadeParcelas) {
        super(valor);
        this.quantidadeParcelas = quantidadeParcelas;
    }

    @Override
    public void processarPagamento() {
        double valorFinal = this.valor;

        if (quantidadeParcelas == 1) {
            System.out.println("Pagamento em 1x sem juros.");

        } else if (quantidadeParcelas >= 2 && quantidadeParcelas <= 6) {

            valorFinal = this.valor + (this.valor * 0.05);
            System.out.println("(taxa de 5%)");

        } else if (quantidadeParcelas >= 7 && quantidadeParcelas <= 12) {

            valorFinal = this.valor + (this.valor * 0.10);
            System.out.println("(taxa de 10%)");

        } else {
            System.out.println("Erro: A quantidade de parcelas deve ser entre 1 e 12.");
            return;
        }

        double valorDaParcela = valorFinal / quantidadeParcelas;

        System.out.printf("Total a pagar: R$%.2f%n", valorFinal);
        System.out.printf("Dividido em: %dx de R$%.2f%n", quantidadeParcelas, valorDaParcela);

    }
}
