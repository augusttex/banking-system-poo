package br.com.banco.model;

public class ContaPoupanca extends ContaBancaria {

    public ContaPoupanca(String nroConta, Proprietario proprietario, double saldo) {
        super(nroConta, proprietario, saldo);
    }

    @Override
    public String sacar(double valor) {
        if (valor <= 0) {
            return "Valor invalido para saque!";
        }
        if (getSaldo() - valor >= 0) {
            setSaldo(getSaldo() - valor);
            registrarOperacao(String.format("Saque: -R$ %.2f | Saldo: R$ %.2f", valor, getSaldo()));
            return "Saque realizado com sucesso!";
        }
        return "Saldo insuficiente para saque!";
    }

    @Override
    public String atualizar(double taxa) {
        if (taxa < 0) {
            return "Taxa invalida para atualizacao!";
        }
        double rendimento = getSaldo() > 0 ? getSaldo() * (2 * taxa) : 0;
        setSaldo(getSaldo() + rendimento);
        registrarOperacao(String.format("Atualizacao (2x taxa %.2f%%): +R$ %.2f | Saldo: R$ %.2f",
                taxa * 100, rendimento, getSaldo()));
        return "Conta poupanca atualizada com sucesso!";
    }

    @Override
    public String getTipoConta() {
        return "Conta Poupanca";
    }
}
