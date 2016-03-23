package Objects;

import java.util.Random;
import java.util.ArrayList;

public class Person {

	private Terrain myPreviousTerrainPosition;
	private String name; //Only for testing purpose
	private int politenessLevel; //1-10 (10 max politeness)
	private boolean pedestrian;
	private int decision;
	private int turningCounter;
	private int direction = 1;

	public int getDirection() {
		return direction;
	}

	public Person(String name, int politenessLevel, boolean pedestrian, Terrain myPreviousTerrainPosition) {
		super();
		this.name = name;
		this.politenessLevel = politenessLevel;
		this.pedestrian = pedestrian;
		this.decision =  0;
		this.turningCounter = 0;
		this.myPreviousTerrainPosition = myPreviousTerrainPosition;
	}

	public void setTurningCounter(){
		turningCounter ++;
	}

	public void setMyPreviousTerrainPosition(Terrain terrainPosition){
		this.myPreviousTerrainPosition = terrainPosition;
	}

	public Terrain getMyPreviousTerrainPosition(){
		return this.myPreviousTerrainPosition;
	}

	public void decide(ArrayList<Terrain> terrainArrayList) {

		this.decision = new Random().nextInt(terrainArrayList.size());
		int counter = 0;
		while (decision == terrainArrayList.indexOf(myPreviousTerrainPosition)) {
			counter++;
			System.out.println(Thread.currentThread().getName() + " Collision found!" + " Decision: " + decision + " size: " + terrainArrayList.size());
			if (counter>10){
				System.exit(1);
			}
			this.decision = new Random().nextInt(terrainArrayList.size());
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPolitenessLevel() {
		return politenessLevel;
	}

	public void setPolitenessLevel(int politenessLevel) {
		this.politenessLevel = politenessLevel;
	}

	public boolean isPedestrian() {
		return pedestrian;
	}

	public void setPedestrian(boolean pedestrian) {
		this.pedestrian = pedestrian;
	}
	
	public void setDecision(int i){
		this.decision = i;
	}
	
	public int getDecision(){
		return decision;
	}

}
