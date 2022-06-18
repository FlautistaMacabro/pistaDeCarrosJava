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

  public RegistrosGerais(int quantCarros, int voltas, float probQuebra, float probAbastecimento) {
    Corrida corridaAux = new Corrida(5, 11, 5, 20);
    setCorrida(corridaAux);
    realizarCorrida();
  }

  public final Corrida getCorrida() {
    return corrida;
  }

  private void setCorrida(Corrida corrida) {
    this.corrida = corrida;
  }
  
  private void realizarCorrida(){
    getCorrida().iniciarCorrida();
  }
  
  public ArrayList<String> getColocacaoFinal() {
    ArrayList<Carro> listaCarrosOrdenados = (getCorrida().getArmazem()).getCarrosOrdenadosColocacao();
    ArrayList<String> listaNomesCarros = new ArrayList<>();
    for(Carro carro : listaCarrosOrdenados)
      if(carro.isEmFuncionamento())
        listaNomesCarros.add(carro.getNome());
    return listaNomesCarros;
  }
  
//  public void imprimirPosicoes() {
//    ArrayList<String> listaCarrosOrdenados = getColocacaoFinal();
//    System.out.println("\t CLASSIFICACAO \n\n");
//    int tam = listaCarrosOrdenados.size();
//    for (int i = 0; i < tam; i++)
//      System.out.println("\t"+(i+1)+" lugar: "+listaCarrosOrdenados.get(i));
//  }
  
}
