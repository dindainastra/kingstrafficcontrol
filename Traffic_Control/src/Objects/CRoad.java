
package Objects;
import java.awt.*;
import java.awt.geom.Arc2D;

import Controllers.Node;

//Curve Road
public class CRoad implements Terrain{
    
	// Variables declaration
	private Node currentNode;
	private Node nextNode;
	//Set size of straight road
    private int xStart,yStart;
    private final int arc_angle = 90;
    private int road_width;
    private int start_angle;
    private int color=1;
    // color=1 for grey and color=0 for blue

   /* public CRoad(int x_Start, int y_Start, int start_angle){
        this.start_angle = start_angle;
        this.xStart = x_Start;
        this.yStart = y_Start;
        //this.road_width=100;
    }*/

    public CRoad(int x_Start, int y_Start,int road_width, int start_angle,int color){
        this.start_angle = start_angle;
        this.xStart = x_Start;
        this.yStart = y_Start;
        this.road_width=road_width;
        this.color=color;
    }

    public void doDrawing(Graphics2D g){
        Graphics2D road=(Graphics2D) g;
        Graphics2D lane_divider=(Graphics2D) g;
        Graphics2D sroad_border=(Graphics2D) g;

        // color=1 for grey and color=0 for blue
        if (color==1){
            road.setColor(Color.gray);
        }
        else if (color==0){
            road.setColor(Color.BLUE);
        }


        //draw straight road

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
        //Drawing lanes according to the wisth of the road.

        if (road_width==100)
        {
            sroad_border.draw(new Arc2D.Double(xStart+25,yStart+25,road_width/2,road_width/2,start_angle,arc_angle,Arc2D.OPEN));
        }
        else if(road_width==200){
            sroad_border.draw(new Arc2D.Double(xStart+35,yStart+35,road_width/1.5,road_width/1.5,start_angle,arc_angle,Arc2D.OPEN));
        }


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

	@Override
	public int getLenght() {
		return xStart;  // <--- lenght of the curves???? 
	} 
	
	@Override
	public Node getNextNode() {
		return this.nextNode;
	}

	@Override
	public void setNextNode(Node n) {
		this.nextNode = n;
	}

	@Override
	public Node getCurrentNode() {
		return this.currentNode;
	}

	@Override
	public void setCurrentNode(Node n) {
		this.currentNode = n;
	}
}


