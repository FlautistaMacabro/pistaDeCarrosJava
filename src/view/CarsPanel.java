/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author mathe
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class CarsPanel extends JPanel {

    private ArrayList<CarsDisplay> listaDeCarros;
    private int locationX;
    private int locationY;
    private int numberOfCars;
    private int numberOfLaps;
    private final int distanciaEntrePista = 15;
    private final int alturaPista = 30;
    private JButton button;
    private ArrayList<CarsDisplay> vencedores;

    CarsPanel(int numberOfCars, int numberOfLaps, int locationX, int locationY, JButton button) {
        this.listaDeCarros = new ArrayList<>();
        this.locationX = locationX;
        this.locationY = locationY;
        this.button = button;
        this.numberOfCars = numberOfCars;
        this.numberOfLaps = numberOfLaps;
        for (int i = 0; i < numberOfCars; i++) {
            this.listaDeCarros.add(new CarsDisplay(this.locationX,
                    this.locationY + (i + 1) * (alturaPista + distanciaEntrePista), i + 1, numberOfLaps));
        }

        this.vencedores = new ArrayList<>();
        setMaximumSize(new Dimension(3000,600));
    }

    //--------------- Métodos ---------------// 
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (CarsDisplay display : this.listaDeCarros) {
            display.paintDisplay(g);
        }
    }

    public void moverCarros(int carIndex) {
        listaDeCarros.get(carIndex - 1).moverCarro();
        this.revalidate();
        this.repaint();
    }

    public void mudarStatusCarros(int carIndex, String statusMessage) {
        listaDeCarros.get(carIndex - 1).setStatusCarro(statusMessage);
    }

    //--------------- Getters ---------------// 
    public ArrayList<CarsDisplay> getListaDeCarros() {
        return listaDeCarros;
    }

}
