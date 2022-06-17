/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho1_itj.model;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author vinip
 */
public class Corrida {
  private int voltas;
  private int tamanhoPista;
  private float probQuebra;
  private float probAbastecimento;
  private final Armazem armazem;
  private int posicaoDisputada;
//  private final String frasePrimeiroLugar = "1 (PRIMEIRO)";
//  private final String fraseSegundoLugar = "2 (SEGUNDO)";
//  private final String fraseTerceiroLugar = "3 (TERCEIRO)";

  public Corrida(int quantCarros, int voltas, float probQuebra, float probAbastecimento) {
    int tamanhoDaPista = 20;
    if(voltas < 11)
      voltas = 11;
    armazem = new Armazem(quantCarros);
    setVoltas(voltas);
    setProbQuebra(probQuebra);
    setProbAbastecimento(probAbastecimento);
    setTamanhoPista(tamanhoDaPista);
    setPosicaoDisputada(1);
  }
  
  public void iniciarCorrida(){
    Carro[] vencedores = {null,null,null};
    Armazem verificarExistenciaDeCarros = getArmazem();
    int i = 0;
    for (; i < 3; i++)
      while(vencedores[i] == null && verificarExistenciaDeCarros.idPossiveldeCarro() != -1)
        vencedores[i] = realizaAcaoDoCarro();
    System.out.println("\n\t\t PODIO \n");
    for (i = 0; i < 3 && vencedores[i] != null; i++)
        System.out.println("\tO "+(i+1)+" colocado foi o: "+vencedores[i].getNome()+" !!!");
    for (; i < 3; i++)
        System.out.println("\tNENHUM competidor chegou em "+(i+1)+" LUGAR !!!");
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
//      String nomeCarro = carroSorteado.getNome();
      carro.subtrairKilometrosDaVolta(tamanhoVolta);
      carro.somarUmaVolta();
//  Mensagem de VOLTA REALIZADA
//      System.out.println("O "+nomeCarro+" acabou de COMPLETAR UMA VOLTA! Restam "+(quantVoltas-voltasPercorridas));
      return true;
    }
    return false;
  }
  
  public boolean verificaCarroVenceu(Carro carro){
    int quantVoltas = getVoltas();
    int voltasPercorridas = carro.getVoltasPercorridas();
    if(voltasPercorridas >= quantVoltas){
      Armazem acessoAosCarros = getArmazem();
      int colocacao = getPosicaoDisputada();
      setPosicaoDisputada(colocacao+1);
      acessoAosCarros.removerCarroDaCorrida(carro);
      /*String fraseColocacao = "";
      switch(colocacao){
        case 1 -> fraseColocacao = getFrasePrimeiroLugar();
        case 2 -> fraseColocacao = getFraseSegundoLugar();
        case 3 -> fraseColocacao = getFraseTerceiroLugar();
      }*/
//  Mensagem de VITÓRIA IMEDIATA
//        System.out.println("\n\nO "+nomeCarro+" CRUZOU A LINHA DE CHEGADA! Ficando em "+fraseColocacao+" LUGAR !!!\n\n");
      return true;
    }
    return false;
  }
  
  public void registraQuebraEFaltaCombustivel(Carro carro){
    float probQuebr = getProbQuebra();
    float probAbast = getProbAbastecimento();
    String nomeCarro = carro.getNome();
    if(carro.calcularProbabilidadeQuebra(probQuebr)){
        Armazem acessoAosCarros = getArmazem();
        System.out.println("Opa, o "+nomeCarro+" acabou de QUEBRAR! Infelizmente ele esta FORA DA CORRIDA !!\n");
        acessoAosCarros.removerCarroDaCorrida(carro);
    }
    else if(carro.calcularProbabilidadeAbastecimento(probAbast))
      System.out.println("Opa, o "+nomeCarro+" precisa ABASTECER! Ele deve que esperar o pit stop.\n");
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
    Integer idSorteado = acessoAosCarros.idPossiveldeCarro();
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

  public int getVoltas() {
    return voltas;
  }

  public final void setVoltas(int voltas) {
    this.voltas = voltas;
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
