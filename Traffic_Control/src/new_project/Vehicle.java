package new_project;
import java.awt.*;
import javax.swing.*;

public class Vehicle extends JFrame {
    // Variables declaration for drawing car
    private int pos_x,pos_y;
    private int R,G,B;
    private int length = 20 ,width = 15;
    
    //set car position
    Vehicle(int x_coordinate, int y_coordinate, int type){ //type 1=normal; 2= large; 3=small;   
//        Timer t = new Timer (5,this);
//        t.start();
        this.pos_x = x_coordinate;
        this.pos_y = y_coordinate;

        //check type of Vehicle
        //type 1 = cars, blue
        if (type ==1){
            R = 173;
            G = 216;
            B = 230;
        //type 2 = lorries and buses, brown
        }else if (type ==2){
            length += 20;
            width += 2;
            R = 222;
            G = 184;
            B = 135;
        //type 3 = bikes, pink
        }else if (type == 3){
            length -= 10;
            width -= 2;
            R = 221;
            G = 160;
            B = 221;
        }else {
            type= 1;
        }
    }   
    
    //draw Vehicle
    protected void doDrawing(Graphics g){
        g.setColor(new Color (R,G,B));
        g.fillRect(pos_x, pos_y, length, width);   
       // repaint(); - i think we need to repaint our vehicles as they move
    }
      
}