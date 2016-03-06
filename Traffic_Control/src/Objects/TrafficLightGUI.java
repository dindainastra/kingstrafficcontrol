
package Objects;
import java.awt.*;

public class TrafficLightGUI {
    
    // Variables declaration for traffic light
    private static int pos_x, pos_y, rotates;
    private static final int width = 3, length = 50;
    private static int R,G,B;
    
    //set traffic light colour and shape
    static void trafficlightgui(int x_coordinate, int y_coordinate, int RGB, int rotation){    
        TrafficLightGUI.pos_x = x_coordinate;
        TrafficLightGUI.pos_y = y_coordinate;
        rotates = rotation;
        if (RGB==1){//RED
            R = 255; G=0; B=0;
        }else if (RGB==2){//GREEN
            R = 0; G=255; B=0;
        }else if (RGB ==3){//AMBER
            R = 255; G=215; B=0;
        }
    }   
    
    //draw traffic light
    protected static void doDrawing(Graphics2D g){
        //AffineTransform old3 = g.getTransform();
        //g.rotate(Math.toRadians(rotates),pos_x,pos_y);
        g.setColor(new Color (R,G,B));
        g.fillRect(pos_x, pos_y, width, length); 
        //g.setTransform(old3);
    } 
    
    
}


    
  