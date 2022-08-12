/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author mathe
 */
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
    private final int distanciaEntrePista = 15;
    private final int alturaPista = 30;
    private JButton button;
    private ArrayList<CarsDisplay> vencedores;

    CarsPanel(int numberOfCars, int locationX, int locationY, JButton button) {
        this.listaDeCarros = new ArrayList<>();
        this.locationX = locationX;
        this.locationY = locationY;
        this.button = button;

        for (int i = 0; i < numberOfCars; i++) {
            this.listaDeCarros.add(new CarsDisplay(this.locationX,
                    this.locationY + (i + 1) * (alturaPista + distanciaEntrePista), i + 1));
        }

        this.vencedores = new ArrayList<>();
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("clicou");
            }
        });

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (CarsDisplay display : this.listaDeCarros) {
            display.paintDisplay(g);
        }
    }
}
