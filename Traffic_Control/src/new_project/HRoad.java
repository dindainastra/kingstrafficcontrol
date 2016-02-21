package new_project;
import java.awt.*;
import javax.swing.*;
//import new_project.RectanglesExample;


public class HRoad extends JFrame {

    // Variables declaration
    //Set size of horizontal road
    private int xStart,yStart;
    private final int road_length = 100;
    private final int road_width = 50;

    HRoad(int x_Start, int y_Start){
        this.xStart = x_Start;
        this.yStart = y_Start;
    }   
    
    protected void doDrawing(Graphics g)
    {
        Graphics2D road=(Graphics2D) g;
        Graphics2D lane_divider=(Graphics2D) g;
        Graphics2D hroad_border=(Graphics2D) g;
       
        //draw horizontal road
        road.setColor(Color.gray);
        road.fillRect(xStart, yStart, road_length , road_width);
        
        //draw road border
        hroad_border.setColor(Color.black);//bug - draws dash lines rather than solid
        hroad_border.drawLine(xStart,road_width/2,xStart+road_length-1,road_width/2);
        hroad_border.drawLine(xStart,(road_width*3/2),xStart+road_length-1,(road_width*3/2));
        
        //draw road divider
        float[] dash = {4f, 0f, 2f};
        BasicStroke bs3 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f, dash, 2f);
        
        //draw road lines
        lane_divider.setStroke(bs3);
        lane_divider.setColor(Color.white);
        lane_divider.drawLine(xStart,road_width,xStart+road_length,road_width);
    }
       
}