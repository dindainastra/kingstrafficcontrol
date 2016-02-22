package new_project;
import java.awt.*;

public class TrafficLight{

    // Variables declaration for traffic light
    private static int pos_x, pos_y, rotates;
    private static final int width = 5, length = 25;
    private static int R,G,B;
    
    //set traffic light colour and shape
    static void trafficlight(int x_coordinate, int y_coordinate, int RGB, int rotation){    
        TrafficLight.pos_x = x_coordinate;
        TrafficLight.pos_y = y_coordinate;
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
    protected static void doDrawing(Graphics g){
        Graphics2D light=(Graphics2D) g;
        
        light.setColor(new Color (R,G,B));
        light.rotate(Math.toRadians(rotates));
        light.fillRect(pos_x, pos_y, width, length); 
        System.out.print("traffic is on B\n");
    } 
}