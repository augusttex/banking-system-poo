package br.com.banco.model;

public class ContaCorrente extends ContaBancaria {

    private double limite;
    private double mensalidade;

    public ContaCorrente(String nroConta, Proprietario proprietario, double saldo, double limite, double mensalidade) {
        super(nroConta, proprietario, saldo);
        this.limite = limite;
        this.mensalidade = mensalidade;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(double mensalidade) {
        this.mensalidade = mensalidade;
    }

    @Override
    public String sacar(double valor) {
        if (valor <= 0) {
            return "Valor invalido para saque!";
        }
        if (getSaldo() + limite >= valor) {
            setSaldo(getSaldo() - valor);
            registrarOperacao(String.format("Saque: -R$ %.2f | Saldo: R$ %.2f", valor, getSaldo()));
            return "Saque realizado com sucesso!";
        }
        return "Saldo e limite insuficientes para saque!";
    }

    public String transferir(ContaBancaria destino, double valor) {
        if (destino == null) {
            return "Conta de destino invalida!";
        }
        if (this == destino) {
            return "A conta de origem e destino nao podem ser iguais!";
        }
        if (valor <= 0) {
            return "Valor invalido para transferencia!";
        }
        if (getSaldo() + limite >= valor) {
            setSaldo(getSaldo() - valor);
            destino.setSaldo(destino.getSaldo() + valor);
            registrarOperacao(String.format("Transferencia enviada para conta %s: -R$ %.2f | Saldo: R$ %.2f",
                    destino.getNroConta(), valor, getSaldo()));
            destino.registrarOperacao(String.format("Transferencia recebida da conta %s: +R$ %.2f | Saldo: R$ %.2f",
                    getNroConta(), valor, destino.getSaldo()));
            return "Transferencia realizada com sucesso!";
        }
        return "Saldo e limite insuficientes para transferencia!";
    }

    @Override
    public String atualizar(double taxa) {
        if (taxa < 0) {
            return "Taxa invalida para atualizacao!";
        }
        double rendimento = getSaldo() > 0 ? getSaldo() * taxa : 0;
        setSaldo(getSaldo() + rendimento - mensalidade);
        registrarOperacao(String.format("Atualizacao (taxa %.2f%%, +R$ %.2f, -mensalidade R$ %.2f) | Saldo: R$ %.2f",
                taxa * 100, rendimento, mensalidade, getSaldo()));
        return "Conta corrente atualizada com sucesso!";
    }

    @Override
    public String getTipoConta() {
        return "Conta Corrente";
    }

    @Override
    protected String getDetalhesEspecificos() {
        return "Limite: R$ " + String.format("%.2f", limite) + "\n" +
                "Mensalidade: R$ " + String.format("%.2f", mensalidade) + "\n";
    }
}
