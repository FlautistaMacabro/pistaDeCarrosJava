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
    private int numberOfLaps;
    private int avancoPorVolta;
    private String statusCarro;
    private JLabel labelStatus;
    private JLabel labelVolta;
    private static int idCarro = 0;

    CarsDisplay(int locationX, int locationY, int carNumber, int numberOfLaps) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.numeroDoCarro = carNumber;
        this.percursoCarro = 0;
        this.voltasDoCarro = 0;
        this.numberOfLaps = numberOfLaps;
        this.avancoPorVolta = 1000/this.numberOfLaps;

        this.labelVolta = new JLabel("Volta: ");

        this.labelVolta.setVisible(true);

        this.labelVolta.setLocation(this.locationX, this.locationY + 10);
        
        
        
        this.labelStatus = new JLabel("Status: ");

        this.labelStatus.setVisible(true);

        this.labelStatus.setLocation(this.locationX+100, this.locationY + 10);
        
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
        g.drawString("Status: " + this.getStatusCarro(), this.locationX + 100, this.locationY);
        g.drawString("Carro: " + this.numeroDoCarro, this.locationX + 200, this.locationY);
        this.drawCar(g, locationX, locationY);
        g.fillRect(this.getLocationX(), this.getLocationY() + 5, 1000, 2);
         g.fillRect(this.getLocationX(), this.getLocationY(), 1, 5);
        int size = avancoPorVolta;
        for(int i = 0; i < this.numberOfLaps; i++){
            g.fillRect(this.getLocationX() + size, this.getLocationY(), 1, 5);
            size += avancoPorVolta;
        }
    }
    
      public void moverCarro()
    { 
        if(this.percursoCarro <= 1000)
        {
            this.voltasDoCarro +=1;
            this.percursoCarro  +=  avancoPorVolta;
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

    public String getStatusCarro() {
        return statusCarro;
    }

    public void setStatusCarro(String statusCarro) {
        this.statusCarro = statusCarro;
    }
    
    
    

}
