package br.com.banco.model;

import java.util.ArrayList;
import java.util.List;

public abstract class ContaBancaria {

    private String nroConta;
    private Proprietario proprietario;
    private double saldo;
    private final List<String> historicoOperacoes = new ArrayList<>();

    public ContaBancaria(String nroConta, Proprietario proprietario, double saldo) {
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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String depositar(double valor) {
        if (valor <= 0) {
            return "Valor invalido para deposito!";
        }
        saldo += valor;
        registrarOperacao(String.format("Deposito: +R$ %.2f | Saldo: R$ %.2f", valor, saldo));
        return "Deposito realizado com sucesso!";
    }

    public abstract String sacar(double valor);

    public abstract String atualizar(double taxa);

    public abstract String getTipoConta();

    protected void registrarOperacao(String descricao) {
        historicoOperacoes.add(descricao);
    }

    protected String getHistoricoFormatado() {
        if (historicoOperacoes.isEmpty()) {
            return "Nenhuma operacao registrada.";
        }
        StringBuilder sb = new StringBuilder();
        for (String op : historicoOperacoes) {
            sb.append("- ").append(op).append("\n");
        }
        return sb.toString();
    }

    protected String getDetalhesEspecificos() {
        return "";
    }

    public String extrato() {
        return "Tipo da conta: " + getTipoConta() + "\n" +
                "Numero da conta: " + nroConta + "\n" +
                "Dados da conta: " + proprietario + "\n" +
                getDetalhesEspecificos() +
                "Saldo: R$ " + String.format("%.2f", saldo) + "\n" +
                "Historico de operacoes:\n" + getHistoricoFormatado();
    }
}
