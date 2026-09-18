package br.com.banco.app;

import javax.swing.JOptionPane;
import br.com.banco.model.Conta;
import br.com.banco.model.Proprietario;

public class Banco {

    static boolean operacoesHabilitadas = false;
    public static void main(String[] args) {
        Conta conta = null;
        String opcao = null;
        int opcaoInt = 0;
        int resposta = 0;
        do {
            opcao = JOptionPane.showInputDialog("Escolha uma opção: \n" +
                    "1 - Criar conta\n" +
                    "2 - Sacar\n" +
                    "3 - Depositar\n" +
                    "4 - Consultar extrato\n" +
                    "5 - Area do Gerente\n" +
                    "6 - Sair");

            opcaoInt = Integer.parseInt(opcao);

            switch (opcaoInt){
                case 1:
                    String nome = JOptionPane.showInputDialog("Digite o nome do proprietario:");
                    String cpf = JOptionPane.showInputDialog("Digite o CPF do proprietario:");
                    String endereco = JOptionPane.showInputDialog("Digite o endereco do proprietario:");
                    String email = JOptionPane.showInputDialog("Digite o email do proprietario:");
                    Proprietario proprietario = new Proprietario(nome, cpf, endereco, email);
                    conta = new Conta(proprietario, 0);
                    resposta = JOptionPane.showConfirmDialog(null, "Deseja realizar outra operação?");
                    break;
                case 2:
                    if(operacoesHabilitadas) {
                        if (conta != null) {
                            double valorSaque = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do saque:"));
                            conta.sacar(valorSaque);
                        } else {
                            JOptionPane.showMessageDialog(null, "Crie uma conta antes de sacar.");
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "Saque nao permitido!");
                    }
                    resposta = JOptionPane.showConfirmDialog(null, "Deseja realizar outra operação?");
                    break;
                case 3:
                    if(operacoesHabilitadas) {
                        if(conta != null){
                            double valorDeposito = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do deposito:"));
                            conta.depositar(valorDeposito);
                        } else {
                            JOptionPane.showMessageDialog(null, "Crie uma conta antes de depositar.");
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "Deposito nao permitido!");
                    }
                    resposta = JOptionPane.showConfirmDialog(null, "Deseja realizar outra operação?");
                    break;
                case 4:
                    if(operacoesHabilitadas) {
                        if (conta != null) {
                            JOptionPane.showMessageDialog(null, conta.extrato());
                        } else {
                            JOptionPane.showMessageDialog(null, "Crie uma conta antes de consultar o extrato.");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Extrato nao permitido!");
                    }
                    resposta = JOptionPane.showConfirmDialog(null, "Deseja realizar outra operação?");
                    break;
                case 5:
                    operacoesHabilitadas = !operacoesHabilitadas;
                    if(operacoesHabilitadas){
                    JOptionPane.showMessageDialog(null, "Permissões liberadas!");
                    }else{
                    JOptionPane.showMessageDialog(null, "Permissões bloqueadas!");
                    }
                    resposta = JOptionPane.showConfirmDialog(null, "Deseja realizar outra operação?");
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    break;
            }

        }while (resposta != JOptionPane.NO_OPTION && opcaoInt != 6);

    }
}

