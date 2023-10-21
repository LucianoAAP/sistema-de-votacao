package com.betrybe.sistemadevotacao;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe principal.
 */
public class Principal {

  /**
   * Método principal.
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    GerenciamentoVotacao gerenciamentoVotacao = new GerenciamentoVotacao();
    System.out.println("----------- Bem-vindo ao Sistema de Votação -----------");
    System.out.println("");
    showRegisterCandidateMenu(scanner, gerenciamentoVotacao);
  }

  /**
   * Mensagem do registro da pessoa candidata.
   */
  private static void showRegisterCandidateMessage() {
    System.out.println("Cadastrar pessoa candidata?");
    System.out.println("1 - Sim");
    System.out.println("2 - Não");
    System.out.println("Entre com o número correspondente à opção desejada:");
  }
  
  /**
   * Mensagem do registro da pessoa eleitora.
   */
  private static void showRegisterVoterMessage() {
    System.out.println("Cadastrar pessoa eleitora?");
    System.out.println("1 - Sim");
    System.out.println("2 - Não");
    System.out.println("Entre com o número correspondente à opção desejada:");
  }
  
  /**
   * Mensagem do início da votação.
   */
  private static void showVotingMessage() {
    System.out.println("Entre com o número correspondente à opção desejada:");
    System.out.println("1 - Votar");
    System.out.println("2 - Resultado Parcial");
    System.out.println("3 - Finalizar Votação");
  }
  
  /**
   * Menu do registro da pessoa candidata.
   */
  private static void showRegisterCandidateMenu(Scanner scanner,
      GerenciamentoVotacao gerenciamentoVotacao) {
    try {
      showRegisterCandidateMessage();
      int option = scanner.nextInt();
      if (option == 1) {
        System.out.println("Entre com o nome da pessoa candidata:");
        String name = scanner.next();
        System.out.println("Entre com o número da pessoa candidata:");
        int number = scanner.nextInt();
        gerenciamentoVotacao.cadastrarPessoaCandidata(name, number);
        showRegisterCandidateMenu(scanner, gerenciamentoVotacao);
      } else if (option == 2) {
        System.out.println("");
        System.out.println("");
        System.out.println("----------- Cadastre as pessoas eleitoras -----------");
        System.out.println("");
        showRegisterVoterMenu(scanner, gerenciamentoVotacao);
      } else {
        System.out.println("Entre com uma opção válida!");
        showRegisterCandidateMenu(scanner, gerenciamentoVotacao);
      }
    } catch (InputMismatchException e) {
      System.out.println("Entre com uma opção válida!");
      showRegisterCandidateMenu(scanner, gerenciamentoVotacao);
    }
  }
  
  /**
   * Menu do registro da pessoa eleitora.
   */
  private static void showRegisterVoterMenu(Scanner scanner,
      GerenciamentoVotacao gerenciamentoVotacao) {
    try {
      showRegisterVoterMessage();
      int option = scanner.nextInt();
      if (option == 1) {
        System.out.println("Entre com o nome da pessoa eleitora:");
        String name = scanner.next();
        System.out.println("Entre com o cpf da pessoa eleitora:");
        String cpf = scanner.next();
        gerenciamentoVotacao.cadastrarPessoaEleitora(name, cpf);;
        showRegisterVoterMenu(scanner, gerenciamentoVotacao);
      } else if (option == 2) {
        System.out.println("");
        System.out.println("");
        System.out.println("----------- Votação iniciada! -----------");
        System.out.println("");
        showVotingMenu(scanner, gerenciamentoVotacao);
      } else {
        System.out.println("Entre com uma opção válida!");
        showRegisterVoterMenu(scanner, gerenciamentoVotacao);
      }
    } catch (InputMismatchException e) {
      System.out.println("Entre com uma opção válida!");
      showRegisterVoterMenu(scanner, gerenciamentoVotacao);
    }
  }
  
  /**
   * Menu da votação.
   */
  private static void showVotingMenu(Scanner scanner, GerenciamentoVotacao gerenciamentoVotacao) {
    try {
      showVotingMessage();
      int option = scanner.nextInt();
      if (option == 1) {
        System.out.println("Entre com o cpf da pessoa eleitora:");
        String cpf = scanner.next();
        System.out.println("Entre com o número da pessoa candidata:");
        int number = scanner.nextInt();
        gerenciamentoVotacao.votar(cpf, number);
        showVotingMenu(scanner, gerenciamentoVotacao);
      } else if (option == 2) {
        gerenciamentoVotacao.mostrarResultado();
        showVotingMenu(scanner, gerenciamentoVotacao);
      } else if (option == 3) {
        gerenciamentoVotacao.mostrarResultado();
        if (gerenciamentoVotacao.getTotalVotos() == 0) {
          showVotingMenu(scanner, gerenciamentoVotacao);
        }
        scanner.close();
      } else {
        System.out.println("Entre com uma opção válida!");
        showVotingMenu(scanner, gerenciamentoVotacao);
      }
    } catch (InputMismatchException e) {
      System.out.println("Entre com uma opção válida!");
      showVotingMenu(scanner, gerenciamentoVotacao);
    }
  }
}
