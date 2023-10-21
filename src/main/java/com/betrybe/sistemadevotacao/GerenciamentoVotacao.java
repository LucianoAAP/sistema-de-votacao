package com.betrybe.sistemadevotacao;

import java.util.ArrayList;

/**
 * Gerenciamento votação.
 */
public class GerenciamentoVotacao {
  private ArrayList<PessoaCandidata> pessoasCandidatas = new ArrayList<PessoaCandidata>();
  private ArrayList<PessoaEleitora> pessoasEleitoras = new ArrayList<PessoaEleitora>();
  private ArrayList<String> cpfsComputados = new ArrayList<String>();
  private int totalVotos;

  public ArrayList<PessoaCandidata> getPessoasCandidatas() {
    return pessoasCandidatas;
  }

  public void setPessoasCandidatas(ArrayList<PessoaCandidata> pessoasCandidatas) {
    this.pessoasCandidatas = pessoasCandidatas;
  }

  public ArrayList<PessoaEleitora> getPessoasEleitoras() {
    return pessoasEleitoras;
  }

  public void setPessoasEleitoras(ArrayList<PessoaEleitora> pessoasEleitoras) {
    this.pessoasEleitoras = pessoasEleitoras;
  }

  public ArrayList<String> getCpfsComputados() {
    return cpfsComputados;
  }

  public void setCpfsComputados(ArrayList<String> cpfsComputados) {
    this.cpfsComputados = cpfsComputados;
  }

  public int getTotalVotos() {
    return totalVotos;
  }

  public void setTotalVotos(int totalVotos) {
    this.totalVotos = totalVotos;
  }
  
  /**
   * Cadastra pessoa candidata.
   */
  public void cadastrarPessoaCandidata(String nome, int numero) {
    for (PessoaCandidata pessoaCandidata : this.pessoasCandidatas) {      
      if (pessoaCandidata.getNumero() == numero) {
        System.out.println("Número pessoa candidata já utilizado!");
        return;
      }
    }
    this.pessoasCandidatas.add(new PessoaCandidata(nome, numero));
  }
  
  /**
   * Cadastra pessoa eleitora.
   */
  public void cadastrarPessoaEleitora(String nome, String cpf) {
    for (PessoaEleitora pessoaEleitora : this.pessoasEleitoras) {
      if (pessoaEleitora.getCpf().equals(cpf)) {
        System.out.println("Pessoa eleitora já cadastrada!");
        return;
      }
    }
    this.pessoasEleitoras.add(new PessoaEleitora(nome, cpf));
  }
  
  /**
   * Registra voto.
   */
  public void votar(String cpfPessoaEleitora, int numeroPessoaCandidata) {
    if (this.cpfsComputados.contains(cpfPessoaEleitora)) {
      System.out.println("Pessoa eleitora já votou!");
      return;
    }
    for (PessoaCandidata pessoaCandidata : this.pessoasCandidatas) {
      if (pessoaCandidata.getNumero() == numeroPessoaCandidata) {
        pessoaCandidata.receberVoto();
        System.out.println(pessoaCandidata.getVotos());
        break;
      }
    }
    this.cpfsComputados.add(cpfPessoaEleitora);
    this.totalVotos += 1;
  }
  
  /**
   * Mostra o resultado da votação.
   */
  public void mostrarResultado() {
    if (this.totalVotos == 0) {
      System.out.println("É preciso ter pelo menos um voto para mostrar o resultado.");
      return;
    }
    for (int index = 0; index < this.pessoasCandidatas.size(); index += 1) {
      PessoaCandidata pessoaCandidata = this.pessoasCandidatas.get(index);
      System.out.println(String.format("Nome: %s - %s votos ( %s", pessoaCandidata.getNome(),
          pessoaCandidata.getVotos(), calcularPorcentagemVotos(index)) + "% )");
    }
    System.out.println(String.format("Total de votos: %s", this.totalVotos));
  }
  
  private double calcularPorcentagemVotos(int index) {
    double percentage = (double) pessoasCandidatas.get(index).getVotos() / this.totalVotos * 100;
    return Math.round(percentage);
  }
}
