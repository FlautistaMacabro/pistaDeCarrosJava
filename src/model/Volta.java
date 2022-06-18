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
  private final ArrayList<Integer> listaIDsCarrosAbasteceram;
  private final ArrayList<Integer> listaIDsCarrosQuebraram;
  private final ArrayList<String> listaNomesCarrosNoPodio;

  public Volta() {
    this.listaIDsCarrosAbasteceram = new ArrayList<>();
    this.listaIDsCarrosQuebraram = new ArrayList<>();
    this.listaNomesCarrosNoPodio = new ArrayList<>();
  }

  public ArrayList<Integer> getListaIDsCarrosAbasteceramClone() {
    ArrayList<Integer> listaClone = new ArrayList<>(getListaIDsCarrosAbasteceram());
    return listaClone;
  }
  
  public ArrayList<Integer> getListaIDsCarrosQuebraramClone() {
    ArrayList<Integer> listaClone = new ArrayList<>(getListaIDsCarrosQuebraram());
    return listaClone;
  }
  
  public ArrayList<String> getListaNomesCarrosNoPodioClone() {
    ArrayList<String> listaClone = new ArrayList<>(getListaNomesCarrosNoPodio());
    return listaClone;
  }
  
  public void addListaIDsCarrosAbasteceram(int idCarro) {
    getListaIDsCarrosAbasteceram().add(idCarro);
  }
  
  public void addListaIDsCarrosQuebraram(int idCarro) {
    getListaIDsCarrosQuebraram().add(idCarro);
  }
  
  public void addListaNomesCarrosNoPodio(String vencedor) {
    getListaNomesCarrosNoPodio().add(vencedor);
  }

  private ArrayList<Integer> getListaIDsCarrosAbasteceram() {
    return listaIDsCarrosAbasteceram;
  }

  private ArrayList<Integer> getListaIDsCarrosQuebraram() {
    return listaIDsCarrosQuebraram;
  }

  private ArrayList<String> getListaNomesCarrosNoPodio() {
    return listaNomesCarrosNoPodio;
  }
  
}
