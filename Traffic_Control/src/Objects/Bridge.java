package Objects;

import java.awt.Graphics2D;
import java.util.ArrayList;

//bridge dummy comment

public class Bridge implements Terrain{

	private ArrayList<Terrain> nextTerrainList;
	private ArrayList<Terrain> previousTerrainList;
	private ArrayList<Object> forwardListFlow;
	private ArrayList<Object> backwardListFlow;
	private int lenght;
	
	@Override
	public void doDrawing(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public int getLenght() {
		return this.lenght;
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
	public ArrayList<Terrain> getNextTerrainList() {
		return this.nextTerrainList;
	}

	@Override
	public ArrayList<Terrain> getPreviousTerrainList() {
		return this.previousTerrainList;
	}

	@Override
	public void setNextTerrainList(ArrayList<Terrain> tl) {
		this.nextTerrainList = tl;
	}

	@Override
	public void setPreviousTerrainList(ArrayList<Terrain> tl) {
		this.previousTerrainList = tl;
	}

	@Override
	public void setNextTerrainList(Terrain t) {
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
