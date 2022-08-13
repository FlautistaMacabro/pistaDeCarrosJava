/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.ArrayList;
import model.Carro;
import model.Corrida;

/**
 *
 * @author vinip
 */
public class RegistrosGerais {
  private Corrida corrida;

  public RegistrosGerais(int quantCarros, int voltas, float probQuebra, float probAbastecimento) throws CloneNotSupportedException, InterruptedException {
    Corrida corridaAux = new Corrida(quantCarros, voltas, probQuebra, probAbastecimento);
    setCorrida(corridaAux);
    realizarCorrida();
  }

  public final Corrida getCorrida() {
    return corrida;
  }

  private void setCorrida(Corrida corrida) {
    this.corrida = corrida;
  }
  
  private void realizarCorrida() throws CloneNotSupportedException, InterruptedException{
    getCorrida().iniciarCorrida();
  }
  
  public ArrayList<Carro> getListaCarrosOrdenadosColocacao() throws CloneNotSupportedException {
    ArrayList<Carro> listaCarrosOrdenados = (getCorrida().getRegistrosCarros()).getCarrosOrdenadosColocacao();
    ArrayList<Carro> listaNomesCarros = new ArrayList<>();
    for(Carro carro : listaCarrosOrdenados)
      listaNomesCarros.add(carro.clone());
    return listaNomesCarros;
  }
  
  public ArrayList<Carro> getListaCarrosVencedoresOrdenadosColocacao() throws CloneNotSupportedException {
    ArrayList<Carro> listaCarrosOrdenados = (getCorrida().getRegistrosCarros()).getCarrosOrdenadosColocacao();
    ArrayList<Carro> listaNomesCarros = new ArrayList<>();
    Carro inexistente = new Carro(-1, "Inexistente");
    for(Carro carro : listaCarrosOrdenados)
      if(carro.isEmFuncionamento())
        listaNomesCarros.add(carro.clone());
    int i = listaNomesCarros.size();
    for (; i < 3; i++)
      listaNomesCarros.add(inexistente);
    return listaNomesCarros;
  }
  
  public ArrayList<Carro> getListaCarrosVencedoresPodio() throws CloneNotSupportedException {
    ArrayList<Carro> listaCarrosOrdenados = (getCorrida().getRegistrosCarros()).getCarrosOrdenadosColocacao();
    ArrayList<Carro> listaNomesCarros = new ArrayList<>();
    Carro inexistente = new Carro(-1, "Inexistente");
    for(Carro carro : listaCarrosOrdenados)
      if(carro.isEmFuncionamento())
        listaNomesCarros.add(carro.clone());
    int i = listaNomesCarros.size();
    for (; i < 3; i++)
      listaNomesCarros.add(inexistente);
    return (ArrayList<Carro>) listaNomesCarros.subList(0, 3);
  }
  
  public ArrayList<String> getListaStringCarrosOrdenadosColocacao() throws CloneNotSupportedException {
    ArrayList<Carro> listaCarrosOrdenados = getListaCarrosOrdenadosColocacao();
    ArrayList<String> listaStatusString = new ArrayList<>();
    int tam = listaCarrosOrdenados.size();
    int i = 0;
    int tamanhoPista = getCorrida().getTamanhoPista();
    for(; i < tam; i++){
      Carro car = listaCarrosOrdenados.get(i);
      if(car.isEmFuncionamento())
        listaStatusString.add((i+1)+"º LUGAR: "+car.getNome()+"\n");
      else break;
    }
    for(; i < tam; i++){
      Carro car = listaCarrosOrdenados.get(i);
      listaStatusString.add(car.getNome()+": QUEBROU. Completou "+car.getVoltasPercorridas()+" voltas e percorreu "+car.getDistanciaPercorrida(tamanhoPista)+" km\n");
    }
    return listaStatusString;
  }
  
//  public void imprimirPosicoes() throws CloneNotSupportedException {
//    ArrayList<Carro> listaCarrosOrdenados = getListaCarrosOrdenadosColocacao();
//    System.out.println("\t CLASSIFICACAO \n\n");
//    int tam = listaCarrosOrdenados.size();
//    for (int i = 0; i < tam; i++)
//      System.out.println("\t"+(i+1)+" lugar: "+listaCarrosOrdenados.get(i).getNome());
//  }
  
}
