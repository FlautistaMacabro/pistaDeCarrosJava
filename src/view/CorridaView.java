/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.CorridaController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import persistencia.RegistrosGerais;

/**
 *
 * @author lucas, matheus
 */
public class CorridaView extends JFrame {
    
    private int numberOfCars;
    private int numberOfLaps;


    public CorridaView(int numberOfCars, int numberOfLaps) {
        super("Resultados da Corrida");
        
        this.numberOfCars = numberOfCars;
        this.numberOfLaps = numberOfLaps;
        
        initComponents();
    }

    //--------------- Métodos ---------------// 
    public static void addEvent(String event, JTextArea textArea) {
        textArea.append(event + "\n");
    }
    
    /*public static void fillTextArea(ArrayList<String> data, JTextArea textArea) {
        for (String a : data) {
            textArea.append(a + "\n");
        }
    }*/

    //--------------- Setters ---------------// 
    public void setColocacao(JLabel label, String nome) {
        label.setText(label.getText() + nome);
    }

    //--------------- Getters ---------------// 
    public JTextArea getEventosTextArea() {
        return eventosTextArea;
    }

    public JTextArea getStatusTextArea() {
        return statusTextArea;
    }

    public JLabel getPrimeiroLugar() {
        return primeiroLugar;
    }

    public JLabel getSegundoLugar() {
        return segundoLugar;
    }

    public JLabel getTerceiroLugar() {
        return terceiroLugar;
    }
    
    public ArrayList getPodio(){
        ArrayList podio = new ArrayList();
        podio.add(getPrimeiroLugar());
        podio.add(getSegundoLugar());
        podio.add(getTerceiroLugar());
        return podio; 
    }

    //--------------- Listeners ---------------//
    public void addEventosJTextAreaListener(HierarchyListener h) {
        eventosTextArea.addHierarchyListener(h);
    }
    
    public void addButtonListener(ActionListener a){
        button.addActionListener(a);
    }

    //--------------- Inicialização de Componentes ---------------//
    private void initComponents() {
        //Instanciação de Componentes
        panel = new JPanel();

        container1 = new JPanel();

        container3 = new JPanel();
        container4 = new JPanel();
        eventosLabel = new JLabel("Eventos");
        statusCarrosLabel = new JLabel("Status dos Carros");
        podioLabel = new JLabel("Pódio");
        primeiroLugar = new JLabel("Primeiro Lugar: ");
        segundoLugar = new JLabel("Segundo Lugar: ");
        terceiroLugar = new JLabel("Terceiro Lugar: ");
        eventosTextArea = new JTextArea();
        statusTextArea = new JTextArea();

        eventosTextArea.setEditable(false);
        statusTextArea.setEditable(false);

        scrollEventosTextArea = new JScrollPane(eventosTextArea);
        scrollStatusTextArea = new JScrollPane(statusTextArea);

        //Configurações da Janela/Frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1500, 800);
        setLocationRelativeTo(null);

        //Layout e Panel
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        container1.setLayout(new BoxLayout(container1, BoxLayout.Y_AXIS));
        container4.setLayout(new BoxLayout(container4, BoxLayout.Y_AXIS));
        container3.setLayout(new BoxLayout(container3, BoxLayout.Y_AXIS));

        scrollEventosTextArea.setMaximumSize(new Dimension(500, 500));
        scrollStatusTextArea.setPreferredSize(new Dimension(100, 100));

        //Outros componentes
        eventosLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusCarrosLabel.setFont(new Font("Arial", Font.BOLD, 14));
        podioLabel.setFont(new Font("Arial", Font.BOLD, 14));

        container1.add(Box.createRigidArea(new Dimension(0, 10)));
        container1.add(eventosLabel);

        container1.add(Box.createRigidArea(new Dimension(0, 10)));
        container1.add(scrollEventosTextArea);
        container1.add(Box.createRigidArea(new Dimension(0, 40)));
        button = new JButton("Iniciar Corrida");
        container2 = new CarsPanel(numberOfCars, 50, 100, button);

        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        container1.add(button);
        panel.add(container1);
        container4.add(container2);

        container3.add(podioLabel);
        container3.add(primeiroLugar);
        container3.add(segundoLugar);
        container3.add(terceiroLugar);
        container4.add(container3);
        panel.add(container4);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));

        add(panel);
    }

    //---------------Declaração de Variáveis---------------// 
    private JPanel panel;
    private JPanel container1;
    private CarsPanel container2;
    private JPanel container3;
    private JPanel container4;
    private JLabel eventosLabel;
    private JLabel statusCarrosLabel;
    private JLabel podioLabel;
    private JTextArea eventosTextArea;
    private JScrollPane scrollEventosTextArea;
    private JScrollPane scrollStatusTextArea;
    private JTextArea statusTextArea;
    private JLabel primeiroLugar;
    private JLabel segundoLugar;
    private JLabel terceiroLugar;
    private JButton button;
}
