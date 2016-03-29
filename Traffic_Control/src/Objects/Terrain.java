package Objects;

import java.awt.Graphics2D;
import java.util.ArrayList;

public interface Terrain {


    void doDrawing(Graphics2D g);

    int  getLenght();

    void setForwardListFlow(ArrayList<Object> ol);
    void setForwardListFlow(Object o);
    ArrayList<Object> getForwardListFlow();

    void setBackwardListFlow(ArrayList<Object> ol);
    void setBackwardListFlow(Object o);
    ArrayList<Object> getBackwardListFlow();

    ArrayList<Terrain> getNeighboursTerrainList();
    ArrayList<Terrain> getPreviousTerrainList();

    void setNeighboursTerrainList(ArrayList<Terrain> tl);
    void setPreviousTerrainList(ArrayList<Terrain> tl);

    void setNeighboursTerrainList(Terrain t);
    void setPreviousTerrainList(Terrain t);

    void removeVehicleFromList(Vehicle v);

    int getxStart();
    int getYStart();
    int getRotation();
}
