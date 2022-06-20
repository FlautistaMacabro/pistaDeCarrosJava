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

  public RegistrosGerais(int quantCarros, int voltas, float probQuebra, float probAbastecimento) throws CloneNotSupportedException {
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
  
  private void realizarCorrida() throws CloneNotSupportedException{
    getCorrida().iniciarCorrida();
  }
  
  public ArrayList<Carro> getListaCarrosOrdenadosColocacao() throws CloneNotSupportedException {
    ArrayList<Carro> listaCarrosOrdenados = (getCorrida().getRegistrosCarros()).getCarrosOrdenadosColocacao();
    ArrayList<Carro> listaNomesCarros = new ArrayList<>();
    for(Carro carro : listaCarrosOrdenados)
      if(carro.isEmFuncionamento())
        listaNomesCarros.add(carro.clone());
    return listaNomesCarros;
  }
  
//  public void imprimirPosicoes() throws CloneNotSupportedException {
//    ArrayList<Carro> listaCarrosOrdenados = getListaCarrosOrdenadosColocacao();
//    System.out.println("\t CLASSIFICACAO \n\n");
//    int tam = listaCarrosOrdenados.size();
//    for (int i = 0; i < tam; i++)
//      System.out.println("\t"+(i+1)+" lugar: "+listaCarrosOrdenados.get(i).getNome());
//  }
  
}
