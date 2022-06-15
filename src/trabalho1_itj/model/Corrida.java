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
  private final String frasePrimeiroLugar = "1º (PRIMEIRO)";
  private final String fraseSegundoLugar = "2º (SEGUNDO)";
  private final String fraseTerceiroLugar = "3º (TERCEIRO)";

  public Corrida(int quantCarros, int voltas, float probQuebra, float probAbastecimento) {
    if(voltas < 10)
      voltas = 10;
    armazem = new Armazem(quantCarros);
    setVoltas(voltas);
    setProbQuebra(probQuebra);
    setProbAbastecimento(probAbastecimento);
    setTamanhoPista(20);
    setPosicaoDisputada(1);
  }
  
  public void iniciarCorrida(){
    Carro[] vencedores = {null,null,null};
    for (int i = 0; i < 3; i++)
      while(vencedores[i] == null)
        vencedores[i] = realizaAcaoDoCarro();
    System.out.println("\n\t\t PODIO \n");
    for (int i = 0; i < 3; i++)
      System.out.println("\tO "+(i+1)+"º colocado foi o: "+vencedores[i].getNome()+" !!!");
  }
  
  public boolean verificarPassagemDeVoltasEVencedor(){
    Armazem acessoAosCarros = getArmazem();
    ArrayList<Carro> listaCarros = acessoAosCarros.getListaDeCarrosClone();
    int tamanhoVolta = getTamanhoPista();
    int quantVoltas = getVoltas();
    for(var car : listaCarros){
      if(car.getKilometrosRodados() >= tamanhoVolta){
        car.subtrairKilometrosDaVolta(tamanhoVolta);
        car.somarUmaVolta();
        int voltasPercorridas = car.getVoltasPercorridas();
        String nomeCarro = car.getNome();
        System.out.println("O "+nomeCarro+" acabou de COMPLETAR UMA VOLTA! Restam "+(quantVoltas-voltasPercorridas)+"\n");
        
        if(voltasPercorridas >= quantVoltas){
          int colocacao = getPosicaoDisputada();
          setPosicaoDisputada(colocacao+1);
          acessoAosCarros.removerCarroDaCorrida(car);
          String fraseColocacao = "";
          switch(colocacao){
            case 1 -> fraseColocacao = getFrasePrimeiroLugar();
            case 2 -> fraseColocacao = getFraseSegundoLugar();
            case 3 -> fraseColocacao = getFraseTerceiroLugar();
          }
          System.out.println("\nO "+nomeCarro+" CRUZOU A LINHA DE CHEGADA! Ficando em "+fraseColocacao+" LUGAR !!!\n\n");
          return true;
        }
      }
    }
    return false;
  }
  
  public Carro realizaAcaoDoCarro() {
    float probQuebr = getProbQuebra();
    float probAbast = getProbAbastecimento();
    var carroSorteado = escolheCarroParaAcao();
    String nomeCarro = carroSorteado.getNome();
    if(carroSorteado.calcularProbabilidadeQuebra(probQuebr))
      System.out.println("Opa, o "+nomeCarro+" acabou de QUEBRAR! Ele deve que esperar o concerto.");
    else if(carroSorteado.calcularProbabilidadeAbastecimento(probAbast))
      System.out.println("Opa, o "+nomeCarro+" precisa ABASTECER! Ele deve que esperar o pit stop.");
    else {
      carroSorteado.somarKilometrosRodados(kilometrosPercorridos());
      if(verificarPassagemDeVoltasEVencedor())
        return carroSorteado;
    }
    return null;
  }
 
  public Carro escolheCarroParaAcao() {
    Armazem acessoAosCarros = getArmazem();
    Integer idSorteado = acessoAosCarros.idPossiveldeCarro();
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
