/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author lucas, matheus
 */
public class CorridaView extends JFrame {

    public CorridaView() {
        super("Corrida");
        initComponents();
    }
    
    
    //--------------- Métodos ---------------// 
    public static void fillTextArea(ArrayList<String> data, JTextArea textArea) {
        for (String a : data) {
            textArea.append(a + "\n");
        }
    }

    //--------------- Setters ---------------// 
    public void setColocacao(JLabel label, String nome){
        label.setText(label.getText() + nome);
    }
    
    
    //--------------- Getters ---------------// 
    public JTextArea getStatusTextArea(){
        return statusTextArea;
    }
    
    public JLabel getPrimeiroLugar(){
        return primeiroLugar;
    }
    
    public JLabel getSegundoLugar(){
        return segundoLugar;
    }
    
    public JLabel getTerceiroLugar(){
        return terceiroLugar;
    }
    
    
    //--------------- Listeners ---------------//
    public void addStatusCarrosJTextAreaListener(HierarchyListener h) {
       statusTextArea.addHierarchyListener(h);
    }
     
    public void addPrimeiroLugarJLabelListener(HierarchyListener h) {
        primeiroLugar.addHierarchyListener(h);
    }
    
    public void addSegundoLugarJLabelListener(HierarchyListener h) {
        segundoLugar.addHierarchyListener(h);
    }
    
    public void addTerceiroLugarJLabelListener(HierarchyListener h) {
        terceiroLugar.addHierarchyListener(h);
    }
    
    
    //--------------- Inicialização de Componentes ---------------//
    private void initComponents() {
        //Instanciação de Componentes
        panel = new JPanel();

        container1 = new JPanel();
        container2 = new JPanel();
        container3 = new JPanel();

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
        setSize(800, 600);
        setLocationRelativeTo(null);

        //Layout e Panel
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        container1.setLayout(new BoxLayout(container1, BoxLayout.Y_AXIS));
        container2.setLayout(new BoxLayout(container2, BoxLayout.Y_AXIS));
        container3.setLayout(new BoxLayout(container3, BoxLayout.Y_AXIS));
        
        scrollEventosTextArea.setPreferredSize(new Dimension(200,100));
        scrollStatusTextArea.setPreferredSize(new Dimension(200,100));
        
        
        ArrayList<String> data = new ArrayList<>(Arrays.asList("London", "Tokyo", "New York", "London", "Tokyo", "New York", "London", "Tokyo", "New York", "London", "Tokyo", "New York", "London", "Tokyo", "New York", "London", "Tokyo", "New York", "London", "Tokyo", "New York", "London", "Tokyo", "New York", "London", "Tokyo", "New York", "London", "Tokyo", "New York", "London", "Tokyo", "New York", "London", "Tokyo", "New York", "London", "Tokyo", "New York", "London", "Tokyo", "New York", "London", "Tokyo", "New York", "London", "Tokyo", "New York", "London", "Tokyo", "New York"));
        fillTextArea(data, eventosTextArea);
        
        //Outros componentes
        
        eventosLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusCarrosLabel.setFont(new Font("Arial", Font.BOLD, 14));
        podioLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        
        container1.add(Box.createRigidArea(new Dimension(0, 10)));
        container1.add(eventosLabel);
        container1.add(Box.createRigidArea(new Dimension(0, 10)));
        container1.add(scrollEventosTextArea);
        container1.add(Box.createRigidArea(new Dimension(0, 40)));
        container2.add(Box.createRigidArea(new Dimension(0, 10)));
        container2.add(statusCarrosLabel);
        container2.add(Box.createRigidArea(new Dimension(0, 10)));
        container2.add(scrollStatusTextArea);
        container2.add(Box.createRigidArea(new Dimension(0, 40)));
        container3.add(podioLabel);
        container3.add(primeiroLugar);
        container3.add(segundoLugar);
        container3.add(terceiroLugar);
        
        
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        panel.add(container1);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        panel.add(container2);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        panel.add(container3);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        
    
        
        add(panel);
    }

    //---------------Declaração de Variáveis---------------// 
    private JPanel panel;
    private JPanel container1;
    private JPanel container2;
    private JPanel container3;
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

}
