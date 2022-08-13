/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.RegistrosCarros;
import persistencia.RegistrosVoltas;

/**
 *
 * @author vinip
 */
public class CarroEmCorrida extends Thread{
  
  private Corrida corridaAtual;
  private Carro carroBase;
  private final CountDownLatch startSignal;
  private final CountDownLatch doneSignal;
  
  public CarroEmCorrida(Carro carro, Corrida corrida, CountDownLatch startSignal, CountDownLatch doneSignal) {
    this.startSignal = startSignal;
    this.doneSignal = doneSignal;
    setCarroBase(carro);
    setCorridaAtual(corrida);
  }
  
  @Override
  public void run() {
    try {
      getStartSignal().await();
      largada();
      getDoneSignal().countDown();
    } catch (CloneNotSupportedException | InterruptedException ex) {
      Logger.getLogger(CarroEmCorrida.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  private void largada() throws CloneNotSupportedException, InterruptedException {
    //System.out.println("startou");
    Carro carroBase = getCarroBase();
    while(carroBase.isEmFuncionamento() && getCorridaAtual().getVoltaAtual() > carroBase.getVoltasPercorridas()){
      //System.out.println("loop");
      if(!carroBase.isComCombustivel()){
        Thread.sleep(10);
        carroBase.setComCombustivel(true);
      }
      carroBase.somarKilometrosRodados(kilometrosPercorridos());
      if(verificarEstadoGeralDoCarro(carroBase))
        break;
    }
  }
  
  public int kilometrosPercorridos(){
    int minimoKilometros = 1;
    int maximoKilometros = 5;
    return ((new Random()).nextInt(maximoKilometros+1-minimoKilometros))+minimoKilometros;
  }
  
  public synchronized boolean verificarEstadoGeralDoCarro(Carro carro) throws CloneNotSupportedException{
    if(verificaCarroDeuVolta(carro)){
      if(verificaCarroVenceu(carro))
        return true;
      registraQuebraEFaltaCombustivel(carro);
    }
    return false;
  }
  
  public boolean verificaCarroDeuVolta(Carro carro) throws CloneNotSupportedException{
    var corridaAtual = getCorridaAtual();
    int tamanhoVolta = corridaAtual.getTamanhoPista();
    if(carro.getKilometrosRodados() >= tamanhoVolta){
//      String nomeCarro = carro.getNome();
      carro.subtrairKilometrosDaVolta(tamanhoVolta);
      carro.somarUmaVolta();
      RegistrosVoltas registrosVoltas = corridaAtual.getRegistrosVoltas();
      int voltaAtual = corridaAtual.getVoltaAtual();
      int voltasPercorridasCarro = carro.getVoltasPercorridas()+1;
      Volta voltaRegistro = registrosVoltas.getListaVoltasClone().get(voltaAtual-1);
      if((voltasPercorridasCarro) > voltaAtual){
        registroEventosNaVolta(carro);
        if(voltasPercorridasCarro <= registrosVoltas.getQuantVoltas()){
          corridaAtual.setVoltaAtual(voltasPercorridasCarro);
          voltaAtual = corridaAtual.getVoltaAtual();
          voltaRegistro = registrosVoltas.getListaVoltasClone().get(voltaAtual-2);
        }
      }
      String nomeCarro = carro.getNome();
      voltaRegistro.addListaEventosGerais("O "+nomeCarro+" COMPLETOU A "+(voltasPercorridasCarro-1)+"º VOLTA!\n");
      voltaRegistro.addListaStatusCarros("AVANÇAR,"+nomeCarro.charAt(nomeCarro.length()-1)+",\n");
//  Mensagem de VOLTA REALIZADA
//      System.out.println("O "+nomeCarro+" acabou de COMPLETAR UMA VOLTA! Restam "+(getRegistrosVoltas().getQuantVoltas()-carro.getVoltasPercorridas()));
      return true;
    }
    return false;
  }
  
  public void registroEventosNaVolta(Carro carro) throws CloneNotSupportedException{
    var corridaAtual = getCorridaAtual();
    Volta voltaRegistro = corridaAtual.getRegistrosVoltas().getListaVoltasClone().get(corridaAtual.getVoltaAtual()-1);
    ArrayList<Carro> listaCarros = corridaAtual.getRegistrosCarros().getListaDeCarrosRealClone();
    // Primeiro lugar
    voltaRegistro.addListaCarrosNoPodio(carro);
    Carro inexistente = new Carro(-1, "Inexistente");
    Carro segundo = inexistente;
    Carro terceiro = inexistente;
    int tamanhoPista = corridaAtual.getTamanhoPista();
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
  
  public boolean verificaCarroVenceu(Carro carro){
    var corridaAtual = getCorridaAtual();
    int quantVoltas = corridaAtual.getRegistrosVoltas().getQuantVoltas();
    int voltasPercorridas = carro.getVoltasPercorridas();
    if(voltasPercorridas >= quantVoltas){
      RegistrosCarros acessoAosCarros = corridaAtual.getRegistrosCarros();
      int colocacao = corridaAtual.getPosicaoDisputada();
      int voltaAtual = corridaAtual.getVoltaAtual();
      Volta voltaRegistro = corridaAtual.getRegistrosVoltas().getListaVoltasClone().get(voltaAtual-1);
      String nomeCarro = carro.getNome();
      carro.setColocacao(colocacao);
      if(colocacao > 0 && colocacao < 4)
        voltaRegistro.getListaCarrosNoPodioClone().set(colocacao-1, carro);
      corridaAtual.setPosicaoDisputada(colocacao+1);
      voltaRegistro.addListaEventosGerais("O "+nomeCarro+" CRUZOU A LINHA DE CHEGADA !!!\n");
      voltaRegistro.addListaStatusCarros("TERMINOU,"+nomeCarro.charAt(nomeCarro.length()-1)+"\n");
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
  
  public void registraQuebraEFaltaCombustivel(Carro carro) throws CloneNotSupportedException{
//    String nomeCarro = carro.getNome();
    var corridaAtual = getCorridaAtual();
    Volta voltaRegistro = corridaAtual.getRegistrosVoltas().getListaVoltasClone().get(corridaAtual.getVoltaAtual()-1);
    if(calculaQuebra()){
        String nomeCarro = carro.getNome();
        carro.setEmFuncionamento(false);
        voltaRegistro.addListaEventosGerais("O "+nomeCarro+" QUEBROU! Infelizmente ele SAIU DA CORRIDA !!\n");
        voltaRegistro.addListaStatusCarros("QUEBROU,"+nomeCarro.charAt(nomeCarro.length()-1)+",\n");
        corridaAtual.getRegistrosCarros().removerCarroDaCorrida(carro);
//        System.out.println("Opa, o "+nomeCarro+" acabou de QUEBRAR! Infelizmente ele esta FORA DA CORRIDA !!\n");
    }
    else if(calculaAbastecimento()){
        String nomeCarro = carro.getNome();
        carro.setComCombustivel(false);
        voltaRegistro.addListaAbastecimentosCarros(carro);
        voltaRegistro.addListaEventosGerais("O "+nomeCarro+" ABASTECEU! Ele teve que esperar o pit stop.\n");
        voltaRegistro.addListaStatusCarros("ABASTECEU,"+nomeCarro.charAt(nomeCarro.length()-1)+",\n");
//      System.out.println("Opa, o "+nomeCarro+" precisa ABASTECER! Ele deve que esperar o pit stop.\n");
    }
  }
  
  public boolean calculaQuebra(){
    return calculaProbabilidade(getCorridaAtual().getProbQuebra());
  }
  
  public boolean calculaAbastecimento(){
    return calculaProbabilidade(getCorridaAtual().getProbAbastecimento());
  }
  
  private boolean calculaProbabilidade(float porcentagem){
    Random rand = new Random();
    return rand.nextInt((int) (100/porcentagem))==0;
  }

  public Corrida getCorridaAtual() {
    return corridaAtual;
  }

  public final void setCorridaAtual(Corrida corridaAtual) {
    this.corridaAtual = corridaAtual;
  }

  public Carro getCarroBase() {
    return carroBase;
  }

  public final void setCarroBase(Carro carroBase) {
    this.carroBase = carroBase;
  }

  public CountDownLatch getStartSignal() {
    return startSignal;
  }

  public CountDownLatch getDoneSignal() {
    return doneSignal;
  }
  
}
