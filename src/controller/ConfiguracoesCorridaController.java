/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import persistencia.RegistrosGerais;
import view.ConfiguracoesCorridaView;
import view.CorridaView;

/**
 *
 * @author lucas
 */
public class ConfiguracoesCorridaController {
    private ConfiguracoesCorridaView view;
    
    public ConfiguracoesCorridaController(ConfiguracoesCorridaView view){
        this.view = view;
        
        view.addNumeroCarrosListener(new JTextFieldInputHandler(1));
        view.addNumeroVoltasListener(new JTextFieldInputHandler(1));
        view.addProbabilidadeQuebraListener(new JTextFieldInputHandler(2));
        view.addProbabilidadeAbastecimentoListener(new JTextFieldInputHandler(2));
        view.addButtonListener(new IniciarCorridaButtonHandler());
    }
    
    //------------------------------- Métodos -------------------------------//
    
    private void iniciarCorrida(int numCarros, int numVoltas, int probaQuebrar, int probaAbastecer) throws CloneNotSupportedException {
        RegistrosGerais corridaRegistrada = new RegistrosGerais(numCarros, numVoltas, probaQuebrar, probaAbastecer);
        CorridaView view = new CorridaView();
        CorridaController controller = new CorridaController(view,corridaRegistrada);
                
        view.setVisible(true);
    }
            
            
    //------------------------------- Listeners -------------------------------//
            
    private class JTextFieldInputHandler implements KeyListener{
        
        private int tam;
        
        public JTextFieldInputHandler(int tam){
            this.tam = tam;
        }
        
        public static String maxLength(String entrada, int tamanho) {
            StringBuffer saida = new StringBuffer();
            char[] caracteres = removeCaracters(entrada).toCharArray();
            for (int i = 0; i < caracteres.length && i <= tamanho; i++) {
                saida.append(caracteres[i]);
            }
            return saida.toString();
        }

        public static String removeCaracters(String entrada) {
            Pattern numericos = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
            Matcher encaixe = numericos.matcher(entrada);
            StringBuffer saida = new StringBuffer();
            while (encaixe.find()) {
                saida.append(encaixe.group());
            }
            return saida.toString();
        }
        
        @Override
        public void keyTyped(KeyEvent e) {
            JTextField field = (JTextField)e.getComponent();
            field.setText(maxLength(field.getText(), tam));
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
            JTextField field = (JTextField)e.getComponent();
            field.setText(maxLength(field.getText(), tam));
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            JTextField field = (JTextField)e.getComponent();
            field.setText(maxLength(field.getText(), tam));
        }
    }
    
    
    
    private class IniciarCorridaButtonHandler implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String numeroCarros = (view.getNumeroCarros()).getText();
            String numeroVoltas = (view.getNumeroVoltas()).getText();
            String probabilidadeQuebra = (view.getProbabilidadeQuebra()).getText();
            String probabilidadeAbastecimento = (view.getProbabilidadeAbastecimento()).getText();
            
            int numCarros = 0;
            int numVoltas = 0;
            int probaQuebrar = 0;
            int probaAbastecimento = 0;

            if (numeroCarros.isBlank() || numeroVoltas.isBlank() || probabilidadeQuebra.isBlank() || probabilidadeAbastecimento.isBlank()) {
                JOptionPane.showMessageDialog(null, "Erro! Alguns dos campos estão vazios", "Error", 0);
                return;
            }
            
            try {
                numCarros = Integer.parseInt(numeroCarros);
                numVoltas = Integer.parseInt(numeroVoltas);
                probaQuebrar = Integer.parseInt(probabilidadeQuebra);
                probaAbastecimento = Integer.parseInt(probabilidadeAbastecimento);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Erro! Os valores inseridos nos campos estão incorretos.", "Error", 0);
                return;
            }
            
            
            if ((numCarros < 2 || numCarros > 20) || (numVoltas < 11 || numVoltas > 30) || (probaQuebrar < 0 || probaQuebrar > 100) || (probaAbastecimento < 0 || probaAbastecimento > 100)) {
                JOptionPane.showMessageDialog(null, "Erro! Os valores inseridos nos campos estão incorretos.", "Error", 0);
            } 
            else {
                JOptionPane.showMessageDialog(null, "Sucesso! A corrida foi configurada corretamente.", "Sucesso", 1);
                view.setVisible(false);
                view.dispose();
                
                try {
                    iniciarCorrida(numCarros,numVoltas,probaQuebrar,probaAbastecimento);
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(ConfiguracoesCorridaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
