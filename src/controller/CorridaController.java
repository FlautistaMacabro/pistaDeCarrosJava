/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import model.Carro;
import persistencia.RegistrosGerais;
import view.CorridaView;

/**
 *
 * @author lucas
 */
public class CorridaController {

    private final CorridaView view;
    private final RegistrosGerais corridaRegistrada;

    public CorridaController(CorridaView view, RegistrosGerais corridaRegistrada) {
        this.view = view;
        this.corridaRegistrada = corridaRegistrada;

        view.addButtonListener(new JButtonHandler());
        view.addEventosJTextAreaListener(new EventosJTextAreaHandler());
        /*view.addStatusCarrosJTextAreaListener(new StatusCarrosJTextAreaHandler());
        view.addPrimeiroLugarJLabelListener(new PodioJLabelHandler(0));
        view.addSegundoLugarJLabelListener(new PodioJLabelHandler(1));
        view.addTerceiroLugarJLabelListener(new PodioJLabelHandler(2));*/
    }
    
    /*public class UpdateGUIRunnable implements Runnable {
        private List<String> strsToSet;
        
        public MyRunnable(List<String> strsToSet) {
            this.strsToSet = strsToSet;
        }
        
        @Override
        public void run() {
            try {
                if(strsToSet.size() > 0) {
                    final String str = strsToSet.get(0);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            txtLeadingText.setText(str);
                        }
                    });

                    Thread.sleep(1000);
                    List<String> newList = new LinkedList<String>(strsToSet);
                    newList.remove(0);
                    new Thread(new MyRunnable(newList)).start();
                }
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
    //new Thread(new MyRunnable(Arrays.asList(one, two, three))).start();

    private class JButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           
            /*System.out.println("Iniciando a corrida...");

            JTextArea textArea = view.getEventosTextArea();

            ArrayList<String> eventos = corridaRegistrada.getCorrida().getRegistrosVoltas().getEventosGeraisTodaCorrida();
            ArrayList<String> comandos = corridaRegistrada.getCorrida().getRegistrosVoltas().getStatusCarrosTodaCorrida();
            
            
            new Thread(new UpdateGUIRunnable(Arrays.asList(one, two, three))).start();*/
        }

    }

    private class EventosJTextAreaHandler implements HierarchyListener {

        @Override
        public void hierarchyChanged(HierarchyEvent e) {
            JComponent component = (JComponent) e.getSource();

            if ((HierarchyEvent.SHOWING_CHANGED & e.getChangeFlags()) != 0
                    && component.isShowing()) {

                CorridaView.addEvent("APERTE O BOTÃO PARA INICIAR A CORRIDA!!!", view.getEventosTextArea());
                //CorridaView.fillTextArea(corridaRegistrada.getCorrida().getRegistrosVoltas().getEventosGeraisTodaCorrida(), view.getEventosTextArea());

            }

        }
    }

    /*private class PodioJLabelHandler implements HierarchyListener {
        
        private final int posicao;
        
        public PodioJLabelHandler(int posicao){
            this.posicao = posicao;
        }

        @Override
        public void hierarchyChanged(HierarchyEvent e){
            JComponent component = (JComponent) e.getSource();

            if ((HierarchyEvent.SHOWING_CHANGED & e.getChangeFlags()) != 0
                    && component.isShowing()) {
                
              
                try {
                    Carro carro = corridaRegistrada.getListaCarrosVencedoresOrdenadosColocacao().get(posicao);
                    
                    JLabel podio = (JLabel)component;
                    podio.setFont(new Font("Arial", Font.PLAIN, 12));
                
                    if(carro.isEmFuncionamento()){
                        view.setColocacao(podio, carro.getNome());
                    }
                    else{
                        view.setColocacao(podio, "Inexistente");
                    }
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(CorridaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
            }

        }
    }*/
 /*private class StatusCarrosJTextAreaHandler implements HierarchyListener {
        
        @Override
        public void hierarchyChanged(HierarchyEvent e){
            JComponent component = (JComponent) e.getSource();

            if ((HierarchyEvent.SHOWING_CHANGED & e.getChangeFlags()) != 0
                    && component.isShowing()) {

                
                try {
                    CorridaView.fillTextArea(corridaRegistrada.getListaStringCarrosOrdenadosColocacao(), view.getStatusTextArea());
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(CorridaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }

        }
    }*/
}
