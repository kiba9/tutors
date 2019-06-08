/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tutors;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author cena M
 */
public class Panneau1 extends JPanel{
    private int posX = 10;
    private int posY = 10;
    
    @Override
    public void paintComponent(Graphics g){
        
           //desiner les figures
        
        /*int y = this.getHeight()/4;
        int x = this.getWidth()/4;
        int x2[] = {50, 100, 200, 250, 250, 200, 100, 50};
        int y2[] = {100, 70, 70, 100, 150, 170, 170, 150};
        g.drawPolygon(x2, y2, 8);
        //Font font = new
        g.setFont(new Font("Times New Roman", Font.BOLD, 50));
        g.setColor(Color.blue);
        g.drawString("MM", 50, 45);
        g.drawLine(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.gray);
        g.fillOval(x, y, this.getWidth()/2, this.getHeight()*2/3);*/
        
        
        //Ajout d'une image
        
        try{
        Image img = ImageIO.read(new File("IMG_5552.jpg"));
        g.drawImage(img, 400, 100, 150, 150, this);
        // pour une image de fond
        //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        
        } catch (IOException e){
        e.printStackTrace();
        }
        
        
        //remplissage de degradé
        
        /*Graphics2D g2d =(Graphics2D) g;
        GradientPaint gp, gp2, gp3, gp4, gp5, gp6;
        g2d.setPaint(new GradientPaint(0, 0, Color.red, 20, 0, Color.white, true));
        gp2 = new GradientPaint(20, 0, Color.magenta, 40, 0, Color.blue, true);
        gp3 = new GradientPaint(40, 0, Color.blue, 60, 0, Color.green, true);
        gp4 = new GradientPaint(60, 0, Color.green, 80, 0, Color.yellow, true);
        gp5 = new GradientPaint(80, 0, Color.yellow, 100, 0, Color.orange, true);
        gp6 = new GradientPaint(100, 0, Color.orange, 120, 0, Color.red, true);
        g2d.fillRect(0, 0, 20,this.getHeight());
        g2d.setPaint(gp2);
        g2d.fillRect(20, 0, 20,this.getHeight());
        g2d.setPaint(gp3);
        g2d.fillRect(40, 0, 20,this.getHeight());
        g2d.setPaint(gp4);
        g2d.fillRect(60, 0, 20,this.getHeight());
        g2d.setPaint(gp5);
        g2d.fillRect(80, 0, 20,this.getHeight());
        g2d.setPaint(gp6);
        g2d.fillRect(100, 0, 40,this.getHeight());*/
        
        /* g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.red);
        g.fillOval(posX, posY, 50, 50);  */
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    public int getPosX(){
      return posX;  
    }
    
    public void setPosX(int posX){
        this.posX = posX;
    }
}
