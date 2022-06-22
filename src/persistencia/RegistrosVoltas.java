/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.ArrayList;
import model.Carro;
import model.Volta;

/**
 *
 * @author vinip
 */
public class RegistrosVoltas {
  private int quantVoltas;
  private final ArrayList<Volta> listaVoltas;

  public RegistrosVoltas(int quantVoltas) {
    listaVoltas = new ArrayList<>();
    for (int i = 0; i < quantVoltas; i++)
      listaVoltas.add(new Volta());
    setQuantVoltas(quantVoltas);
  }
  
  public ArrayList<Volta> getListaVoltasClone() {
    ArrayList<Volta> listaClone = new ArrayList<>(getListaVoltas());
    return listaClone;
  }
  
  public int getQuantVoltas() {
    return quantVoltas;
  }
  
  public ArrayList<String> getEventosGeraisTodaCorrida() {
    ArrayList<Volta> listaVoltasAux = getListaVoltasClone();
    ArrayList<String> listaEventosTodaCorrida = new ArrayList<>();
    int quantVoltas = getQuantVoltas();
    for(int i = 0; i < quantVoltas; i++){
      Volta volta = listaVoltasAux.get(i);
      ArrayList<String> listaEventos = volta.getListaEventosGerais();
      listaEventosTodaCorrida.add("\n--------------- Volta "+(i+1)+" ---------------\n");
      for(String evento : listaEventos)
        listaEventosTodaCorrida.add(evento);
    }
    return listaEventosTodaCorrida;
  }
  
  public ArrayList<Carro> getPodioFinalCorrida() throws CloneNotSupportedException {
    ArrayList<Volta> listaVoltasAux = getListaVoltasClone();
    return listaVoltasAux.get(getQuantVoltas()-1).getListaCarrosNoPodioRealClone();
  }
  
  public ArrayList<Carro> getCarrosAbasteceramTodaCorrida() throws CloneNotSupportedException {
    ArrayList<Volta> listaVoltasAux = getListaVoltasClone();
    ArrayList<Carro> listaCarrosAbasteceramTodaCorrida = new ArrayList<>();
    for(Volta volta : listaVoltasAux){
      ArrayList<Carro> listaCarrosAbasteceramVolta = volta.getListaAbastecimentosCarrosClone();
      for(Carro car : listaCarrosAbasteceramVolta)
        listaCarrosAbasteceramTodaCorrida.add(car.clone());
    }
    return listaCarrosAbasteceramTodaCorrida;
  }
  
  public ArrayList<Carro> getCarrosQuebraramTodaCorrida() throws CloneNotSupportedException {
    ArrayList<Volta> listaVoltasAux = getListaVoltasClone();
    ArrayList<Carro> listaCarrosQuebraramTodaCorrida = new ArrayList<>();
    for(Volta volta : listaVoltasAux){
      ArrayList<Carro> listaCarrosQuebraramVolta = volta.getListaCarrosQuebraramClone();
      for(Carro car : listaCarrosQuebraramVolta)
        listaCarrosQuebraramTodaCorrida.add(car.clone());
    }
    return listaCarrosQuebraramTodaCorrida;
  }

  private ArrayList<Volta> getListaVoltas() {
    return listaVoltas;
  }

  private void setQuantVoltas(int quantVoltas) {
    this.quantVoltas = quantVoltas;
  }

}
