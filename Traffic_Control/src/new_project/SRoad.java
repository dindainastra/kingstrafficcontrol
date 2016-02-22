package new_project;
import java.awt.*;
import java.awt.geom.AffineTransform;

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
        Graphics2D sroad_border=(Graphics2D) g;
        
        //draw straight road
        AffineTransform old = road.getTransform();
        road.rotate(Math.toRadians(rotation),xStart,yStart);
        road.setColor(Color.gray);
        road.fillRect(xStart, yStart, road_length , road_width);
        
        //draw road divider
        float[] dash1 = {4f, 0f, 2f};
        float[] dash2 = {100f,0f};
        BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
        BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 1.0f, dash2, 0f);
        
        //draw road border
        sroad_border.setStroke(bs2);
        sroad_border.setColor(Color.black);
        sroad_border.drawLine(xStart,road_width/2,xStart+road_length-1,road_width/2);
        sroad_border.drawLine(xStart,(road_width*3/2),xStart+road_length-1,(road_width*3/2));
                
        //draw road lanes
        lane_divider.setStroke(bs1);
        lane_divider.setColor(Color.black);
        lane_divider.drawLine(xStart,road_width,xStart+road_length,road_width);
        
        //draw traffic lights
        if (trafficlight ==10){//traffic lights on the left hand side of the road
            TrafficLight.trafficlight(xStart, road_width/2,  RGB, rotation);
            TrafficLight.doDrawing(g);
            TrafficLight.trafficlight(xStart, road_width,  RGB, rotation);
            TrafficLight.doDrawing(g);
        }else if (trafficlight ==01){//traffic lights on the right hand side of the road
            TrafficLight.trafficlight(xStart+road_length-5, road_width/2,RGB,rotation);
            TrafficLight.doDrawing(g); 
            TrafficLight.trafficlight(xStart+road_length-5, road_width,RGB,rotation);
            TrafficLight.doDrawing(g);  
        }else if (trafficlight ==11){//traffic lights on both side of the road
            TrafficLight.trafficlight(xStart, road_width/2,  RGB, rotation);
            TrafficLight.doDrawing(g);
            TrafficLight.trafficlight(xStart, road_width,  RGB, rotation);
            TrafficLight.doDrawing(g);
            TrafficLight.trafficlight(xStart+road_length-5, road_width/2,RGB,rotation);
            TrafficLight.doDrawing(g); 
            TrafficLight.trafficlight(xStart+road_length-5, road_width,RGB,rotation);
            TrafficLight.doDrawing(g);
        }
        road.setTransform(old);
    } 
}