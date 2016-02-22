package new_project;
import java.awt.*;

public class LorriesBus {
    // Variables declaration
    private final int pos_x,pos_y;
    private final int R = 222, G = 184, B = 135;//pastel brown
    private final int length = 40 ,width = 17;
    
    //set lorry position
    LorriesBus(int x_coordinate, int y_coordinate){  
        this.pos_x = x_coordinate;
        this.pos_y = y_coordinate;
    }
    //draw lorry
    protected void doDrawing(Graphics g){
        g.setColor(new Color (R,G,B));
        g.fillRect(pos_x, pos_y, length, width);   
       // repaint(); - i think we need to repaint our vehicles as they move
    }
      
}