package Objects;

import java.awt.Graphics2D;
import java.util.ArrayList;

public interface Terrain {


	public void doDrawing(Graphics2D g);

	public int  getLenght();

    public void setForwardListFlow(ArrayList<Object> ol);
    public void setForwardListFlow(Object o);
    public ArrayList<Object> getForwardListFlow();

    public void setBackwardListFlow(ArrayList<Object> ol);
    public void setBackwardListFlow(Object o);
    public ArrayList<Object> getBackwardListFlow();

    public ArrayList<Terrain> getNeighboursTerrainList();
    public ArrayList<Terrain> getPreviousTerrainList();

    public void setNeighboursTerrainList(ArrayList<Terrain> tl);
    public void setPreviousTerrainList(ArrayList<Terrain> tl);

    public void setNeighboursTerrainList(Terrain t);
    public void setPreviousTerrainList(Terrain t);

    public void removeVehicleFromList(Vehicle v);

    public int getxStart();
    public int getYStart();
}
