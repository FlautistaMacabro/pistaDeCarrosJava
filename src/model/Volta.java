/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author vinip
 */ // colo kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk
public class Volta {
  private final ArrayList<Carro> listaIDsCarrosAbasteceram;
  private final ArrayList<Carro> listaIDsCarrosQuebraram;
  private final ArrayList<Carro> listaNomesCarrosNoPodio;

  public Volta() {
    this.listaIDsCarrosAbasteceram = new ArrayList<>();
    this.listaIDsCarrosQuebraram = new ArrayList<>();
    this.listaNomesCarrosNoPodio = new ArrayList<>();
  }

  public ArrayList<Carro> getListaIDsCarrosAbasteceramClone() throws CloneNotSupportedException {
    var listaCarros = getListaCarrosAbasteceram();
    ArrayList<Carro> carrosListaClone = new ArrayList(listaCarros.size());
    for(Carro car : listaCarros){
      carrosListaClone.add(car.clone());
    }
    return carrosListaClone;
  }
  
  public ArrayList<Carro> getListaIDsCarrosQuebraramClone() throws CloneNotSupportedException {
    var listaCarros = getListaCarrosQuebraram();
    ArrayList<Carro> carrosListaClone = new ArrayList(listaCarros.size());
    for(Carro car : listaCarros){
      carrosListaClone.add(car.clone());
    }
    return carrosListaClone;
  }
  
  public ArrayList<Carro> getListaNomesCarrosNoPodioClone() throws CloneNotSupportedException {
    var listaCarros = getListaCarrosNoPodio();
    ArrayList<Carro> carrosListaClone = new ArrayList(listaCarros.size());
    for(Carro car : listaCarros){
      carrosListaClone.add(car.clone());
    }
    return carrosListaClone;
  }
  
  public void addListaCarrosAbasteceram(Carro carro) throws CloneNotSupportedException {
    getListaCarrosAbasteceram().add(carro.clone());
  }
  
  public void addListaCarrosQuebraram(Carro carro) throws CloneNotSupportedException {
    getListaCarrosQuebraram().add(carro.clone());
  }
  
  public void addListaCarrosNoPodio(Carro vencedor) throws CloneNotSupportedException {
    getListaCarrosNoPodio().add(vencedor.clone());
  }

  private ArrayList<Carro> getListaCarrosAbasteceram() {
    return listaIDsCarrosAbasteceram;
  }

  private ArrayList<Carro> getListaCarrosQuebraram() {
    return listaIDsCarrosQuebraram;
  }

  private ArrayList<Carro> getListaCarrosNoPodio() {
    return listaNomesCarrosNoPodio;
  }
  
}
