/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho1_itj.model;

import java.util.Random;

/**
 *
 * @author vinip
 */
public class Carro {
  private int id_carro;
  private String nome;
  private boolean comCombustivel;
  private boolean emFuncionamento;

  public Carro(int id_carro, String nome, boolean comCombustivel, boolean emFuncionamento) {
    setId_carro(id_carro);
    setNome(nome);
    setComCombustivel(comCombustivel);
    setEmFuncionamento(emFuncionamento);
  }
  
  public Carro(int id_carro, String nome, boolean comCombustivel) {
    setId_carro(id_carro);
    setNome(nome);
    setComCombustivel(comCombustivel);
    setEmFuncionamento(true);
  }
  
  public Carro(String nome, int id_carro, boolean emFuncionamento) {
    setId_carro(id_carro);
    setNome(nome);
    setComCombustivel(true);
    setEmFuncionamento(emFuncionamento);
  }
  
  public Carro(int id_carro, String nome) {
    setId_carro(id_carro);
    setNome(nome);
    setComCombustivel(true);
    setEmFuncionamento(true);
  }

  public int getId_carro() {
    return id_carro;
  }

  public final void setId_carro(int id_carro) {
    this.id_carro = id_carro;
  }

  public String getNome() {
    return nome;
  }

  public final void setNome(String nome) {
    this.nome = nome;
  }

  public final boolean isComCombustivel() {
    return comCombustivel;
  }

  public final void setComCombustivel(boolean comCombustivel) {
    this.comCombustivel = comCombustivel;
  }

  public final boolean isEmFuncionamento() {
    return emFuncionamento;
  }

  public final void setEmFuncionamento(boolean emFuncionamento) {
    this.emFuncionamento = emFuncionamento;
  }
  
  public boolean calcularProbabilidadeQuebra(float porcentagem){
    setEmFuncionamento(calcularProbabilidade(porcentagem));
    return isEmFuncionamento();
  }
  
  public boolean calcularProbabilidadeAbastecimento(float porcentagem){
    setComCombustivel(calcularProbabilidade(porcentagem));
    return isComCombustivel();
  }
  
  public boolean calcularProbabilidade(float porcentagem){
    Random rand = new Random();
    return rand.nextInt((int) (100/porcentagem))==0;
  }
  
}
