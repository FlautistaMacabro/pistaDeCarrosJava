/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import persistencia.RegistrosVoltas;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author vinip
 */
public class Corrida {
  private int voltaAtual;
  private int tamanhoPista;
  private int posicaoDisputada;
  private float probQuebra;
  private float probAbastecimento;
  private final Armazem armazem;
  private final RegistrosVoltas registrosVoltas;
  private final String frasePrimeiroLugar = "1 (PRIMEIRO)";
  private final String fraseSegundoLugar = "2 (SEGUNDO)";
  private final String fraseTerceiroLugar = "3 (TERCEIRO)";

  public Corrida(int quantCarros, int voltas, float probQuebra, float probAbastecimento) {
    int tamanhoDaPista = 20;
    if(voltas < 11)
      voltas = 11;
    armazem = new Armazem(quantCarros);
    registrosVoltas = new RegistrosVoltas(voltas);
    setVoltaAtual(1);
    setProbQuebra(probQuebra);
    setProbAbastecimento(probAbastecimento);
    setTamanhoPista(tamanhoDaPista);
    setPosicaoDisputada(1);
  }
  
  public void iniciarCorrida(){
    while(continuarCorrida())
      realizaAcaoDoCarro();
  }
  
  public boolean continuarCorrida(){
    return (getArmazem().quantIDsPossiveisCarro() > 0);
  }
  
  public boolean verificarEstadoGeralDoCarro(Carro carro){
    if(verificaCarroDeuVolta(carro)){
      if(verificaCarroVenceu(carro))
        return true;
      registraQuebraEFaltaCombustivel(carro);
    }
    return false;
  }
  
  public boolean verificaCarroDeuVolta(Carro carro){
    int tamanhoVolta = getTamanhoPista();
    if(carro.getKilometrosRodados() >= tamanhoVolta){
//      String nomeCarro = carro.getNome();
      carro.subtrairKilometrosDaVolta(tamanhoVolta);
      carro.somarUmaVolta();
//  Mensagem de VOLTA REALIZADA
//      System.out.println("O "+nomeCarro+" acabou de COMPLETAR UMA VOLTA! Restam "+(getRegistrosVoltas().getQuantVoltas()-carro.getVoltasPercorridas()));
      int voltasPercorridasCarro = carro.getVoltasPercorridas();
      if((voltasPercorridasCarro+1) > getVoltaAtual()){
        registroEventosNaVolta(carro);
        setVoltaAtual(voltasPercorridasCarro);
      }
      return true;
    }
    return false;
  }
  
  public boolean verificaCarroVenceu(Carro carro){
    int quantVoltas = getRegistrosVoltas().getQuantVoltas();
    int voltasPercorridas = carro.getVoltasPercorridas();
    if(voltasPercorridas >= quantVoltas){
      Armazem acessoAosCarros = getArmazem();
      int colocacao = getPosicaoDisputada();
      carro.setColocacao(colocacao);
      setPosicaoDisputada(colocacao+1);
      acessoAosCarros.removerCarroDaCorrida(carro);
//      String fraseColocacao = "";
//      switch(colocacao){
//        case 1 -> fraseColocacao = getFrasePrimeiroLugar();
//        case 2 -> fraseColocacao = getFraseSegundoLugar();
//        case 3 -> fraseColocacao = getFraseTerceiroLugar();
//      }
//      String nomeCarro = carro.getNome();
//  Mensagem de VITÓRIA IMEDIATA
//      System.out.println("\n\nO "+nomeCarro+" CRUZOU A LINHA DE CHEGADA! Ficando em "+fraseColocacao+" LUGAR !!!\n\n");
      return true;
    }
    return false;
  }
  
  public void registroEventosNaVolta(Carro carro){
    Volta voltaRegistro = getRegistrosVoltas().getListaVoltasClone().get(getVoltaAtual()-1);
    ArrayList<Carro> listaCarros = getArmazem().getListaDeCarrosClone();
    // Primeiro lugar
    voltaRegistro.addListaNomesCarrosNoPodio(carro.getNome());
    Carro segundo = new Carro(-2, "");
    Carro terceiro = new Carro(-3, "");
    int tamanhoPista = getTamanhoPista();
    for(Carro car : listaCarros){
      if(!car.isEmFuncionamento())
        voltaRegistro.addListaIDsCarrosQuebraram(car.getId_carro());
      else {
        int distanciaPercorridaCarro = car.getDistanciaPercorrida(tamanhoPista);
        if(segundo.getDistanciaPercorrida(tamanhoPista) < distanciaPercorridaCarro && car.getId_carro() != carro.getId_carro()){
          terceiro.setNome(segundo.getNome());
          segundo.setNome(car.getNome());
        }
      }
    }
  }
  
  public void registraQuebraEFaltaCombustivel(Carro carro){
//    String nomeCarro = carro.getNome();
    if(calculaQuebra()){
        carro.setEmFuncionamento(false);
        getArmazem().removerCarroDaCorrida(carro);
//        System.out.println("Opa, o "+nomeCarro+" acabou de QUEBRAR! Infelizmente ele esta FORA DA CORRIDA !!\n");
    }
    else if(calculaAbastecimento()){
        carro.setComCombustivel(false);
        Volta voltaRegistro = getRegistrosVoltas().getListaVoltasClone().get(getVoltaAtual()-1);
        voltaRegistro.addListaIDsCarrosAbasteceram(carro.getId_carro());
//      System.out.println("Opa, o "+nomeCarro+" precisa ABASTECER! Ele deve que esperar o pit stop.\n");
    }
  }
  
  public boolean calculaQuebra(){
    return calculaProbabilidade(getProbQuebra());
  }
  
  public boolean calculaAbastecimento(){
    return calculaProbabilidade(getProbAbastecimento());
  }
  
  private boolean calculaProbabilidade(float porcentagem){
    Random rand = new Random();
    return rand.nextInt((int) (100/porcentagem))==0;
  }
  
  public Carro realizaAcaoDoCarro() {
    var carroSorteado = escolheCarroParaAcao();
    if(carroSorteado == null)
      return null;
    while(!carroSorteado.isComCombustivel()){
      carroSorteado.setComCombustivel(true);
      carroSorteado = escolheCarroParaAcao();
    }
    carroSorteado.somarKilometrosRodados(kilometrosPercorridos());
    if(verificarEstadoGeralDoCarro(carroSorteado))
      return carroSorteado;
    return null;
  }
 
  public Carro escolheCarroParaAcao() {
    Armazem acessoAosCarros = getArmazem();
    Integer idSorteado = acessoAosCarros.idCarroParaCorrida();
    if(idSorteado == -1)
      return null;
    ArrayList<Carro> listaCarros = acessoAosCarros.getListaDeCarrosClone();
    for(var car : listaCarros)
      if(car.getId_carro() == idSorteado)
        return car;
    return null;
  }
  
  public int kilometrosPercorridos(){
    int minimoKilometros = 1;
    int maximoKilometros = 5;
    return ((new Random()).nextInt(maximoKilometros+1-minimoKilometros))+minimoKilometros;
  }

  public Armazem getArmazem() {
    return armazem;
  }

  public int getVoltaAtual() {
    return voltaAtual;
  }

  public final void setVoltaAtual(int voltaAtual) {
    this.voltaAtual = voltaAtual;
  }

  public int getTamanhoPista() {
    return tamanhoPista;
  }

  private void setTamanhoPista(int tamanhoPista) {
    this.tamanhoPista = tamanhoPista;
  }

  public float getProbQuebra() {
    return probQuebra;
  }

  private void setProbQuebra(float probQuebra) {
    this.probQuebra = probQuebra;
  }

  public float getProbAbastecimento() {
    return probAbastecimento;
  }

  private void setProbAbastecimento(float probAbastecimento) {
    this.probAbastecimento = probAbastecimento;
  }

  public int getPosicaoDisputada() {
    return posicaoDisputada;
  }

  public RegistrosVoltas getRegistrosVoltas() {
    return registrosVoltas;
  }

  private void setPosicaoDisputada(int posicaoDisputada) {
    this.posicaoDisputada = posicaoDisputada;
  }

//  private String getFrasePrimeiroLugar() {
//    return frasePrimeiroLugar;
//  }
//
//  private String getFraseSegundoLugar() {
//    return fraseSegundoLugar;
//  }
//
//  private String getFraseTerceiroLugar() {
//    return fraseTerceiroLugar;
//  }
  
}
