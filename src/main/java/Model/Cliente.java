package Model;

// Herança e Polimorfismo: Cliente herda os atributos da classe abstrata Pessoa
public class Cliente extends Pessoa {
    private String email;
    private int pontosFidelidade;

    public Cliente(String cpf, String nome, int pontosFidelidade, String email) {
        super(cpf, nome);
        this.pontosFidelidade = pontosFidelidade;
        this.email = email;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getPontosFidelidade() { return pontosFidelidade; }
    public void setPontosFidelidade(int pontosFidelidade) { this.pontosFidelidade = pontosFidelidade; }

    public void adicionarPontos(int pontos) {
        this.pontosFidelidade += pontos;
    }

    @Override
    public void exibirDados() {
        System.out.println("Cliente: " + getNome() + " | CPF: " + getCpf() + " | Pontos: " + getPontosFidelidade());
    }
}