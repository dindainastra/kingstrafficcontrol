
package Objects;
import Controllers.Node;

import java.awt.*;
import java.awt.geom.AffineTransform;

//Straight Road
public class StraightRoad implements Terrain{
    // Variables declaration
    //Set size of straight road
    private int xStart,yStart, trafficlight, RGB1, RGB2, rotation;
    private final int road_length = 100;
    private final int road_width = 50;

    public StraightRoad(int x_Start, int y_Start, int trafficlight, int RGB1, int RGB2, int rotation){
        this.trafficlight = trafficlight;
        this.rotation = rotation;
        this.xStart = x_Start;
        this.yStart = y_Start;
        this.RGB1 = RGB1;
        this.RGB2 = RGB2;
    }   
    
    public void doDrawing(Graphics2D g){
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
        sroad_border.drawLine(xStart,yStart,xStart+road_length-1,yStart);
        sroad_border.drawLine(xStart,yStart+road_width,xStart+road_length-1,yStart+road_width);
                
        //draw road lanes
        lane_divider.setStroke(bs1);
        lane_divider.setColor(Color.white);
        lane_divider.drawLine(xStart,yStart+road_width/2,xStart+road_length,yStart+road_width/2);
        
        //draw traffic lights
        if (trafficlight ==10){//traffic lights on the left hand side of the road
            TrafficLightGUI.trafficlightgui(xStart, yStart,  RGB1, rotation);
            TrafficLightGUI.doDrawing(g);
            TrafficLightGUI.trafficlightgui(xStart,  yStart+road_width/2,  RGB2, rotation);
            TrafficLightGUI.doDrawing(g);
        }else if (trafficlight ==01){//traffic lights on the right hand side of the road
            TrafficLightGUI.trafficlightgui(xStart+road_length-5, yStart,RGB1,rotation);
            TrafficLightGUI.doDrawing(g);
            TrafficLightGUI.trafficlightgui(xStart+road_length-5, yStart+road_width/2,RGB2,rotation);
            TrafficLightGUI.doDrawing(g);
        }else if (trafficlight ==11){//traffic lights on both side of the road
            TrafficLightGUI.trafficlightgui(xStart, yStart,  RGB1, rotation);
            TrafficLightGUI.doDrawing(g);
            TrafficLightGUI.trafficlightgui(xStart,  yStart+road_width/2,  RGB2, rotation);
            TrafficLightGUI.doDrawing(g);
            TrafficLightGUI.trafficlightgui(xStart+road_length-5, yStart,RGB1,rotation);
            TrafficLightGUI.doDrawing(g);
            TrafficLightGUI.trafficlightgui(xStart+road_length-5,  yStart+road_width/2,RGB2,rotation);
            TrafficLightGUI.doDrawing(g);
        }
        road.setTransform(old);
    }

    @Override
	public void setInWhichNodeLocated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getInWhichNodeLocated() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPerson() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPerson() {
		// TODO Auto-generated method stub
		
	} 
}


