package Nodes;

import Controllers.TrafficManagement;
import Interfaces.Terrain;
import Interfaces.Vehicle;

import javax.swing.*;
import java.awt.*;
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
    private int xStart, yStart, rotation;
    private int road_length = 100;

    //private final TrafficLights trafficLight0, trafficLight1;

    // adding constructor to initialise road_length
    public StraightRoad(int x_Start, int y_Start, int rotation, int road_length, TrafficManagement trafficManagement) {
        this.rotation = rotation;
        this.xStart = x_Start;
        this.yStart = y_Start;
        this.road_length = road_length;

        //this.trafficLight0 = new TrafficLights(xStart, yStart+road_width/2, RGB2, rotation);
        //this.trafficLight1 = new TrafficLights(xStart+road_length-5, yStart, RGB1, rotation);

        neighboursTerrainList = new ArrayList<Terrain>();
        previousTerrainList = new ArrayList<Terrain>();
        forwardListFlow = new ArrayList<Object>();
        backwardListFlow = new ArrayList<Object>();
        this.trafficManagement = trafficManagement;
    }

    public void doDrawing(Graphics2D g) {
        Graphics2D road = g;

        //Road divider
        float[] dash1 = {4f, 0f, 2f};
        float[] dash2 = {100f, 0f};
        BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
        BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash2, 0f);

        int road_width = 100;
        Polygon poly;
        Polygon poly2;
        if (rotation == 0) {
            road.setColor(Color.gray);
            road.fillRect(xStart, yStart, road_length, road_width);

            //draw road border
            road.setStroke(bs2);
            road.setColor(Color.black);
            road.drawLine(xStart, yStart, xStart + road_length - 1, yStart);
            road.drawLine(xStart, yStart + road_width, xStart + road_length - 1, yStart + road_width);

            //draw road lanes
            road.setStroke(bs1);
            road.setColor(Color.white);
            road.drawLine(xStart, yStart + road_width / 4, xStart + road_length, yStart + road_width / 4);

            road.setStroke(bs2);
            road.setColor(Color.white);
            road.drawLine(xStart, yStart + road_width / 2, xStart + road_length, yStart + road_width / 2);

            road.setStroke(bs1);
            road.setColor(Color.white);
            road.drawLine(xStart, (yStart + road_width / 2) + road_width / 4, xStart + road_length, (yStart + road_width / 2) + road_width / 4);

            // direction arrows
            int xPoly[] = {xStart + road_length - 5, xStart + road_length - 20, xStart + road_length - 20};
            int yPoly[] = {yStart + road_width / 4, (yStart + road_width / 4) + 10, (yStart + road_width / 4) - 10};
            int xPoly2[] = {xStart + 5, xStart + 20, xStart + 20};
            int yPoly2[] = {50 + yStart + road_width / 4, (yStart + road_width / 4) + 60, (yStart + road_width / 4) + 40};
            poly = new Polygon(xPoly, yPoly, 3);
            road.drawPolygon(poly);
            poly2 = new Polygon(xPoly2, yPoly2, 3);
            road.drawPolygon(poly2);
        } else {
            road.setColor(Color.gray);
            road.fillRect(xStart, yStart, road_width, road_length);

            //draw road border
            road.setStroke(bs2);
            road.setColor(Color.black);
            road.drawLine(xStart, yStart, xStart, yStart + road_length);
            road.drawLine(xStart + road_width, yStart, xStart + road_width, yStart + road_length);

            //draw road lanes
            road.setStroke(bs1);
            road.setColor(Color.white);
            road.drawLine(xStart + road_width / 4, yStart, xStart + road_width / 4, yStart + road_length);
            road.setStroke(bs2);
            road.setColor(Color.white);
            road.drawLine(xStart + road_width / 2, yStart, xStart + road_width / 2, yStart + road_length);
            road.setStroke(bs1);
            road.setColor(Color.white);
            road.drawLine((xStart + road_width / 2) + road_width / 4, yStart, (xStart + road_width / 2) + road_width / 4, yStart + road_length);

            int xPoly[] = {xStart + road_width - 25, xStart + road_width - 15, xStart + road_width - 35};
            int yPoly[] = {yStart + road_length - 5, yStart + road_length - 20, yStart + road_length - 20};

            int xPoly2[] = {xStart + 25, xStart + 15, xStart + 35};
            int yPoly2[] = {yStart + 5, yStart + 20, yStart + 20};

            poly = new Polygon(xPoly, yPoly, 3);
            road.drawPolygon(poly);
            poly2 = new Polygon(xPoly2, yPoly2, 3);
            road.drawPolygon(poly2);

        }


        Color textColor = Color.RED;
        g.setColor(textColor);  //greg

        g.drawString(String.valueOf(this.trafficManagement.getTerrainList().indexOf(this)), xStart, yStart); //greg

    }

    @Override
    public int getLenght() {
        return this.road_length;
    }

    @Override
    public void setForwardListFlow(ArrayList<Object> ol) {
        this.forwardListFlow = ol;
    }

    @Override
    public ArrayList<Object> getForwardListFlow() {
        return this.forwardListFlow;
    }

    @Override
    public void setForwardListFlow(Object o) {
        this.forwardListFlow.add(o);
    }

    @Override
    public void setBackwardListFlow(ArrayList<Object> ol) {
        this.backwardListFlow = ol;
    }

    @Override
    public ArrayList<Object> getBackwardListFlow() {
        return this.backwardListFlow;
    }

    @Override
    public void setBackwardListFlow(Object o) {
        this.backwardListFlow.add(o);
    }

    @Override
    public ArrayList<Terrain> getNeighboursTerrainList() {
        return this.neighboursTerrainList;
    }

    public void setNeighboursTerrainList(ArrayList<Terrain> tl) {
        this.neighboursTerrainList = tl;
    }

    @Override
    public void setNeighboursTerrainList(Terrain t) {
        this.neighboursTerrainList.add(t);
    }

    @Override
    public ArrayList<Terrain> getPreviousTerrainList() {
        return this.previousTerrainList;
    }

    @Override
    public void setPreviousTerrainList(ArrayList<Terrain> tl) {
        this.previousTerrainList = tl;
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

    public int getxStart() {
        return xStart;
    }

    public int getYStart() {
        return yStart;
    }

    @Override
    public int getRotation() {
        return rotation;
    }
}
