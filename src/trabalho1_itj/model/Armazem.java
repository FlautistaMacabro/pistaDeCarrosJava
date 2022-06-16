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
public class Armazem {
  private final ArrayList<Carro> listaDeCarros;
  private final ArrayList<Integer> idsPossiveis;
  private int quantCarros;

  public Armazem(int quantCarros) {
    listaDeCarros = new ArrayList<>();
    idsPossiveis = new ArrayList<>();
    setQuantCarros(quantCarros);
    cadastrarIDs();
    cadastrarListaCarros();
    cadastrarIDs();
  }
  
  public void imprimirListaCarros() {
    var listaCarros = getListaDeCarrosClone();
    for(var car : listaCarros){
      System.out.println("ID: "+car.getId_carro()+" Nome: "+car.getNome());
    }
  }
  
  private void cadastrarListaCarros(){
    int quant = getQuantCarros();
    var listaCarros = getListaDeCarros();
    ArrayList<Integer> listaIds = getIdsPossiveis();
    for (int i = 0; i < quant; i++) {
      Integer index = idPossiveldeCarro();
      Carro car = new Carro(index,"Carro "+(i+1));
      listaCarros.add(car);
      listaIds.remove(index);
    }
  }
  
  private void cadastrarIDs(){
    int quant = getQuantCarros();
    ArrayList<Integer> listaIds = getIdsPossiveis();
    for (int i = 0; i < quant; i++) {
      listaIds.add(i);
    }
  }
  
  public Integer idPossiveldeCarro() {
    ArrayList<Integer> listaIds = getIdsPossiveis();
    if(listaIds.isEmpty())
      return -1;
    int quant = getQuantCarros();
    Random rand = new Random();
    Integer index = rand.nextInt(quant);
    while(!listaIds.contains(index))
      index = rand.nextInt(quant);
    return index;
  }
  
  public void removerCarroDaCorrida(Carro car){
    var listaCarros = getListaDeCarros();
    var listaIds = getIdsPossiveis();
    Integer idCarro = car.getId_carro();
    listaCarros.remove(car);
    listaIds.remove(idCarro);
  }

  private ArrayList<Integer> getIdsPossiveis() {
    return idsPossiveis;
  }

  private ArrayList<Carro> getListaDeCarros() {
    return listaDeCarros;
  }
  
  public ArrayList<Carro> getListaDeCarrosClone(){
    ArrayList<Carro> carrosListaClone = new ArrayList(getListaDeCarros());
    return carrosListaClone;
  }

  public int getQuantCarros() {
    return quantCarros;
  }

  public final void setQuantCarros(int quantCarros) {
    this.quantCarros = quantCarros;
  }
  
}
