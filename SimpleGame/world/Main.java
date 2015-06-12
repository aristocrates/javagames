package world;

import javax.swing.*;
import java.awt.image.*;
import java.awt.*;
/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main
{
 static Background bck;
    public static void main(String[] args)
    {
        final JFrame f = new JFrame();
       
        f.setContentPane(new JPanel(){
            Image img;
            {
                img = new BufferedImage(665,500,BufferedImage.TYPE_INT_RGB);
                bck = new StarrySky(665,500,Color.black,Color.white);
            }
            public void paintComponent(Graphics g)
            {
                g.drawImage(bck.getImage(),0,0,getWidth(),getHeight(),null);
            }
        });
        Thread t = new Thread(new Runnable(){
            public void run()
            {
                while(true)
                {
                    try{
                    Thread.sleep(30);}
                    catch(Exception e){}
                    bck.update();
                    f.getContentPane().repaint();
                }
            }
        });
        f.setSize(665,500);
        f.setVisible(true);
        t.start();
    }
}
