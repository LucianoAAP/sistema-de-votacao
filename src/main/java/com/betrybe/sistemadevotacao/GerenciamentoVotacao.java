package com.betrybe.sistemadevotacao;

import java.util.ArrayList;

/**
 * Gerenciamento votação.
 */
public class GerenciamentoVotacao implements GerenciamentoVotacaoInterface {
  private ArrayList<PessoaCandidata> pessoasCandidatas = new ArrayList<PessoaCandidata>();
  private ArrayList<PessoaEleitora> pessoasEleitoras = new ArrayList<PessoaEleitora>();
  private ArrayList<String> cpfsComputados = new ArrayList<String>();

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
    return this.pessoasCandidatas.stream().map(pessoaCandidata -> pessoaCandidata.getVotos())
        .reduce(0, (acumulador, atual) -> acumulador += atual);
  }
  
  /**
   * Cadastra pessoa candidata.
   */
  public void cadastrarPessoaCandidata(String nome, int numero) {
    for (PessoaCandidata pessoaCandidata : this.pessoasCandidatas) {      
      if (pessoaCandidata.getNumero() == numero) {
        System.out.println("Número da pessoa candidata já utilizado!");
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
        break;
      }
    }
    this.cpfsComputados.add(cpfPessoaEleitora);
  }
  
  /**
   * Mostra o resultado da votação.
   */
  public void mostrarResultado() {
    if (getTotalVotos() == 0) {
      System.out.println("É preciso ter pelo menos um voto para mostrar o resultado.");
      return;
    }
    for (int index = 0; index < this.pessoasCandidatas.size(); index += 1) {
      PessoaCandidata pessoaCandidata = this.pessoasCandidatas.get(index);
      System.out.println(String.format("Nome: %s - %s votos ( %s", pessoaCandidata.getNome(),
          pessoaCandidata.getVotos(), calcularPorcentagemVotos(index)) + "% )");
    }
    System.out.println(String.format("Total de votos: %s", getTotalVotos()));
  }
  
  private double calcularPorcentagemVotos(int index) {
    double percentage = (double) pessoasCandidatas.get(index).getVotos() / getTotalVotos() * 100;
    return Math.round(percentage);
  }
}
