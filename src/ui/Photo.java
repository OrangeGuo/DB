package ui;

import javax.swing.*;
import java.awt.*;


public class Photo extends JPanel {
    private Image image;
    private int width=99;
    private int height=99;
    public Photo(int width,int height){
        this.width=width;
        this.height=height;
        image=Toolkit.getDefaultToolkit().getImage("D:\\java图标\\unamed.jpg") ;
    }
    public void setImage(String path){
        image=Toolkit.getDefaultToolkit().getImage(path);
        this.repaint();
    }

    public void paint(Graphics g){
        g.drawImage(image,0,0,width,height,this);

    }
}
