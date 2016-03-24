package Objects;
import javax.swing.*;

import Controllers.TrafficManagement;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

//Straight Road
public class StraightRoad extends JPanel implements Terrain {

    // Variables declaration

    private ArrayList<Terrain> neighboursTerrainList;
    private ArrayList<Terrain> previousTerrainList;
    private ArrayList<Object> forwardListFlow;
    private ArrayList<Object> backwardListFlow;
    private TrafficManagement trafficManagement;


    //Set size of straight road
    //private int xStart,yStart, trafficlight, RGB1, RGB2, rotation;
    private int xStart,yStart, rotation;
    private int road_length = 100;
    private final int road_width = 100;
    private Polygon poly,poly2;

    //private final TrafficLights trafficLight0, trafficLight1;

    // adding constructor to initialise road_length
    public StraightRoad(int x_Start, int y_Start, int rotation, int road_length, TrafficManagement trafficManagement){
        this.rotation = rotation;
        this.xStart = x_Start;
        this.yStart = y_Start;
        this.road_length=road_length;

        //this.trafficLight0 = new TrafficLights(xStart, yStart+road_width/2, RGB2, rotation);
        //this.trafficLight1 = new TrafficLights(xStart+road_length-5, yStart, RGB1, rotation);

        neighboursTerrainList = new ArrayList<Terrain>();
        previousTerrainList  = new ArrayList<Terrain>();
        forwardListFlow = new ArrayList<Object>();
        backwardListFlow = new ArrayList<Object>();
        this.trafficManagement = trafficManagement;
    }

    public void doDrawing(Graphics2D g){
        Graphics2D road=(Graphics2D) g;

        //draw straight road
//        AffineTransform old = road.getTransform();
//        road.rotate(Math.toRadians(rotation),xStart,yStart);
//        road.setColor(Color.gray);
//        road.fillRect(xStart, yStart, road_length , road_width);
//
//        //draw road divider
//        float[] dash1 = {4f, 0f, 2f};
//        float[] dash2 = {100f,0f};
//        BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
//        BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 1.0f, dash2, 0f);
//
//        //draw road border
//        road.setStroke(bs2);
//        road.setColor(Color.black);
//        road.drawLine(xStart,yStart,xStart+road_length-1,yStart);
//        road.drawLine(xStart,yStart+road_width,xStart+road_length-1,yStart+road_width);
//
//        //draw road lanes
//        road.setStroke(bs1);
//        road.setColor(Color.white);
//        int y=yStart+(road_width);
//        road.drawLine(xStart,yStart+road_width/4,xStart+road_length,yStart+road_width/4);
//
//        road.setStroke(bs2);
//        road.setColor(Color.white);
//        road.drawLine(xStart,yStart+road_width/2,xStart+road_length,yStart+road_width/2);
//
//        road.setStroke(bs1);
//        road.setColor(Color.white);
//        road.drawLine(xStart,(yStart+road_width/2)+road_width/4,xStart+road_length,(yStart+road_width/2)+road_width/4);
//
//        road.setTransform(old);

        if(rotation==0)
        {
            road.setColor(Color.gray);
            road.fillRect(xStart, yStart, road_length , road_width);

            //draw road divider
            float[] dash1 = {4f, 0f, 2f};
            float[] dash2 = {100f,0f};
            BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
            BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 1.0f, dash2, 0f);

            //draw road border
            road.setStroke(bs2);
            road.setColor(Color.black);
            road.drawLine(xStart,yStart,xStart+road_length-1,yStart);
            road.drawLine(xStart,yStart+road_width,xStart+road_length-1,yStart+road_width);

            //draw road lanes
            road.setStroke(bs1);
            road.setColor(Color.white);
            int y=yStart+(road_width);
            road.drawLine(xStart,yStart+road_width/4,xStart+road_length,yStart+road_width/4);

            road.setStroke(bs2);
            road.setColor(Color.white);
            road.drawLine(xStart,yStart+road_width/2,xStart+road_length,yStart+road_width/2);

            road.setStroke(bs1);
            road.setColor(Color.white);
            road.drawLine(xStart,(yStart+road_width/2)+road_width/4,xStart+road_length,(yStart+road_width/2)+road_width/4);
        }
        else
        {
            road.setColor(Color.gray);
            road.fillRect(xStart, yStart, road_width , road_length );

            //draw road divider
            float[] dash1 = {4f, 0f, 2f};
            float[] dash2 = {100f,0f};
            BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
            BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 1.0f, dash2, 0f);

            //draw road border
            road.setStroke(bs2);
            road.setColor(Color.black);
            road.drawLine(xStart,yStart,xStart+road_length-1,yStart);
            road.drawLine(xStart,yStart+road_width,xStart+road_length-1,yStart+road_width);

            //draw road lanes
            road.setStroke(bs1);
            road.setColor(Color.white);
            int y=yStart+(road_width);
            road.drawLine(xStart,yStart+road_width/4,xStart+road_length,yStart+road_width/4);

            road.setStroke(bs2);
            road.setColor(Color.white);
            road.drawLine(xStart,yStart+road_width/2,xStart+road_length,yStart+road_width/2);

            road.setStroke(bs1);
            road.setColor(Color.white);
            road.drawLine(xStart,(yStart+road_width/2)+road_width/4,xStart+road_length,(yStart+road_width/2)+road_width/4);
        }
    }

    public void paintComponent(Graphics gr){
        Graphics2D g = (Graphics2D)gr;
        Graphics2D road=(Graphics2D) g;
       // Graphics2D lane_divider=(Graphics2D) g;
       // Graphics2D sroad_border=(Graphics2D) g;
        //Graphics2D arrows=(Graphics2D) g;

        //draw straight road
//        AffineTransform old = road.getTransform();
//        road.rotate(Math.toRadians(rotation),xStart,yStart);
//        road.setColor(Color.gray);
//        road.fillRect(xStart, yStart, road_length , road_width);
//
//        //draw road divider
//        float[] dash1 = {4f, 0f, 2f};
//        float[] dash2 = {100f,0f};
//        BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
//        BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 1.0f, dash2, 0f);
//
//        //draw road border
//        road.setStroke(bs2);
//        road.setColor(Color.black);
//        road.drawLine(xStart,yStart,xStart+road_length-1,yStart);
//        road.drawLine(xStart,yStart+road_width,xStart+road_length-1,yStart+road_width);
//
//        //draw road lanes
//        road.setStroke(bs1);
//        road.setColor(Color.white);
//        road.drawLine(xStart,yStart+road_width/4,xStart+road_length,yStart+road_width/4);
//
//        road.setStroke(bs2);
//        road.setColor(Color.white);
//        road.drawLine(xStart,yStart+road_width/2,xStart+road_length,yStart+road_width/2);
//
//        road.setStroke(bs1);
//        road.setColor(Color.white);
//        road.drawLine(xStart,(yStart+road_width/2)+road_width/4,xStart+road_length,(yStart+road_width/2)+road_width/4);
//
//        // direction arrows
//        int xPoly[] = {xStart+road_length-5, xStart+road_length-20, xStart+road_length-20};
//        int yPoly[] = {yStart+road_width/4, (yStart+road_width/4)+10, (yStart+road_width/4)-10};
//        int xPoly2[] = {xStart+5, xStart+20, xStart+20};
//        int yPoly2[] = {50+yStart+road_width/4, (yStart+road_width/4)+60, (yStart+road_width/4)+40};
//        poly = new Polygon(xPoly, yPoly, 3);
//        road.drawPolygon(poly);
//        poly2 = new Polygon(xPoly2, yPoly2, 3);
//        road.drawPolygon(poly2);
//        /*arrows.setStroke(bs2);
//        arrows.setColor(Color.black);
//        arrows.drawLine(xStart+10,yStart+road_width/8,xStart+40,yStart+road_width/8);
//        arrows.drawLine(xStart+10,(yStart+road_width/2)+road_width/8,xStart+40,(yStart+road_width/2)+road_width/8);
//        */
//        //arrows.fillPolygon(new int[]{xStart+10,xStart+20},new int[]{(yStart+road_width/8)-5,(yStart+road_width/8)-10},30);
//        road.setTransform(old);

        //Road divider
        float[] dash1 = {4f, 0f, 2f};
        float[] dash2 = {100f,0f};
        BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
        BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 1.0f, dash2, 0f);

        if(rotation==0)
        {
            road.setColor(Color.gray);
            road.fillRect(xStart, yStart, road_length , road_width);

            //draw road divider
//            float[] dash1 = {4f, 0f, 2f};
//            float[] dash2 = {100f,0f};
//            BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
//            BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 1.0f, dash2, 0f);

            //draw road border
            road.setStroke(bs2);
            road.setColor(Color.black);
            road.drawLine(xStart,yStart,xStart+road_length-1,yStart);
            road.drawLine(xStart,yStart+road_width,xStart+road_length-1,yStart+road_width);

            //draw road lanes
            road.setStroke(bs1);
            road.setColor(Color.white);
            road.drawLine(xStart,yStart+road_width/4,xStart+road_length,yStart+road_width/4);

            road.setStroke(bs2);
            road.setColor(Color.white);
            road.drawLine(xStart,yStart+road_width/2,xStart+road_length,yStart+road_width/2);

            road.setStroke(bs1);
            road.setColor(Color.white);
            road.drawLine(xStart,(yStart+road_width/2)+road_width/4,xStart+road_length,(yStart+road_width/2)+road_width/4);

            // direction arrows
            int xPoly[] = {xStart+road_length-5, xStart+road_length-20, xStart+road_length-20};
            int yPoly[] = {yStart+road_width/4, (yStart+road_width/4)+10, (yStart+road_width/4)-10};
            int xPoly2[] = {xStart+5, xStart+20, xStart+20};
            int yPoly2[] = {50+yStart+road_width/4, (yStart+road_width/4)+60, (yStart+road_width/4)+40};
            poly = new Polygon(xPoly, yPoly, 3);
            road.drawPolygon(poly);
            poly2 = new Polygon(xPoly2, yPoly2, 3);
            road.drawPolygon(poly2);
        }else{
            road.setColor(Color.gray);
            road.fillRect(xStart, yStart, road_width , road_length);

//            //draw road divider
//            float[] dash1 = {4f, 0f, 2f};
//            float[] dash2 = {100f,0f};
//            BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
//            BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 1.0f, dash2, 0f);

            //draw road border
            road.setStroke(bs2);
            road.setColor(Color.black);
            road.drawLine(xStart,yStart,xStart,yStart+road_length);
            road.drawLine(xStart+road_width,yStart,xStart+road_width,yStart+road_length);

            //draw road lanes
            road.setStroke(bs1);
            road.setColor(Color.white);
            road.drawLine(xStart+road_width/4,yStart,xStart+road_width/4,yStart+road_length);
            road.setStroke(bs2);
            road.setColor(Color.white);
            road.drawLine(xStart+road_width/2,yStart,xStart+road_width/2,yStart+road_length);
            road.setStroke(bs1);
            road.setColor(Color.white);
            road.drawLine((xStart+road_width/2)+road_width/4,yStart,(xStart+road_width/2)+road_width/4,yStart+road_length);

            // direction arrows
//            int xPoly[] = {xStart+road_length-5, xStart+road_length-20, xStart+road_length-20};
//            int yPoly[] = {yStart+road_width/4, (yStart+road_width/4)+10, (yStart+road_width/4)-10};
//            int xPoly2[] = {xStart+5, xStart+20, xStart+20};
//            int yPoly2[] = {50+yStart+road_width/4, (yStart+road_width/4)+60, (yStart+road_width/4)+40};
//            poly = new Polygon(xPoly, yPoly, 3);
//            road.drawPolygon(poly);
//            poly2 = new Polygon(xPoly2, yPoly2, 3);
//            road.drawPolygon(poly2);

        }


        Color textColor = Color.RED;
        g.setColor(textColor);  //greg

        g.drawString(String.valueOf(this.trafficManagement.getTerrainList().indexOf(this)), xStart, yStart); //greg
    }


    //@Override
    public int getLenght() {
        return this.road_length;
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
        return this.neighboursTerrainList;
    }

    @Override
    public ArrayList<Terrain> getPreviousTerrainList() {
        return this.previousTerrainList;
    }

    public void setNeighboursTerrainList(ArrayList<Terrain> tl) {
        this.neighboursTerrainList = tl;
    }

    @Override
    public void setPreviousTerrainList(ArrayList<Terrain> tl) {
        this.previousTerrainList = tl;
    }

    @Override
    public void setNeighboursTerrainList(Terrain t) {
        this.neighboursTerrainList.add(t);
    }

    @Override
    public void setPreviousTerrainList(Terrain t) {
        this.previousTerrainList.add(t);
    }

    @Override
    public void removeVehicleFromList(Vehicle v) {
        this.forwardListFlow.remove(v);  //if exists here it removes it from here
        this.backwardListFlow.remove(v); //if not, the forwardListFlow is like it is, and the vehicle is removed from
                                            // the second and vise versa
    }

    public int getxStart(){ return xStart; }
    public int getYStart(){ return yStart; }

    @Override
    public int getRotation() {
        return rotation;
    }
}
