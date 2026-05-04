package Model;

public class Funcionario extends Pessoa {
    private String matricula;
    private String cargo;
    private double percentualVendas;

    public Funcionario(String cpf, String nome, String matricula, String cargo, double percentualVendas) {
        super(cpf, nome);
        this.matricula = matricula;
        this.cargo = cargo;
        this.percentualVendas = percentualVendas;
    }

    public String getMatricula() { return matricula; }
    public String getCargo() { return cargo; }
    public double getPercentualVendas() { return percentualVendas; }

    @Override
    public void exibirDados() {
        System.out.println("Funcionário: " + getNome() + " | Cargo: " + getCargo());
    }
}