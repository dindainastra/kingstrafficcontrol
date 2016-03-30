package Nodes;

import Controllers.TrafficManagement;
import Interfaces.Terrain;
import Interfaces.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SquareJunction extends JPanel implements Terrain {

    private final int road_width = 100;
    // Variables declaration
    private ArrayList<Terrain> nextTerrainList;
    private ArrayList<Terrain> previousTerrainList;
    private ArrayList<Object> forwardListFlow;
    private ArrayList<Object> backwardListFlow;
    private TrafficManagement trafficManagement;
    //Set size of road
    private int xStart, yStart, close;

    public SquareJunction(int x_Start, int y_Start, TrafficManagement trafficManagement) {
        this.xStart = x_Start;
        this.yStart = y_Start;

        nextTerrainList = new ArrayList<Terrain>();
        previousTerrainList = new ArrayList<Terrain>();
        forwardListFlow = new ArrayList<Object>();
        backwardListFlow = new ArrayList<Object>();
        this.trafficManagement = trafficManagement;
    }

    public SquareJunction(int x_Start, int y_Start, TrafficManagement trafficManagement, int close) {
        this.xStart = x_Start;
        this.yStart = y_Start;
        this.close = close;//1= close junction on rightside; 2=leftside; 3=close top; 4=close bottom; 0=open on all sides

        nextTerrainList = new ArrayList<Terrain>();
        previousTerrainList = new ArrayList<Terrain>();
        forwardListFlow = new ArrayList<Object>();
        backwardListFlow = new ArrayList<Object>();
        this.trafficManagement = trafficManagement;
    }


   /* public SquareJunction(int x_Start, int y_Start, int close){
        this.xStart = x_Start;
        this.yStart = y_Start;
        this.close=close;//1= close junction on rightside; 2=leftside; 3=close top; 4=close bottom.

        nextTerrainList = new ArrayList<Terrain>();
        previousTerrainList  = new ArrayList<Terrain>();
        forwardListFlow = new ArrayList<Object>();
        backwardListFlow = new ArrayList<Object>();
    }*/


    public void doDrawing(Graphics2D g) {
        Graphics2D road = g;

        //draw junction
        road.setColor(Color.gray);
        road.fillRect(xStart, yStart, road_width, road_width);

        //draw road divider
        float[] dash = {4f, 0f, 2f};
        float[] dash2 = {100f, 0f};
        BasicStroke bs3 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash, 2f);
        BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash2, 0f);

        //draw road border
        road.setColor(Color.white);
        road.drawLine(xStart, yStart, xStart, yStart + road_width - 1);//left
        road.drawLine(xStart + road_width, yStart, xStart + road_width, yStart + road_width - 1);//right
        road.drawLine(xStart, yStart, xStart + road_width - 1, yStart);//top
        road.drawLine(xStart, yStart + road_width, xStart + road_width - 1, yStart + road_width);//bottom


        if (close == 1) {//close right
            road.setStroke(bs2);
            road.setColor(Color.black);
            road.drawLine(xStart + road_width, yStart, xStart + road_width, yStart + road_width - 1);//right
        } else if (close == 2) {//close left
            road.setStroke(bs2);
            road.setColor(Color.black);
            road.drawLine(xStart, yStart, xStart, yStart + road_width - 1);//left
        } else if (close == 3) {//close top
            road.setStroke(bs2);
            road.setColor(Color.black);
            road.drawLine(xStart, yStart, xStart + road_width - 1, yStart);//top
        } else if (close == 4) {//close bottom
            road.setStroke(bs2);
            road.setColor(Color.black);
            road.drawLine(xStart, yStart + road_width, xStart + road_width - 1, yStart + road_width);//bottom
        }


        //draw road lines
        road.setStroke(bs3);
        road.setColor(Color.white);
        road.drawLine(xStart + road_width / 2, yStart, xStart + road_width / 2, yStart + road_width - 1);//horizontal
        road.drawLine(xStart, yStart + road_width / 2, xStart + road_width, yStart + road_width / 2);//vertical

        Color textColor = Color.YELLOW;
        g.setColor(textColor);  //greg

        g.drawString(String.valueOf(this.trafficManagement.getTerrainList().indexOf(this)), xStart, yStart); //greg

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing((Graphics2D) g);
    }


    //@Override
    public int getLenght() {
        return this.road_width;
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
        this.getForwardListFlow().add(o);
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
        return this.nextTerrainList;
    }

    @Override
    public void setNeighboursTerrainList(Terrain t) {
        this.nextTerrainList.add(t);
    }

    public void setNeighboursTerrainList(ArrayList<Terrain> tl) {
        this.nextTerrainList = tl;
    }

    @Override
    public ArrayList<Terrain> getPreviousTerrainList() {
        return this.previousTerrainList;
    }

    @Override
    public void setPreviousTerrainList(Terrain t) {
        this.previousTerrainList.add(t);
    }

    @Override
    public void setPreviousTerrainList(ArrayList<Terrain> tl) {
        this.previousTerrainList = tl;
    }

    @Override
    public void removeVehicleFromList(Vehicle v) {
        this.forwardListFlow.remove(v);  //if exists here it removes it from here
        this.backwardListFlow.remove(v); //if not, the forwardListFlow is like it is, and the vehicle is removed from the second and vise versa
    }

    @Override
    public int getxStart() {
        return xStart;
    }

    @Override
    public int getYStart() {
        return yStart;
    }

    @Override
    public int getRotation() {
        return 0;
    }
}