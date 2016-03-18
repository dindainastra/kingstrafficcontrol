package Objects;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SquareJunction extends JPanel implements Terrain{

    // Variables declaration
    private ArrayList<Terrain> nextTerrainList;
    private ArrayList<Terrain> previousTerrainList;
    private ArrayList<Object> forwardListFlow;
    private ArrayList<Object> backwardListFlow;

    //Set size of road
    private int xStart,yStart;
    private final int road_width = 100;

    public SquareJunction(int x_Start, int y_Start){
        this.xStart = x_Start;
        this.yStart = y_Start;

        nextTerrainList = new ArrayList<Terrain>();
        previousTerrainList  = new ArrayList<Terrain>();
        forwardListFlow = new ArrayList<Object>();
        backwardListFlow = new ArrayList<Object>();
    }   
    
    public void doDrawing(Graphics2D g){
        Graphics2D road=(Graphics2D) g;
        Graphics2D lane_divider=(Graphics2D) g;
        Graphics2D road_border=(Graphics2D) g;
        
        //draw junction
        road.setColor(Color.gray);
        road.fillRect(xStart, yStart, road_width , road_width);
        
        //draw road divider
        float[] dash = {4f, 0f, 2f};
        BasicStroke bs3 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f, dash, 2f);
       
        //draw road border
        road_border.setStroke(bs3);
        road_border.setColor(Color.white);
        road_border.drawLine(xStart,yStart,xStart,yStart+road_width-1);//left
        road_border.drawLine(xStart+road_width,yStart,xStart+road_width,yStart+road_width-1);//right
        road_border.drawLine(xStart,yStart,xStart+road_width-1,yStart);//top
        road_border.drawLine(xStart,yStart+road_width,xStart+road_width-1,yStart+road_width);//bottom
        
        //draw road lines
        lane_divider.setStroke(bs3);
        lane_divider.setColor(Color.white);
        lane_divider.drawLine(xStart+road_width/2,yStart,xStart+road_width/2,yStart+road_width-1);//horizontal
        lane_divider.drawLine(xStart,yStart+road_width/2,xStart+road_width,yStart+road_width/2);//vertical
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing((Graphics2D)g);
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
    public void setForwardListFlow(Object o) {
        this.getForwardListFlow().add(o);
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


}