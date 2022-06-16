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
  private final String frasePrimeiroLugar = "1 (PRIMEIRO)";
  private final String fraseSegundoLugar = "2 (SEGUNDO)";
  private final String fraseTerceiroLugar = "3 (TERCEIRO)";

  public Corrida(int quantCarros, int voltas, float probQuebra, float probAbastecimento) {
    if(voltas < 11)
      voltas = 11;
    armazem = new Armazem(quantCarros);
    setVoltas(voltas);
    setProbQuebra(probQuebra);
    setProbAbastecimento(probAbastecimento);
    setTamanhoPista(20);
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
    for (i = 0; i < 3; i++)
      if(vencedores[i] != null)
        System.out.println("\tO "+(i+1)+" colocado foi o: "+vencedores[i].getNome()+" !!!");
      else break;
    for (; i < 3; i++)
        System.out.println("\tNENHUM competidor chegou em "+(i+1)+" LUGAR !!!");
  }
  
  public boolean verificarEstadoDoCarro(Carro carroSorteado){
    int tamanhoVolta = getTamanhoPista();
    int quantVoltas = getVoltas();
    if(carroSorteado.getKilometrosRodados() >= tamanhoVolta){
      int voltasPercorridas = carroSorteado.getVoltasPercorridas();
      float probQuebr = getProbQuebra();
      float probAbast = getProbAbastecimento();
      String nomeCarro = carroSorteado.getNome();
      carroSorteado.subtrairKilometrosDaVolta(tamanhoVolta);
      carroSorteado.somarUmaVolta();
      System.out.println("O "+nomeCarro+" acabou de COMPLETAR UMA VOLTA! Restam "+(quantVoltas-voltasPercorridas));
      
      if(voltasPercorridas >= quantVoltas){
        Armazem acessoAosCarros = getArmazem();
        int colocacao = getPosicaoDisputada();
        setPosicaoDisputada(colocacao+1);
        acessoAosCarros.removerCarroDaCorrida(carroSorteado);
        String fraseColocacao = "";
        switch(colocacao){
          case 1 -> fraseColocacao = getFrasePrimeiroLugar();
          case 2 -> fraseColocacao = getFraseSegundoLugar();
          case 3 -> fraseColocacao = getFraseTerceiroLugar();
        }
//        Mensagem de vitória imediada
        System.out.println("\n\nO "+nomeCarro+" CRUZOU A LINHA DE CHEGADA! Ficando em "+fraseColocacao+" LUGAR !!!\n\n");
        return true;
      }
      
      if(carroSorteado.calcularProbabilidadeQuebra(probQuebr)){
        Armazem acessoAosCarros = getArmazem();
        System.out.println("Opa, o "+nomeCarro+" acabou de QUEBRAR! Infelizmente ele esta FORA DA CORRIDA !!\n");
        acessoAosCarros.removerCarroDaCorrida(carroSorteado);
      }
      else if(carroSorteado.calcularProbabilidadeAbastecimento(probAbast))
        System.out.println("Opa, o "+nomeCarro+" precisa ABASTECER! Ele deve que esperar o pit stop.\n");
    }
    return false;
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
    if(verificarEstadoDoCarro(carroSorteado))
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
    Random rand = new Random();
    return (rand.nextInt(4))+1;
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

  private String getFrasePrimeiroLugar() {
    return frasePrimeiroLugar;
  }

  private String getFraseSegundoLugar() {
    return fraseSegundoLugar;
  }

  private String getFraseTerceiroLugar() {
    return fraseTerceiroLugar;
  }
  
}
