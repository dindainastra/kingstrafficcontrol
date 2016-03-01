
package Objects;
import java.awt.*;
import java.awt.geom.Arc2D;

//Curve Road
public class CRoad implements Terrain{
    // Variables declaration
    //Set size of straight road
    private int xStart,yStart;
    private final int road_width = 100, arc_angle = 90;
    private int start_angle;

    CRoad(int x_Start, int y_Start, int start_angle){
        this.start_angle = start_angle;
        this.xStart = x_Start;
        this.yStart = y_Start;
    }   
    
    public void doDrawing(Graphics2D g){
        Graphics2D road=(Graphics2D) g;
        Graphics2D lane_divider=(Graphics2D) g;
        Graphics2D sroad_border=(Graphics2D) g;
        
        //draw straight road
        road.setColor(Color.gray);
        road.fillArc(xStart, yStart, road_width ,road_width , start_angle, arc_angle);
        
        //draw road divider
        float[] dash1 = {4f, 0f, 2f};
        float[] dash2 = {100f,0f};
        BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
        BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 1.0f, dash2, 0f);
        
        //draw road border
        sroad_border.setStroke(bs2);
        sroad_border.setColor(Color.black);
        sroad_border.draw(new Arc2D.Double(xStart,yStart,road_width,road_width,start_angle,arc_angle,Arc2D.OPEN));
                
        //draw road lanes
        lane_divider.setStroke(bs1);
        lane_divider.setColor(Color.white);
        sroad_border.draw(new Arc2D.Double(xStart+25,yStart+25,road_width/2,road_width/2,start_angle,arc_angle,Arc2D.OPEN));
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


