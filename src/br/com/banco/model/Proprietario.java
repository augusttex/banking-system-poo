package br.com.banco.model;

public class Proprietario {

    private String nome;
    private String cpf;
    private String endereco;
    private String email;

    public Proprietario(String nome, String cpf, String endereco, String email){
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf(){
        return cpf;
    }
    public String getEndereco(){
        return endereco;
    }

    public String getEmail(){
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public String toString() {
        return "Proprietário: \n" +
                "Nome: " + nome + "\n" +
                "CPF: " + cpf + "\n" +
                "Endereço: " + endereco + "\n" +
                "Email: " + email + "\n";
    }
}
