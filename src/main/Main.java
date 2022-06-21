/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import controller.ConfiguracoesCorridaController;
import view.ConfiguracoesCorridaView;

/**
 *
 * @author vinip
 */
public class Main {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args){
      ConfiguracoesCorridaView view = new ConfiguracoesCorridaView();
      ConfiguracoesCorridaController controller = new ConfiguracoesCorridaController(view);
                
      view.setVisible(true);
  }
  
}
