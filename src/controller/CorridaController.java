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
    }

    public class UpdateGUIRunnable implements Runnable {

        private ArrayList<String> eventos;
        private ArrayList<String> comandos;
        private JTextArea eventosArea;

        public UpdateGUIRunnable(ArrayList<? extends String> eventos, ArrayList<? extends String> comandos, JTextArea eventosArea) {
            this.eventos = (ArrayList<String>) eventos;
            this.comandos = (ArrayList<String>) comandos;
            this.eventosArea = eventosArea;
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
                            //System.out.println("|"+parts[0]+"|");
                            if ("\nNOVA VOLTA\n".equals(parts[0])) {
                                System.out.println("NOVA VOLTA");
                            } else if ("AVANÇAR".equals(parts[0])) {
                                System.out.println("AVANÇAR - " + parts[1]);
                            } else if ("ABASTECEU".equals(parts[0])) {
                                System.out.println("ABASTECEU -" + parts[1]);
                            } else if ("QUEBROU".equals(parts[0])) {
                                System.out.println("QUEBROU -" + parts[1]);
                            }
                        }
                    });
                }
                if (!eventos.isEmpty() && !comandos.isEmpty()) {
                    Thread.sleep(1000);
                    new Thread(new UpdateGUIRunnable(eventos, comandos, eventosArea)).start();
                } else {
                    //Preenchendo o pódio
                    try {
                        ArrayList<Carro> carros = corridaRegistrada.getListaCarrosVencedoresOrdenadosColocacao();
                        ArrayList vencedores = view.getPodio();

                        for (Carro carro : carros) {
                            JLabel podio = (JLabel) vencedores.remove(0);
                            podio.setFont(new Font("Arial", Font.PLAIN, 12));

                            if (carro.isEmFuncionamento()) {
                                view.setColocacao(podio, carro.getNome());
                            } else {
                                view.setColocacao(podio, "Inexistente");
                            }
                        }

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

            JTextArea eventosArea = view.getEventosTextArea();

            ArrayList<String> eventos = corridaRegistrada.getCorrida().getRegistrosVoltas().getEventosGeraisTodaCorrida();
            ArrayList<String> comandos = corridaRegistrada.getCorrida().getRegistrosVoltas().getStatusCarrosTodaCorrida();

            new Thread(new UpdateGUIRunnable(eventos, comandos, eventosArea)).start();
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
}
