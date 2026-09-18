package br.com.banco.model;

public class Conta {

    private Proprietario proprietario;
    private double saldo;

    public Conta(Proprietario proprietario, double saldo) {
        this.proprietario = proprietario;
        this.saldo = saldo;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public double getSaldo(){
        return saldo;
    }

    public void setSaldo(double saldo){
        this.saldo = saldo;
    }


    public String depositar(double valor){
        saldo += valor;
        return "Deposito realizado com sucesso!";
    }

    public String sacar(double valor){
        if(valor <= saldo) {
            saldo -= valor;
            return "Saque realizado com sucesso!";
        }else{
            return "Saldo insuficiente para saque!";
        }
    }

    public String extrato(){
        return "Dados da conta: " + proprietario + "\n" + "Saldo: " + saldo;
    }
}
