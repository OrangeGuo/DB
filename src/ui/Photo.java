package ui;

import javax.swing.*;
import java.awt.*;


public class Photo extends JPanel {
    private Image image;
    public Photo(){
        image=Toolkit.getDefaultToolkit().getImage("D:\\java图标\\unamed.jpg") ;
    }
    public void setImage(String path){
        image=Toolkit.getDefaultToolkit().getImage(path);
        this.repaint();
    }
    public void paint(Graphics g){
        g.drawImage(image,0,0,99,99,this);

    }
}
