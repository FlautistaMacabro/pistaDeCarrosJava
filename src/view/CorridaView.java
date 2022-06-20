/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

    //--------------- Getters ---------------// 
    

    //--------------- Listeners ---------------//
    

    //--------------- Inicialização de Componentes ---------------//
    private void initComponents() {
        //Instanciação de Componentes
        panel = new JPanel();
        
        container1 = new JPanel();
        container2 = new JPanel();
        container3 = new JPanel();
        
        label1 = new JLabel("Eventos");
        label2 = new JLabel("Status dos Carros");
        label3 = new JLabel("Pódio");
        label4 = new JLabel("Primeiro Lugar: ");
        label5 = new JLabel("Segundo Lugar: ");
        label6 = new JLabel("Terceiro Lugar: ");
        
        textArea1 = new JTextArea();
        textArea2 = new JTextArea();
        //Configurações da Janela/Frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        //Layout e Panel
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        container1.setLayout(new BoxLayout(container1, BoxLayout.Y_AXIS));
        container2.setLayout(new BoxLayout(container2, BoxLayout.Y_AXIS));
        container3.setLayout(new BoxLayout(container3, BoxLayout.Y_AXIS));
        //Outros componentes
        container1.add(Box.createRigidArea(new Dimension(0, 20))); 
        container1.add(label1);
        container1.add(Box.createRigidArea(new Dimension(0, 10))); 
        container1.add(textArea1);
        container1.add(Box.createRigidArea(new Dimension(0, 50))); 
        container2.add(Box.createRigidArea(new Dimension(0, 20))); 
        container2.add(label2);
        container2.add(Box.createRigidArea(new Dimension(0, 10))); 
        container2.add(textArea2);
        container2.add(Box.createRigidArea(new Dimension(0, 50))); 
        container3.add(label3);
        container3.add(label4);
        container3.add(label5);
        container3.add(label6);
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
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    
}
