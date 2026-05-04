package Model;

public class Fornecedor {
    private String cnpj;
    private String nomeFantasia;
    private String contato;

    public Fornecedor(String cnpj, String nomeFantasia, String contato) {
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.contato = contato;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
