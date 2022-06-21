/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author vinip
 */
public class Carro implements Comparable<Carro>, Cloneable{
  private String nome;
  private int id_carro;
  private int kilometrosRodados;
  private int voltasPercorridas;
  private int colocacao;
  private boolean comCombustivel;
  private boolean emFuncionamento;
  
  public Carro(int id_carro, String nome) {
    setId_carro(id_carro);
    setNome(nome);
    setKilometrosRodados(0);
    setVoltasPercorridas(0);
    setColocacao(0);
    setComCombustivel(true);
    setEmFuncionamento(true);
  }
  
  public Carro(int id_carro, String nome, int colocacao) {
    setId_carro(id_carro);
    setNome(nome);
    setKilometrosRodados(0);
    setVoltasPercorridas(0);
    setColocacao(colocacao);
    setComCombustivel(true);
    setEmFuncionamento(true);
  }

  /*public Carro(int id_carro, String nome, boolean comCombustivel, boolean emFuncionamento) {
    setId_carro(id_carro);
    setNome(nome);
    setKilometrosRodados(0);
    setKilometrosRodados(0);
    setComCombustivel(comCombustivel);
//    setEmFuncionamento(emFuncionamento);
  }
  
  public Carro(int id_carro, String nome, boolean comCombustivel) {
    setId_carro(id_carro);
    setNome(nome);
    setKilometrosRodados(0);
    setVoltasPercorridas(0);
    setComCombustivel(comCombustivel);
//    setEmFuncionamento(true);
  }
  
  public Carro(String nome, int id_carro, boolean emFuncionamento) {
    setId_carro(id_carro);
    setNome(nome);
    setKilometrosRodados(0);
    setVoltasPercorridas(0);
    setComCombustivel(true);
//    setEmFuncionamento(emFuncionamento);
  }*/

  public int getId_carro() {
    return id_carro;
  }

  private void setId_carro(int id_carro) {
    this.id_carro = id_carro;
  }

  public String getNome() {
    return nome;
  }

  public final void setNome(String nome) {
    this.nome = nome;
  }

  public int getKilometrosRodados() {
    return kilometrosRodados;
  }

  private void setKilometrosRodados(int kilometrosRodados) {
    this.kilometrosRodados = kilometrosRodados;
  }

  public int getVoltasPercorridas() {
    return voltasPercorridas;
  }

  private void setVoltasPercorridas(int voltasPercorridas) {
    this.voltasPercorridas = voltasPercorridas;
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

  public int getColocacao() {
    return colocacao;
  }

  public final void setColocacao(int colocacao) {
    this.colocacao = colocacao;
  }
  
  public void somarKilometrosRodados(int kilometrosExtras){
    setKilometrosRodados(getKilometrosRodados()+kilometrosExtras);
  }
  
  public void subtrairKilometrosDaVolta(int kilometrosDaVolta) {
    setKilometrosRodados(getKilometrosRodados()-kilometrosDaVolta);
  }
  
  public void somarUmaVolta(){
    setVoltasPercorridas(getVoltasPercorridas()+1);
  }
  
  public int getDistanciaPercorrida(int tamanhoPista){
    return (tamanhoPista * getVoltasPercorridas()) + getKilometrosRodados();
  }

  @Override
  public int compareTo(Carro carroComparar) {
    return getColocacao() - carroComparar.getColocacao();
  }
  
  @Override
  public Carro clone() throws CloneNotSupportedException{
    return (Carro) super.clone();
  }
  
}
