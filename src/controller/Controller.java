/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import persistencia.RegistrosGerais;
import view.ConfiguracoesCorridaView;

/**
 *
 * @author lucas
 */
public class Controller {
    private ConfiguracoesCorridaView view;
    private RegistrosGerais corridaRegistrada;
    
    public Controller(ConfiguracoesCorridaView view){
        this.view = view;
        //this.corridaRegistrada = new RegistrosGerais();
        
    }
    
}
