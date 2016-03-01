package Objects;
import java.awt.*;

public class Emergency {
    // Variables declaration
    private final int pos_x,pos_y;
    private final int R = 255, G = 0, B=0; //Red
    private final int length = 20 ,width = 15;
    
    //set car position
       Emergency(int x_coordinate, int y_coordinate){
            this.pos_x = x_coordinate;
            this.pos_y = y_coordinate;
       }
       
    //draw Vehicle
    protected void doDrawing(Graphics g){
        g.setColor(new Color (R,G,B));
        g.fillRect(pos_x, pos_y, length, width);   
    }
      
}