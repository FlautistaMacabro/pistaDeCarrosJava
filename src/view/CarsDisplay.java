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
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class CarsDisplay{

    private int locationX;
    private int locationY;
    private int percursoCarro;
    private int numeroDoCarro;
    private int voltasDoCarro;
    private JLabel labelVolta;

    CarsDisplay(int locationX, int locationY, int carNumber) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.numeroDoCarro = carNumber;
        this.percursoCarro = 0;
        this.voltasDoCarro = 0;

        this.labelVolta = new JLabel("Volta: ");

        this.labelVolta.setVisible(true);

        this.labelVolta.setLocation(this.locationX, this.locationY + 10);

    }

    public int getRandom(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public void drawCar(Graphics g, int positionX, int positionY) {
        BufferedImage image;
        try {
            image = ImageIO.read(CarsDisplay.class.getResource("/resources/car.png"));
        } catch (IOException e) {
            image = null;
        }
        g.drawImage(image, (locationX + this.percursoCarro), locationY, null);
    }
    public void paintDisplay(Graphics g) {
        g.drawString("Volta: " + this.getVoltasDoCarro(), this.locationX, this.locationY);
        this.drawCar(g, locationX, locationY);
        g.fillRect(this.getLocationX(), this.getLocationY() + 5, 1000, 2);
        g.fillRect(this.getLocationX(), this.getLocationY(), 1, 10);
        g.fillRect(this.getLocationX() + 100, this.getLocationY(), 1, 5);
        g.fillRect(this.getLocationX() + 200, this.getLocationY(), 1, 5);
        g.fillRect(this.getLocationX() + 300, this.getLocationY(), 1, 5);
        g.fillRect(this.getLocationX() + 400, this.getLocationY(), 1, 5);
        g.fillRect(this.getLocationX() + 500, this.getLocationY(), 1, 5);
        g.fillRect(this.getLocationX() + 600, this.getLocationY(), 1, 5);
        g.fillRect(this.getLocationX() + 700, this.getLocationY(), 1, 5);
        g.fillRect(this.getLocationX() + 800, this.getLocationY(), 1, 5);
        g.fillRect(this.getLocationX() + 900, this.getLocationY(), 1, 5);
        g.fillRect(this.getLocationX() + 1000, this.getLocationY(), 1, 10);

    }
    
     public void carMoves()
    { 
        if(this.percursoCarro <= 1000)
        {
        	int moveu = getRandom(0, 3) * 100;
            this.percursoCarro = this.percursoCarro + moveu;
        }
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public int getNumeroDoCarro() {
        return numeroDoCarro;
    }

    public void setNumeroDoCarro(int numeroDoCarro) {
        this.numeroDoCarro = numeroDoCarro;
    }

    public int getPercursoCarro() {
        return percursoCarro;
    }

    public void setPercursoCarro(int percursoCarro) {
        this.percursoCarro =percursoCarro;
    }

    public int getVoltasDoCarro() {
        return voltasDoCarro;
    }

    public void setVoltasDoCarro(int voltasDoCarro) {
        this.voltasDoCarro = voltasDoCarro;
    }

    public void adicionarVolta() {
        this.voltasDoCarro = voltasDoCarro + 1;
        this.percursoCarro = percursoCarro - 900;
    }

}
