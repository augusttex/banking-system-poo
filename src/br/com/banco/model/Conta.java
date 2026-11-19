package br.com.banco.model;

public class Conta {

    private String nroConta;
    private Proprietario proprietario;
    private double saldo;

    public Conta(String nroConta, Proprietario proprietario, double saldo) {
        this.nroConta = nroConta;
        this.proprietario = proprietario;
        this.saldo = saldo;
    }

    public String getNroConta() {
        return nroConta;
    }

    public void setNroConta(String nroConta) {
        this.nroConta = nroConta;
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
        if (valor <= 0) {
            return "Valor invalido para deposito!";
        }
        saldo += valor;
        return "Deposito realizado com sucesso!";
    }

    public String sacar(double valor){
        if (valor <= 0) {
            return "Valor invalido para saque!";
        }
        if(valor <= saldo) {
            saldo -= valor;
            return "Saque realizado com sucesso!";
        }else{
            return "Saldo insuficiente para saque!";
        }
    }

    public String transferir(Conta destino, double valor){
        if (destino == null) {
            return "Conta de destino invalida!";
        }
        if (this == destino) {
            return "A conta de origem e destino nao podem ser iguais!";
        }
        if (valor <= 0) {
            return "Valor invalido para transferencia!";
        }
        if (valor <= saldo) {
            saldo -= valor;
            destino.saldo += valor;
            return "Transferencia realizada com sucesso!";
        } else {
            return "Saldo insuficiente para transferencia!";
        }
    }

    public String extrato(){
        return "Numero da conta: " + nroConta + "\n" +
                "Dados da conta: " + proprietario + "\n" +
                "Saldo: R$ " + String.format("%.2f", saldo);
    }
}
