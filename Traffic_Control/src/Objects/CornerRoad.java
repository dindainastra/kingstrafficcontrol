
package Objects;
import Controllers.Node;

import java.awt.*;
import java.awt.geom.Arc2D;

//Curve Road
public class CornerRoad implements Terrain{
    // Variables declaration
	private Node currentNode;
	private Node nextNode;
    //Set size of straight road
    private int xStart,yStart;
    private final int arc_angle = 90;
    private int start_angle, road_width = 200, type;

    public CornerRoad(int x_Start, int y_Start, int start_angle, int type){
        this.start_angle = start_angle;
        this.xStart = x_Start;
        this.yStart = y_Start;
        this.type=type;
        // for round about type=1
        //for curved road type=0
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
        //sroad_border.draw(new Arc2D.Double((xStart+25),(yStart+75),road_width/2,road_width/2,start_angle,arc_angle,Arc2D.OPEN));

        lane_divider.setStroke(bs2);
        lane_divider.setColor(Color.white);
        if (type==1) {//round about
            road_width=250;
            sroad_border.draw(new Arc2D.Double(xStart + 60, yStart + 60, road_width / 2, road_width / 2, start_angle, arc_angle, Arc2D.OPEN));
        }
        else if (type==0){//curved road
            road_width=200;
            sroad_border.draw(new Arc2D.Double(xStart + 50, yStart + 50, road_width / 2, road_width / 2, start_angle, arc_angle, Arc2D.OPEN));
        }

        lane_divider.setStroke(bs1);
        lane_divider.setColor(Color.white);
        //sroad_border.draw(new Arc2D.Double(xStart+75,yStart+35,road_width/2,road_width/2,start_angle,arc_angle,Arc2D.OPEN));

    }

	@Override
	public int getLenght() {
		return road_width;  // probably this is the lenght of this. dunno
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


