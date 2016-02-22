package new_project;
import java.awt.*;

public class Car{
    // Variables declaration
    private final int pos_x,pos_y;
    private final int R = 173, G = 216, B=230; //pastel blue
    private final int length = 20 ,width = 15;
    
    //set car position
       Car(int x_coordinate, int y_coordinate){
            this.pos_x = x_coordinate;
            this.pos_y = y_coordinate;
       }
       
    //draw Vehicle
    protected void doDrawing(Graphics g){
        g.setColor(new Color (R,G,B));
        g.fillRect(pos_x, pos_y, length, width);   
    }
      
}