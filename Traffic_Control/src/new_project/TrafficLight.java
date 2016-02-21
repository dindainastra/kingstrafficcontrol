package new_project;
import java.awt.*;
import javax.swing.*;

public class TrafficLight extends JFrame {
    // Variables declaration for traffic light
    private int pos_x, pos_y;
    private final int length = 25;
    private final int width = 5;
    private int R,G,B;
    
    
    //set traffic light colour and shape
    TrafficLight(int x_coordinate, int y_coordinate, int R, int G, int B){    
        this.pos_x = x_coordinate;
        this.pos_y = y_coordinate;
        this.R = R;
        this.G = G;
        this.B = B;
    }   
    
    //draw traffic light
    protected void doDrawing(Graphics g){
        g.setColor(new Color (R,G,B));
        g.fillRect(pos_x, pos_y, width, length);    
    }
      
}