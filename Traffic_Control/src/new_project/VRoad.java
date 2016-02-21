package new_project;
import java.awt.*;
import javax.swing.*;

public class VRoad extends JFrame {

    // Variables declaration
    //Set size of road
    private int xStart,yStart;
    private final int road_width = 50;
    private final int road_length = 100;

    VRoad(int x_Start, int y_Start){
        this.xStart = x_Start;
        this.yStart = y_Start;
    }   
    
    protected void doDrawing(Graphics g)
    {
        Graphics2D road=(Graphics2D) g;
        Graphics2D lane_divider=(Graphics2D) g;
        Graphics2D road_border=(Graphics2D) g;
       
        //draw vertical road
        road.setColor(Color.gray);
        road.fillRect(xStart, yStart, road_width , road_length);
        
        //draw road divider
        float[] dash = {4f, 0f, 2f};
       
        //draw road border
        road_border.setColor(Color.white);
        road_border.drawLine(xStart,yStart,xStart,yStart+road_length-1);
        road_border.drawLine(xStart+road_width,yStart,xStart+road_width,yStart+road_length-1);
        
        //draw road lines
        BasicStroke bs3 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f, dash, 2f);
        lane_divider.setStroke(bs3);
        lane_divider.setColor(Color.white);
        lane_divider.drawLine(xStart+road_width/2,yStart,xStart+road_width/2,yStart+road_length-1);
    }
       
}