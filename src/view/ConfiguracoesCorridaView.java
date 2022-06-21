/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
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
public class ConfiguracoesCorridaView extends JFrame {

    public ConfiguracoesCorridaView() {
        super("Corrida");
        initComponents();
    }

    //--------------- Getters ---------------// 
    public JTextField getNumeroCarros() {
        return numeroCarros;
    }

    public JTextField getNumeroVoltas() {
        return numeroVoltas;
    }

    public JTextField getProbabilidadeQuebra() {
        return probabilidadeQuebra;
    }

    public JTextField getProbabilidadeAbastecimento() {
        return probabilidadeAbastecimento;
    }

    //--------------- Listeners ---------------//
    public void addButtonListener(ActionListener a) {
        botao.addActionListener(a);
    }
    
    public void addNumeroCarrosListener(KeyListener k) {
        numeroCarros.addKeyListener(k);
    }
    
    public void addNumeroVoltasListener(KeyListener k) {
        numeroVoltas.addKeyListener(k);
    }
    
    public void addProbabilidadeQuebraListener(KeyListener k) {
        probabilidadeQuebra.addKeyListener(k);
    }
    
    public void addProbabilidadeAbastecimentoListener(KeyListener k) {
        probabilidadeAbastecimento.addKeyListener(k);
    }

    //--------------- Inicialização de Componentes ---------------//
    private void initComponents() {
        //Instanciação de Componentes
        panel = new JPanel();
        container1 = new JPanel();
        container2 = new JPanel();
        container3 = new JPanel();
        container4 = new JPanel();
        container5 = new JPanel();
        
        titulo = new JLabel("Configurações da Corrida");
        
        numeroCarrosLabel = new JLabel("Inserir número de carros na corrida (maior que 1 e no máximo 20):");
        numeroCarros = new JTextField();
        numeroCarros.setToolTipText("2 <= x <= 20");
        
        numeroVoltasLabel = new JLabel("Inserir a quantidade de voltas (maior que 10 e no máximo 30):");
        numeroVoltas = new JTextField();
        numeroVoltas.setToolTipText("10 < x <= 30");
        
        probabilidadeQuebraLabel = new JLabel("Inserir probabilidade de quebra de carros:");
        probabilidadeQuebra = new JTextField();
        probabilidadeQuebra.setToolTipText("0 <= x <= 100");
        
        probabilidadeAbastecimentoLabel = new JLabel("Inserir probabilidade para abastecimento:");
        probabilidadeAbastecimento = new JTextField();
        probabilidadeAbastecimento.setToolTipText("0 <= x <= 100");
        
        botao = new JButton("Confirmar");

        //Configurações da Janela/Frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);

        //Layout e Panel
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //Outros componentes 
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        numeroCarrosLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        numeroVoltasLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        probabilidadeQuebraLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        probabilidadeAbastecimentoLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        
        numeroCarros.setPreferredSize(new Dimension(100, 20));
        container1.setLayout(new FlowLayout());
        container1.add(numeroCarrosLabel);
        container1.add(numeroCarros);

        numeroVoltas.setPreferredSize(new Dimension(100, 20));
        container2.setLayout(new FlowLayout());
        container2.add(numeroVoltasLabel);
        container2.add(numeroVoltas);

        probabilidadeQuebra.setPreferredSize(new Dimension(100, 20));
        container3.setLayout(new FlowLayout());
        container3.add(probabilidadeQuebraLabel);
        container3.add(probabilidadeQuebra);

        probabilidadeAbastecimento.setPreferredSize(new Dimension(100, 20));
        container4.setLayout(new FlowLayout());
        container4.add(probabilidadeAbastecimentoLabel);
        container4.add(probabilidadeAbastecimento);

        titulo.setAlignmentX(panel.CENTER_ALIGNMENT);
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        panel.add(titulo);

        panel.add(container1);

        panel.add(container2);

        panel.add(container3);

        panel.add(container4);

        botao.setAlignmentX(panel.CENTER_ALIGNMENT);

        container5.add(botao);
        container5.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(container5);
        add(panel);
    }
    
    //---------------Declaração de Variáveis---------------// 
    private JPanel panel;
    private JPanel container1;
    private JPanel container2;
    private JPanel container3;
    private JPanel container4;
    private JPanel container5;
    private JLabel titulo;
    private JLabel numeroCarrosLabel;
    private JLabel numeroVoltasLabel;
    private JLabel probabilidadeQuebraLabel;
    private JLabel probabilidadeAbastecimentoLabel;
    private JTextField numeroCarros;
    private JTextField numeroVoltas;
    private JTextField probabilidadeQuebra;
    private JTextField probabilidadeAbastecimento;
    private JButton botao;
}
