package new_project;
import java.awt.*;

public class Bike{
    // Variables declaration
    private final int pos_x,pos_y;
    private final int R=128, G=0, B=128; //pastel purple
    private final int length = 10 ,width = 11;
    
    //set Bike position
    Bike(int x_coordinate, int y_coordinate){
        this.pos_x = x_coordinate;
        this.pos_y = y_coordinate;
    }
    
    //draw Bike
    protected void doDrawing(Graphics g){
        g.setColor(new Color (R,G,B));
        g.fillRect(pos_x, pos_y, length, width);   
       // repaint(); - i think we need to repaint our vehicles as they move
    }  
}