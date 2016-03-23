
package Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

//Curve Road
public class CornerRoad extends JPanel implements Terrain{

    // Variables declaration
    private ArrayList<Terrain> nextTerrainList;
    private ArrayList<Terrain> previousTerrainList;
    private ArrayList<Object> forwardListFlow;
    private ArrayList<Object> backwardListFlow;

    //Set size of straight road
    private int xStart,yStart;
    private final int arc_angle = 90;
    private int start_angle, road_width = 200, type;
    private Polygon poly;

    public CornerRoad(int x_Start, int y_Start, int start_angle, int type){
        this.start_angle = start_angle;
        this.xStart = x_Start;
        this.yStart = y_Start;
        this.type=type;
        // for round about type=1
        //for curved road type=0

        nextTerrainList = new ArrayList<Terrain>();
        previousTerrainList  = new ArrayList<Terrain>();
        forwardListFlow = new ArrayList<Object>();
        backwardListFlow = new ArrayList<Object>();
    }

    public void doDrawing(Graphics2D g){
        Graphics2D road=(Graphics2D) g;
        Graphics2D lane_divider=(Graphics2D) g;
        Graphics2D sroad_border=(Graphics2D) g;
        
        //draw straight road
        road.setColor(Color.gray);
        if (type==1){
            road_width=250;
        }
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

        //draw road divider
        if (type==0){//curved road
            //road_width=200;
            sroad_border.setColor(Color.white);
            sroad_border.draw(new Arc2D.Double(xStart + 50, yStart + 50, road_width / 2, road_width / 2, start_angle, arc_angle, Arc2D.OPEN));
            lane_divider.setStroke(bs1);
            lane_divider.draw(new Arc2D.Double(xStart + 75,yStart + 75,road_width / 4,road_width / 4,start_angle,arc_angle,Arc2D.OPEN));
            lane_divider.draw(new Arc2D.Double(xStart + 25,yStart + 25,150, 150 , start_angle,arc_angle,Arc2D.OPEN));
        }else if (type==1){//roundabout
            //road_width=250;
            sroad_border.setStroke(bs2);
            sroad_border.setColor(Color.black);
            sroad_border.draw(new Arc2D.Double(xStart + 90, yStart + 90, road_width / 4, road_width /4, start_angle, arc_angle, Arc2D.OPEN));
            road.setColor(Color.BLUE);
            road.fillOval(xStart + 90, yStart + 90, road_width / 4, road_width /4);
            lane_divider.setStroke(bs1);
            lane_divider.setColor(Color.white);
            lane_divider.draw(new Arc2D.Double(xStart+40,yStart+40,road_width-(road_width/3),road_width-(road_width/3),start_angle,arc_angle,Arc2D.OPEN));
        }
    }

    public void paintComponent(Graphics gr){
        Graphics2D g = (Graphics2D) gr;
        Graphics2D road=(Graphics2D) g;
        //Graphics2D lane_divider=(Graphics2D) g;
        //Graphics2D sroad_border=(Graphics2D) g;

        //draw straight road
        road.setColor(Color.gray);
        if (type==1){
            road_width=250;
        }
        road.fillArc(xStart, yStart, road_width ,road_width , start_angle, arc_angle);

        //draw road divider
        float[] dash1 = {4f, 0f, 2f};
        float[] dash2 = {100f,0f};
        BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
        BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 1.0f, dash2, 0f);

        //draw road border
        road.setStroke(bs2);
        road.setColor(Color.black);
        road.draw(new Arc2D.Double(xStart,yStart,road_width,road_width,start_angle,arc_angle,Arc2D.OPEN));

        //draw road divider
        if (type==0){//curved road
            //road_width=200;
            road.setColor(Color.white);
            road.draw(new Arc2D.Double(xStart + 50, yStart + 50, road_width / 2, road_width / 2, start_angle, arc_angle, Arc2D.OPEN));
            road.setStroke(bs1);
            road.draw(new Arc2D.Double(xStart + 75,yStart + 75,road_width / 4,road_width / 4,start_angle,arc_angle,Arc2D.OPEN));
            road.draw(new Arc2D.Double(xStart + 25,yStart + 25,150, 150 , start_angle,arc_angle,Arc2D.OPEN));
        }else if (type==1){//roundabout
            //road_width=250;
            road.setStroke(bs2);
            road.setColor(Color.black);
            road.draw(new Arc2D.Double(xStart + 90, yStart + 90, road_width / 4, road_width /4, start_angle, arc_angle, Arc2D.OPEN));
            road.setColor(Color.BLUE);
            road.fillOval(xStart + 90, yStart + 90, road_width / 4, road_width /4);
            road.setStroke(bs1);
            road.setColor(Color.white);
            road.draw(new Arc2D.Double(xStart+40,yStart+40,road_width-(road_width/3),road_width-(road_width/3),start_angle,arc_angle,Arc2D.OPEN));
            int xPoly[] = {xStart+40, xStart+30, xStart+50};
            int yPoly[] = {yStart+110, yStart+125,yStart+125 };
            poly = new Polygon(xPoly, yPoly, 3);
            road.drawPolygon(poly);
        }
    }


	@Override
	public int getLenght() {
		return road_width;  // probably this is the lenght of this. dunno
	}


    @Override
    public void setForwardListFlow(ArrayList<Object> ol) {
        this.forwardListFlow = ol;
    }

    @Override
    public void setForwardListFlow(Object o) {
        this.forwardListFlow.add(o);
    }

    @Override
    public ArrayList<Object> getForwardListFlow() {
        return this.forwardListFlow;
    }

    @Override
    public void setBackwardListFlow(ArrayList<Object> ol) {
        this.backwardListFlow = ol;
    }

    @Override
    public void setBackwardListFlow(Object o) {
        this.backwardListFlow.add(o);
    }

    @Override
    public ArrayList<Object> getBackwardListFlow() {
        return this.backwardListFlow;
    }

    @Override
    public ArrayList<Terrain> getNeighboursTerrainList() {
        return this.nextTerrainList;
    }

    @Override
    public ArrayList<Terrain> getPreviousTerrainList() {
        return this.previousTerrainList;
    }

    public void setNeighboursTerrainList(ArrayList<Terrain> tl) {
        this.nextTerrainList = tl;
    }

    @Override
    public void setPreviousTerrainList(ArrayList<Terrain> tl) {
        this.previousTerrainList = tl;
    }

    @Override
    public void setNeighboursTerrainList(Terrain t) {
        this.nextTerrainList.add(t);
    }

    @Override
    public void setPreviousTerrainList(Terrain t) {
        this.previousTerrainList.add(t);
    }

    @Override
    public void removeVehicleFromList(Vehicle v) {
        this.forwardListFlow.remove(v);  //if exists here it removes it from here
        this.backwardListFlow.remove(v); //if not, the forwardListFlow is like it is, and the vehicle is removed from the second and vise versa
    }

    @Override
    public int getxStart() {
        return 0;
    }

    @Override
    public int getYStart() {
        return 0;
    }

    public int getcornerLength(){
        return 0;
    }
}


