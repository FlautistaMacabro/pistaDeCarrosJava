/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import model.Carro;

/**
 *
 * @author vinip
 */
public class RegistrosCarros {
  private final ArrayList<Carro> listaDeCarros;
  private final ArrayList<Integer> idsPossiveis;
  private int quantCarros;

  public RegistrosCarros(int quantCarros) {
    listaDeCarros = new ArrayList<>();
    idsPossiveis = new ArrayList<>();
    setQuantCarros(quantCarros);
    cadastrarIDs();
    cadastrarListaCarros();
    cadastrarIDs();
  }
  
  private void cadastrarListaCarros(){
    int quant = getQuantCarros();
    var listaCarros = getListaDeCarros();
    ArrayList<Integer> listaIds = getIdsPossiveis();
    for (int i = 0; i < quant; i++)
      listaCarros.add(new Carro(idCarroParaCriacao(),"Carro "+(i+1)));
  }
  
  private void cadastrarIDs(){
    int quant = getQuantCarros();
    ArrayList<Integer> listaIds = getIdsPossiveis();
    for (int i = 0; i < quant; i++) {
      listaIds.add(i);
    }
  }
  
  public Integer idCarroParaCriacao() {
    ArrayList<Integer> listaIds = getIdsPossiveis();
    Integer index = idCarroAleatorio();
    listaIds.remove(index);
    return index;
  }
  
  public Integer idCarroParaCorrida() {
    ArrayList<Integer> listaIds = getIdsPossiveis();
    if(listaIds.isEmpty())
      return -1;
    return idCarroAleatorio();
  }
  
  public Integer idCarroAleatorio() {
    ArrayList<Integer> listaIds = getIdsPossiveis();
    int quant = getQuantCarros();
    Random rand = new Random();
    Integer index = rand.nextInt(quant);
    while(!listaIds.contains(index))
      index = rand.nextInt(quant);
    return index;
  }
  
  public void removerCarroDaCorrida(Carro car){
    var listaIds = getIdsPossiveis();
    Integer idCarro = car.getId_carro();
    listaIds.remove(idCarro);
  }
  
  public int quantIDsPossiveisCarro(){
    return getIdsPossiveis().size();
  }
  
//  public Carro buscaCarroPorID(int idCarro){
//    var listaCarros = getListaDeCarrosClone();
//    for(Carro carro : listaCarros)
//      if(carro.getId_carro() == idCarro)
//        return carro;
//    return null;
//  }
  
//  public boolean verificaCarroCruzouALinha(int idCarro){
//    var listaIDPossiveis = getIdsPossiveis();
//    for(Integer id : listaIDPossiveis)
//      if(id == idCarro)
//        return true;
//    return false;
//  }

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
  
  public ArrayList<Carro> getListaDeCarrosRealClone() throws CloneNotSupportedException{
    var listaCarros = getListaDeCarros();
    ArrayList<Carro> carrosListaClone = new ArrayList(listaCarros.size());
    for(Carro car : listaCarros){
      carrosListaClone.add(car.clone());
    }
    return carrosListaClone;
  }

  public int getQuantCarros() {
    return quantCarros;
  }

  public final void setQuantCarros(int quantCarros) {
    this.quantCarros = quantCarros;
  }
  
  public ArrayList<Carro> getCarrosOrdenadosColocacao() throws CloneNotSupportedException {
    var listaCarrosOrdenada = getListaDeCarrosRealClone();
    Collections.sort(listaCarrosOrdenada);
    return listaCarrosOrdenada;
  }
  
}
