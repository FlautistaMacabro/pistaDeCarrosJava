/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import controller.Controller;
import view.ConfiguracoesCorridaView;

/**
 *
 * @author vinip
 */
public class Main {

  /**
   * @param args the command line arguments
   * @throws java.lang.CloneNotSupportedException
   */
  public static void main(String[] args) throws CloneNotSupportedException {
//    RegistrosGerais corridaRegistrada = new RegistrosGerais(8, 11, 5, 20);
//    corridaRegistrada.imprimirPosicoes();

      ConfiguracoesCorridaView view = new ConfiguracoesCorridaView();
      Controller controller = new Controller(view);
                
      view.setVisible(true);
  }
  
}
