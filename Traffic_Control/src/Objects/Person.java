package Objects;

import Interfaces.Terrain;

import java.util.ArrayList;
import java.util.Random;

public class Person {

    private Terrain myPreviousTerrainPosition;
    private String name; //Only for testing purpose
    private int politenessLevel; //1-10 (10 max politeness)
    private boolean pedestrian;
    private int decision;
    private int turningCounter;

    public Person(String name, int politenessLevel, boolean pedestrian, Terrain myPreviousTerrainPosition) {
        super();
        this.name = name;
        this.politenessLevel = politenessLevel;
        this.pedestrian = pedestrian;
        this.decision = 0;
        this.turningCounter = 0;
        this.myPreviousTerrainPosition = myPreviousTerrainPosition;
    }

    public void setTurningCounter() {
        turningCounter++;
    }

    public Terrain getMyPreviousTerrainPosition() {
        return this.myPreviousTerrainPosition;
    }

    public void setMyPreviousTerrainPosition(Terrain terrainPosition) {
        this.myPreviousTerrainPosition = terrainPosition;
    }

    /**
     * Person get a decision based on how many options he/she has.
     * If there isn't an option we have a cirle, so program is going to be stoped after 1000 collide decisions.
     * Circle is prevented by checking (last Node each time != decided Next Node)
     * @param terrainArrayList is the Neighbour list of Person's current Node-Terrain
     */
    public void decide(ArrayList<Terrain> terrainArrayList) {

        this.decision = new Random().nextInt(terrainArrayList.size());
        int counter = 0;
        while (decision == terrainArrayList.indexOf(myPreviousTerrainPosition) || terrainArrayList.get(decision) == null) {
            counter++;
//            System.out.println(Thread.currentThread().getName() + " Collision found!" + " Decision: " + decision + " size: " + terrainArrayList.size());
            if (counter > 1000) {
                System.exit(1);
            }
            this.decision = new Random().nextInt(terrainArrayList.size());
        }

    }

    /**
     * Returns Person's name
     * @return name
     */
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

    /**
     * Checks if it pedestrian.
     * This will be used for Pedestrian Streets
     * Later implementation
     *
     * @return true or false
     */
    public boolean isPedestrian() {
        return pedestrian;
    }

    public void setPedestrian(boolean pedestrian) {
        this.pedestrian = pedestrian;
    }

    public int getDecision() {
        return decision;
    }

    public void setDecision(int i) {
        this.decision = i;
    }

}
