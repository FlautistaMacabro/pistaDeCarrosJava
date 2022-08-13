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
import javax.swing.JOptionPane;
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
    }

    public class UpdateGUIRunnable implements Runnable {

        private ArrayList<String> eventos;
        private ArrayList<String> comandos;
        private JTextArea eventosArea;
        private int voltaAtual;

        public UpdateGUIRunnable(ArrayList<? extends String> eventos, ArrayList<? extends String> comandos, JTextArea eventosArea, int voltaAtual) {
            this.eventos = (ArrayList<String>) eventos;
            this.comandos = (ArrayList<String>) comandos;
            this.eventosArea = eventosArea;
            this.voltaAtual = voltaAtual;
        }

        @Override
        public void run() {
            try {
                if (!eventos.isEmpty()) {
                    // Preenchendo o TextField de Eventos
                    final String str = eventos.remove(0);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            eventosArea.append(str + "\n");
                        }
                    });
                }
                
                
                if (!comandos.isEmpty()) {
                    // Movendo os carros na interface
                    final String[] parts = comandos.remove(0).split(",");
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            if("\nNOVA VOLTA\n".equals(parts[0])) {
                                //System.out.println("NOVA VOLTA");
                                view.setVoltaAtual(String.valueOf(voltaAtual));
                                voltaAtual++;
                            } else if ("AVANÇAR".equals(parts[0])) {
                                //System.out.println("AVANÇAR - " + parts[1]);
                                view.getCarsPanel().moverCarros(Integer.valueOf(parts[1]));
                            } else if ("ABASTECEU".equals(parts[0])) {
                                //System.out.println("ABASTECEU -" + parts[1]);
                            } else if ("QUEBROU".equals(parts[0])) {
                                //System.out.println("QUEBROU -" + parts[1]);
                                view.getCarsPanel().mudarStatusCarros(Integer.valueOf(parts[1]), "Quebrou");
                            }
                        }
                    });
                }
                
                
                if (!eventos.isEmpty() && !comandos.isEmpty()) {
                    Thread.sleep(1000);
                    
                    new Thread(new UpdateGUIRunnable(eventos, comandos, eventosArea, voltaAtual)).start();
                } else {
                    //Preenchendo o pódio
                    try {
                        ArrayList<Carro> carros = corridaRegistrada.getListaCarrosVencedoresOrdenadosColocacao();
                        ArrayList vencedores = view.getPodio();

                        int pos = 0;
                        for (Carro carro : carros) {
                            if(pos == 3) break;
                                
                            JLabel podio = (JLabel) vencedores.remove(0);
                            podio.setFont(new Font("Arial", Font.PLAIN, 12));

                            if (carro.isEmFuncionamento()) {
                                view.setColocacao(podio, carro.getNome());
                            } else {
                                view.setColocacao(podio, "Inexistente");
                            }
                            pos++;
                        }
                        
                        JOptionPane.showMessageDialog(null, "FIM DA CORRIDA!!!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("A corrida terminou...");

                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(CorridaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class JButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("Iniciando a corrida...");
            
            view.getButton().setEnabled(false);

            JTextArea eventosArea = view.getEventosTextArea();

            ArrayList<String> eventos = corridaRegistrada.getCorrida().getRegistrosVoltas().getEventosGeraisTodaCorrida();
            ArrayList<String> comandos = corridaRegistrada.getCorrida().getRegistrosVoltas().getStatusCarrosTodaCorrida();

            int voltaAtual = 1;
            
            new Thread(new UpdateGUIRunnable(eventos, comandos, eventosArea, voltaAtual)).start();
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
                
                // Inicializa o Status dos carros com "Normal"
                int qtdCarro = corridaRegistrada.getCorrida().getRegistrosCarros().getQuantCarros();
                for(int i = 0 ; i < qtdCarro; i++){
                    view.getCarsPanel().mudarStatusCarros(Integer.valueOf(i+1), " Normal");
                }
            }

        }
    }
}
