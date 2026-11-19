package br.com.banco.app;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import br.com.banco.model.Conta;
import br.com.banco.model.Proprietario;

public class Banco {

    static boolean operacoesHabilitadas = false;
    static List<Conta> contas = new ArrayList<>();

    public static void main(String[] args) {
        int opcaoInt = 0;
        int resposta = JOptionPane.YES_OPTION;

        do {
            String opcao = JOptionPane.showInputDialog("Escolha uma opção: \n" +
                    "1 - Criar conta\n" +
                    "2 - Sacar\n" +
                    "3 - Depositar\n" +
                    "4 - Consultar extrato\n" +
                    "5 - Transferir\n" +
                    "6 - Area do Gerente\n" +
                    "7 - Sair");

            if (opcao == null) {
                break;
            }

            try {
                opcaoInt = Integer.parseInt(opcao.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opcao invalida!");
                continue;
            }

            switch (opcaoInt) {
                case 1:
                    criarConta();
                    resposta = JOptionPane.showConfirmDialog(null, "Deseja realizar outra operação?");
                    break;
                case 2:
                    realizarSaque();
                    resposta = JOptionPane.showConfirmDialog(null, "Deseja realizar outra operação?");
                    break;
                case 3:
                    realizarDeposito();
                    resposta = JOptionPane.showConfirmDialog(null, "Deseja realizar outra operação?");
                    break;
                case 4:
                    consultarExtrato();
                    resposta = JOptionPane.showConfirmDialog(null, "Deseja realizar outra operação?");
                    break;
                case 5:
                    realizarTransferencia();
                    resposta = JOptionPane.showConfirmDialog(null, "Deseja realizar outra operação?");
                    break;
                case 6:
                    abrirAreaGerente();
                    resposta = JOptionPane.showConfirmDialog(null, "Deseja realizar outra operação?");
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcao invalida!");
                    break;
            }

        } while (resposta != JOptionPane.NO_OPTION && resposta != JOptionPane.CLOSED_OPTION && opcaoInt != 7);
    }

    private static Conta buscarConta(String nroConta) {
        if (nroConta == null) {
            return null;
        }
        String numeroBuscado = nroConta.trim();
        for (Conta conta : contas) {
            if (conta.getNroConta().equals(numeroBuscado)) {
                return conta;
            }
        }
        return null;
    }

    private static String solicitarTextoNaoVazio(String mensagem) {
        String entrada = JOptionPane.showInputDialog(mensagem);
        if (entrada == null || entrada.trim().isEmpty()) {
            return null;
        }
        return entrada.trim();
    }

    private static Conta solicitarConta(String mensagem) {
        String nroConta = solicitarTextoNaoVazio(mensagem);
        if (nroConta == null) {
            return null;
        }
        Conta conta = buscarConta(nroConta);
        if (conta == null) {
            JOptionPane.showMessageDialog(null, "Conta " + nroConta + " nao encontrada!");
        }
        return conta;
    }

    private static Double solicitarValor(String mensagem) {
        String entrada = solicitarTextoNaoVazio(mensagem);
        if (entrada == null) {
            return null;
        }
        try {
            return Double.parseDouble(entrada.replace(",", "."));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor invalido!");
            return null;
        }
    }

    private static void criarConta() {
        String nroConta = solicitarTextoNaoVazio("Digite o numero da conta:");
        if (nroConta == null) {
            JOptionPane.showMessageDialog(null, "Operacao cancelada ou numero da conta invalido.");
            return;
        }
        if (buscarConta(nroConta) != null) {
            JOptionPane.showMessageDialog(null, "Ja existe uma conta com o numero " + nroConta + "!");
            return;
        }

        String nome = JOptionPane.showInputDialog("Digite o nome do proprietario:");
        String cpf = JOptionPane.showInputDialog("Digite o CPF do proprietario:");
        String endereco = JOptionPane.showInputDialog("Digite o endereco do proprietario:");
        String email = JOptionPane.showInputDialog("Digite o email do proprietario:");
        if (nome == null || cpf == null || endereco == null || email == null) {
            JOptionPane.showMessageDialog(null, "Criacao de conta cancelada.");
            return;
        }

        Proprietario proprietario = new Proprietario(nome.trim(), cpf.trim(), endereco.trim(), email.trim());
        contas.add(new Conta(nroConta, proprietario, 0));
        JOptionPane.showMessageDialog(null, "Conta " + nroConta + " criada com sucesso!");
    }

    private static void realizarSaque() {
        if (!operacoesHabilitadas) {
            JOptionPane.showMessageDialog(null, "Saque nao permitido!");
            return;
        }
        if (contas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Crie uma conta antes de sacar.");
            return;
        }
        Conta conta = solicitarConta("Digite o numero da conta para saque:");
        if (conta == null) {
            return;
        }
        Double valorSaque = solicitarValor("Digite o valor do saque:");
        if (valorSaque == null) {
            return;
        }
        JOptionPane.showMessageDialog(null, conta.sacar(valorSaque));
    }

    private static void realizarDeposito() {
        if (!operacoesHabilitadas) {
            JOptionPane.showMessageDialog(null, "Deposito nao permitido!");
            return;
        }
        if (contas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Crie uma conta antes de depositar.");
            return;
        }
        Conta conta = solicitarConta("Digite o numero da conta para deposito:");
        if (conta == null) {
            return;
        }
        Double valorDeposito = solicitarValor("Digite o valor do deposito:");
        if (valorDeposito == null) {
            return;
        }
        JOptionPane.showMessageDialog(null, conta.depositar(valorDeposito));
    }

    private static void consultarExtrato() {
        if (!operacoesHabilitadas) {
            JOptionPane.showMessageDialog(null, "Extrato nao permitido!");
            return;
        }
        if (contas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Crie uma conta antes de consultar o extrato.");
            return;
        }
        Conta conta = solicitarConta("Digite o numero da conta para consultar o extrato:");
        if (conta == null) {
            return;
        }
        JOptionPane.showMessageDialog(null, conta.extrato());
    }

    private static void realizarTransferencia() {
        if (!operacoesHabilitadas) {
            JOptionPane.showMessageDialog(null, "Transferencia nao permitida!");
            return;
        }
        if (contas.size() < 2) {
            JOptionPane.showMessageDialog(null, "E necessario ter pelo menos duas contas cadastradas para transferir.");
            return;
        }
        Conta contaOrigem = solicitarConta("Digite o numero da conta de origem:");
        if (contaOrigem == null) {
            return;
        }
        Conta contaDestino = solicitarConta("Digite o numero da conta de destino:");
        if (contaDestino == null) {
            return;
        }
        if (contaOrigem == contaDestino) {
            JOptionPane.showMessageDialog(null, "A conta de origem e destino nao podem ser iguais!");
            return;
        }
        Double valorTransferencia = solicitarValor("Digite o valor da transferencia:");
        if (valorTransferencia == null) {
            return;
        }
        JOptionPane.showMessageDialog(null, contaOrigem.transferir(contaDestino, valorTransferencia));
    }

    private static void abrirAreaGerente() {
        int opcaoGerente = 0;
        do {
            String statusPermissao = operacoesHabilitadas ? "Liberadas" : "Bloqueadas";
            String entrada = JOptionPane.showInputDialog("Area do Gerente - Escolha uma opcao:\n" +
                    "1 - Saldos por conta\n" +
                    "2 - Saldo total dos clientes\n" +
                    "3 - Habilitar/Desabilitar operacoes (Atual: " + statusPermissao + ")\n" +
                    "4 - Voltar");

            if (entrada == null) {
                break;
            }

            try {
                opcaoGerente = Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opcao invalida!");
                continue;
            }

            switch (opcaoGerente) {
                case 1:
                    exibirSaldosPorConta();
                    break;
                case 2:
                    exibirSaldoTotalClientes();
                    break;
                case 3:
                    operacoesHabilitadas = !operacoesHabilitadas;
                    if (operacoesHabilitadas) {
                        JOptionPane.showMessageDialog(null, "Permissões liberadas!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Permissões bloqueadas!");
                    }
                    break;
                case 4:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcao invalida!");
                    break;
            }
        } while (opcaoGerente != 4);
    }

    private static void exibirSaldosPorConta() {
        if (contas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma conta cadastrada no sistema.");
            return;
        }
        StringBuilder relatorio = new StringBuilder("Saldos por conta:\n\n");
        for (Conta conta : contas) {
            relatorio.append("Conta: ").append(conta.getNroConta())
                    .append(" | Titular: ").append(conta.getProprietario().getNome())
                    .append(" | Saldo: R$ ").append(String.format("%.2f", conta.getSaldo()))
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, relatorio.toString());
    }

    private static void exibirSaldoTotalClientes() {
        double total = 0;
        for (Conta conta : contas) {
            total += conta.getSaldo();
        }
        JOptionPane.showMessageDialog(null,
                "Total de contas: " + contas.size() + "\n" +
                "Saldo total dos clientes: R$ " + String.format("%.2f", total));
    }
}
