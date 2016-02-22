package new_project;
import java.awt.*;
import javax.swing.*;

public class Motorbike extends JFrame {
    // Variables declaration
    private final int pos_x,pos_y;
    private final int R=221, G=160, B=221; //pastel purple
    private final int length = 10 ,width = 11;
    
    //set Motorbike position
    Motorbike(int x_coordinate, int y_coordinate){
        this.pos_x = x_coordinate;
        this.pos_y = y_coordinate;
    }
    
    //draw Motorbike
    protected void doDrawing(Graphics g){
        g.setColor(new Color (R,G,B));
        g.fillRect(pos_x, pos_y, length, width);   
       // repaint(); - i think we need to repaint our vehicles as they move
    }  
}