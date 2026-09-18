package br.com.banco.app;

import java.util.Scanner;
import br.com.banco.model.Conta;
import br.com.banco.model.Proprietario;

public class Banco {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        Conta conta = null;

        do {

            System.out.println("Escolha uma opção:");
            System.out.println("1 - Criar conta");
            System.out.println("2 - Sacar");
            System.out.println("3 - Depositar");
            System.out.println("4 - Consultar extrato");
            System.out.println("5 - Sair");

            opcao = scanner.nextInt();

            switch (opcao){
                case 1:
                    System.out.println("Digite o nome do proprietario:");
                    String nome = scanner.nextLine();
                    System.out.println("Digite o CPF do proprietario:");
                    String cpf = scanner.next();
                    System.out.println("Digite o endereco do proprietario:");
                    String endereco = scanner.nextLine();
                    System.out.println("Digite o email do proprietario:");
                    String email = scanner.next();
                    Proprietario proprietario = new Proprietario(nome, cpf, endereco, email);
                    conta = new Conta(proprietario, 0);
                    break;
                case 2:
                    if (conta != null) {
                        System.out.println("Digite o valor do saque:");
                        double valorSaque = scanner.nextDouble();
                        conta.sacar(valorSaque);
                    } else {
                        System.out.println("Crie uma conta antes de sacar.");
                    }
                    break;
                case 3:
                    if(conta != null){
                        System.out.println("Digite o valor do deposito:");
                        double valorDeposito = scanner.nextDouble();
                        conta.depositar(valorDeposito);
                    } else {
                        System.out.println("Crie uma conta antes de depositar.");
                    }
                    break;
                case 4:
                    if(conta != null){
                        System.out.println(conta.extrato());
                    } else {
                        System.out.println("Crie uma conta antes de consultar o extrato.");
                    }
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        }while (opcao != 5);

    }
}

