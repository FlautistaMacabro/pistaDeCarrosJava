/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author vinip
 */
public class Volta {
  private final ArrayList<String> listaEventosGerais;
  private final ArrayList<String> listaStatusCarros;
  private final ArrayList<Carro> listaAbastecimentosCarros;
  private final ArrayList<Carro> listaCarrosQuebraram;
  private final ArrayList<Carro> listaCarrosNoPodio;

  public Volta() {
    this.listaEventosGerais = new ArrayList<>();
    this.listaStatusCarros = new ArrayList<>();
    this.listaAbastecimentosCarros = new ArrayList<>();
    this.listaCarrosQuebraram = new ArrayList<>();
    this.listaCarrosNoPodio = new ArrayList<>();
  }

  public ArrayList<Carro> getListaAbastecimentosCarrosClone() throws CloneNotSupportedException {
    var listaCarros = getListaAbastecimentosCarros();
    ArrayList<Carro> carrosListaClone = new ArrayList(listaCarros.size());
    for(Carro car : listaCarros){
      carrosListaClone.add(car.clone());
    }
    return carrosListaClone;
  }
  
  public ArrayList<Carro> getListaCarrosQuebraramClone() throws CloneNotSupportedException {
    var listaCarros = getListaCarrosQuebraram();
    ArrayList<Carro> carrosListaClone = new ArrayList(listaCarros.size());
    for(Carro car : listaCarros){
      carrosListaClone.add(car.clone());
    }
    return carrosListaClone;
  }
  
  public ArrayList<Carro> getListaCarrosNoPodioClone() {
    var listaCarros = getListaCarrosNoPodio();
    if(listaCarros.size() < 3){
      Carro inexistente = new Carro(-1, "Inexistente");
      listaCarros.add(inexistente);
      while(listaCarros.size() < 3)
        listaCarros.add(inexistente);
    }
    return listaCarros;
  }
  
  public ArrayList<Carro> getListaCarrosNoPodioRealClone() throws CloneNotSupportedException {
    var listaCarros = getListaCarrosNoPodioClone();
    ArrayList<Carro> carrosListaClone = new ArrayList(listaCarros.size());
    for(Carro car : listaCarros){
      carrosListaClone.add(car.clone());
    }
    return carrosListaClone;
  }
  
  public void addListaEventosGerais(String evento) {
    getListaEventosGerais().add(evento);
  }
  
  public void addListaStatusCarros(String evento) {
    getListaStatusCarros().add(evento);
  }
  
  public void addListaAbastecimentosCarros(Carro carro) throws CloneNotSupportedException {
    getListaAbastecimentosCarros().add(carro.clone());
  }
  
  public void addListaCarrosQuebraram(Carro carro) throws CloneNotSupportedException {
    getListaCarrosQuebraram().add(carro.clone());
  }
  
  public void addListaCarrosNoPodio(Carro vencedor) throws CloneNotSupportedException {
    getListaCarrosNoPodio().add(vencedor.clone());
  }

  public ArrayList<String> getListaEventosGerais() {
    return listaEventosGerais;
  }
  
  public ArrayList<String> getListaStatusCarros() {
    return listaStatusCarros;
  }

  private ArrayList<Carro> getListaAbastecimentosCarros() {
    return listaAbastecimentosCarros;
  }

  private ArrayList<Carro> getListaCarrosQuebraram() {
    return listaCarrosQuebraram;
  }

  public ArrayList<Carro> getListaCarrosNoPodio() {
    return listaCarrosNoPodio;
  }
  
}
