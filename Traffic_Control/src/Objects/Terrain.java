package Objects;

import java.awt.*;
import java.util.ArrayList;

public interface Terrain {


    void doDrawing(Graphics2D g);

    int getLenght();

    void setForwardListFlow(ArrayList<Object> ol);

    ArrayList<Object> getForwardListFlow();

    void setForwardListFlow(Object o);

    void setBackwardListFlow(ArrayList<Object> ol);

    ArrayList<Object> getBackwardListFlow();

    void setBackwardListFlow(Object o);

    ArrayList<Terrain> getNeighboursTerrainList();

    void setNeighboursTerrainList(Terrain t);

    ArrayList<Terrain> getPreviousTerrainList();

    void setPreviousTerrainList(Terrain t);

    void setNeighboursTerrainList(ArrayList<Terrain> tl);

    void setPreviousTerrainList(ArrayList<Terrain> tl);

    void removeVehicleFromList(Vehicle v);

    int getxStart();

    int getYStart();

    int getRotation();
}
