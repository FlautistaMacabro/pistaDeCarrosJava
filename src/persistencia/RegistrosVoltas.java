/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.ArrayList;
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
  
  public ArrayList<String> getPodioFinalCorrida() {
    ArrayList<Volta> listaVoltasAux = getListaVoltasClone();
    return listaVoltasAux.get(getQuantVoltas()-1).getListaNomesCarrosNoPodioClone();
  }
  
  public ArrayList<Integer> getIDsCarrosAbasteceramTodaCorrida() {
    ArrayList<Volta> listaVoltasAux = getListaVoltasClone();
    ArrayList<Integer> listaIDsCarrosAbasteceramTodaCorrida = new ArrayList<>();
    for(Volta volta : listaVoltasAux){
      ArrayList<Integer> listaIDsCarrosAbasteceramVolta = volta.getListaIDsCarrosAbasteceramClone();
      for(Integer id : listaIDsCarrosAbasteceramVolta)
        listaIDsCarrosAbasteceramTodaCorrida.add(id);
    }
    return listaIDsCarrosAbasteceramTodaCorrida;
  }
  
  public ArrayList<Integer> getIDsCarrosQuebraramTodaCorrida() {
    ArrayList<Volta> listaVoltasAux = getListaVoltasClone();
    ArrayList<Integer> listaIDsCarrosQuebraramTodaCorrida = new ArrayList<>();
    for(Volta volta : listaVoltasAux){
      ArrayList<Integer> listaIDsCarrosQuebraramVolta = volta.getListaIDsCarrosQuebraramClone();
      for(Integer id : listaIDsCarrosQuebraramVolta)
        listaIDsCarrosQuebraramTodaCorrida.add(id);
    }
    return listaIDsCarrosQuebraramTodaCorrida;
  }

  private ArrayList<Volta> getListaVoltas() {
    return listaVoltas;
  }

  private void setQuantVoltas(int quantVoltas) {
    this.quantVoltas = quantVoltas;
  }

}
