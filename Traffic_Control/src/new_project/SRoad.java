package new_project;
import java.awt.*;

public class SRoad{

    // Variables declaration
    //Set size of straight road
    private int xStart,yStart, trafficlight, RGB, rotation;
    private final int road_length = 100;
    private final int road_width = 50;

    SRoad(int x_Start, int y_Start, int trafficlight, int RGB, int rotation){
        this.trafficlight = trafficlight;
        this.rotation = rotation;
        this.xStart = x_Start;
        this.yStart = y_Start;
        this.RGB = RGB;
    }   
    
    protected void doDrawing(Graphics2D g){
        Graphics2D road=(Graphics2D) g;
        Graphics2D lane_divider=(Graphics2D) g;
        Graphics2D hroad_border=(Graphics2D) g;
        
        //draw straight road
        road.rotate(Math.toRadians(rotation),xStart,yStart);
        road.setColor(Color.gray);
        road.fillRect(xStart, yStart, road_length , road_width);
        
        //draw road border
        hroad_border.setColor(Color.white);//bug - draws dash lines rather than solid
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
        
        if (trafficlight ==10){//traffic lights on the left hand side
            TrafficLight.trafficlight(xStart, road_width/2,  RGB, rotation);
            TrafficLight.doDrawing(g);
            TrafficLight.trafficlight(xStart, road_width,  RGB, rotation);
            TrafficLight.doDrawing(g);
            System.out.print(" Reached here B:" + RGB+ " .\n");
        }else if (trafficlight ==01){//traffic lights on the right hand side
            TrafficLight.trafficlight(xStart+road_length-5, road_width/2,RGB,rotation);
            TrafficLight.doDrawing(g); 
            TrafficLight.trafficlight(xStart+road_length-5, road_width,RGB,rotation);
            TrafficLight.doDrawing(g);  
            System.out.print(" Reached here C:" + RGB+ " .\n");
        }
    } 
}