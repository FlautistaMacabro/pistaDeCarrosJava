/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import persistencia.RegistrosCarros;
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
  private final RegistrosCarros armazem;
  private final RegistrosVoltas registrosVoltas;
//  private final String frasePrimeiroLugar = "1 (PRIMEIRO)";
//  private final String fraseSegundoLugar = "2 (SEGUNDO)";
//  private final String fraseTerceiroLugar = "3 (TERCEIRO)";

  public Corrida(int quantCarros, int voltas, float probQuebra, float probAbastecimento) {
    int tamanhoDaPista = 20;
    if(voltas < 11)
      voltas = 11;
    armazem = new RegistrosCarros(quantCarros);
    registrosVoltas = new RegistrosVoltas(voltas);
    setVoltaAtual(1);
    setProbQuebra(probQuebra);
    setProbAbastecimento(probAbastecimento);
    setTamanhoPista(tamanhoDaPista);
    setPosicaoDisputada(1);
  }
  
  public void iniciarCorrida() throws CloneNotSupportedException{
    while(continuarCorrida())
      realizaAcaoDoCarro();
  }
  
  public boolean continuarCorrida(){
    return (getRegistrosCarros().quantIDsPossiveisCarro() > 0);
  }
  
  public boolean verificarEstadoGeralDoCarro(Carro carro) throws CloneNotSupportedException{
    if(verificaCarroDeuVolta(carro)){
      if(verificaCarroVenceu(carro))
        return true;
      registraQuebraEFaltaCombustivel(carro);
    }
    return false;
  }
  
  public boolean verificaCarroDeuVolta(Carro carro) throws CloneNotSupportedException{
    int tamanhoVolta = getTamanhoPista();
    if(carro.getKilometrosRodados() >= tamanhoVolta){
//      String nomeCarro = carro.getNome();
      carro.subtrairKilometrosDaVolta(tamanhoVolta);
      carro.somarUmaVolta();
      RegistrosVoltas registrosVoltas = getRegistrosVoltas();
      int voltaAtual = getVoltaAtual();
      int voltasPercorridasCarro = carro.getVoltasPercorridas()+1;
      Volta voltaRegistro = registrosVoltas.getListaVoltasClone().get(voltaAtual-1);
      if((voltasPercorridasCarro) > voltaAtual){
        registroEventosNaVolta(carro);
        if(voltasPercorridasCarro <= registrosVoltas.getQuantVoltas()){
          setVoltaAtual(voltasPercorridasCarro);
          voltaAtual = getVoltaAtual();
          voltaRegistro = registrosVoltas.getListaVoltasClone().get(voltaAtual-2);
        }
      }
      voltaRegistro.addListaEventosGerais("O "+carro.getNome()+" COMPLETOU A "+(voltasPercorridasCarro-1)+"º VOLTA!\n");
//  Mensagem de VOLTA REALIZADA
//      System.out.println("O "+nomeCarro+" acabou de COMPLETAR UMA VOLTA! Restam "+(getRegistrosVoltas().getQuantVoltas()-carro.getVoltasPercorridas()));
      return true;
    }
    return false;
  }
  
  public boolean verificaCarroVenceu(Carro carro){
    int quantVoltas = getRegistrosVoltas().getQuantVoltas();
    int voltasPercorridas = carro.getVoltasPercorridas();
    if(voltasPercorridas >= quantVoltas){
      RegistrosCarros acessoAosCarros = getRegistrosCarros();
      int colocacao = getPosicaoDisputada();
      int voltaAtual = getVoltaAtual();
      Volta voltaRegistro = getRegistrosVoltas().getListaVoltasClone().get(voltaAtual-1);
      carro.setColocacao(colocacao);
      if(colocacao > 0 && colocacao < 4)
        voltaRegistro.getListaCarrosNoPodioClone().set(colocacao-1, carro);
      setPosicaoDisputada(colocacao+1);
      acessoAosCarros.removerCarroDaCorrida(carro);
      voltaRegistro.addListaEventosGerais("O "+carro.getNome()+" CRUZOU A LINHA DE CHEGADA !!!\n");
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
  
  public void registroEventosNaVolta(Carro carro) throws CloneNotSupportedException{
    Volta voltaRegistro = getRegistrosVoltas().getListaVoltasClone().get(getVoltaAtual()-1);
    ArrayList<Carro> listaCarros = getRegistrosCarros().getListaDeCarrosRealClone();
    // Primeiro lugar
    voltaRegistro.addListaCarrosNoPodio(carro);
    Carro inexistente = new Carro(-1, "Inexistente");
    Carro segundo = inexistente;
    Carro terceiro = inexistente;
    int tamanhoPista = getTamanhoPista();
    for(Carro car : listaCarros){
      if(!car.isEmFuncionamento())
        voltaRegistro.addListaCarrosQuebraram(car);
      else {
        int distanciaPercorridaCarro = car.getDistanciaPercorrida(tamanhoPista);
        if(segundo.getDistanciaPercorrida(tamanhoPista) < distanciaPercorridaCarro && car.getId_carro() != carro.getId_carro()){
          terceiro = segundo;
          segundo = car;
        }
      }
    }
    if(segundo.getId_carro() == terceiro.getId_carro())
      terceiro = inexistente;
    voltaRegistro.addListaCarrosNoPodio(segundo);
    voltaRegistro.addListaCarrosNoPodio(terceiro);
  }
  
  public void registraQuebraEFaltaCombustivel(Carro carro) throws CloneNotSupportedException{
//    String nomeCarro = carro.getNome();
    Volta voltaRegistro = getRegistrosVoltas().getListaVoltasClone().get(getVoltaAtual()-1);
    if(calculaQuebra()){
        carro.setEmFuncionamento(false);
        getRegistrosCarros().removerCarroDaCorrida(carro);
        voltaRegistro.addListaEventosGerais("O "+carro.getNome()+" QUEBROU! Infelizmente ele SAIU DA CORRIDA !!\n");
//        System.out.println("Opa, o "+nomeCarro+" acabou de QUEBRAR! Infelizmente ele esta FORA DA CORRIDA !!\n");
    }
    else if(calculaAbastecimento()){
        carro.setComCombustivel(false);
        voltaRegistro.addListaAbastecimentosCarros(carro);
        voltaRegistro.addListaEventosGerais("O "+carro.getNome()+" ABASTECEU! Ele teve que esperar o pit stop.\n");
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
  
  public Carro realizaAcaoDoCarro() throws CloneNotSupportedException {
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
    RegistrosCarros acessoAosCarros = getRegistrosCarros();
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

  public RegistrosCarros getRegistrosCarros() {
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
