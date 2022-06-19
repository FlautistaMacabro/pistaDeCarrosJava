/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author lucas, matheus
 */
public class ConfiguracoesCorridaView extends JFrame{
    
    public ConfiguracoesCorridaView(){
        super("Configurações da Corrida");
        initComponents();      
    }
    
    
    //--------------- Getters ---------------// 
    
    public JTextField getTextField1(){
        return text1;
    }
    
    public JTextField getTextField2(){
        return text2;
    }
    
    public JTextField getTextField3(){
        return text3;
    }
    
    public JTextField getTextField4(){
        return text4;
    }
    
    
    //--------------- Listeners ---------------//
    
    public void addButtonListener(ActionListener a){
         button.addActionListener(a);
    }
    
    
    //--------------- Inicialização de Componentes ---------------//
    private void initComponents() {
        //Instanciação de Componentes
        JPanel panel = new JPanel();
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JTextField text1 = new JTextField();
        JLabel label3 = new JLabel();
        JTextField text2 = new JTextField();
        JLabel label4  = new JLabel();
        JTextField text3 = new JTextField();
        JLabel label5  = new JLabel();
        JTextField text4 = new JTextField();
        JButton button = new JButton();
       
        //Configurações da Janela/Frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,450);
        setLocationRelativeTo(null);
        
        //Layout e Panel
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        //Outros componentes 
        
       
        add(panel);
    }
    
    
    //---------------Declaração de Variáveis---------------// 
    private JPanel panel;
    private JLabel label1;
    private JLabel label2;
    private JTextField text1;
    private JLabel label3;
    private JTextField text2;
    private JLabel label4;
    private JTextField text3;
    private JLabel label5;
    private JTextField text4;
    private JButton button;
}
