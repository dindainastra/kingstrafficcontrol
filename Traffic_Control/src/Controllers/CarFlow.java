package Controllers;

import Objects.Draw;
import Objects.Terrain;
import Objects.Vehicle;
import Objects.TrafficLights;
import Objects.Car;
import Objects.StraightRoad;
import Objects.CornerRoad;
import Objects.SquareJunction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class CarFlow implements Runnable {

    private Draw map;
    private Terrain aTerrain;
    private int flowDirection;   // 0 <---   and 1 ---->
    private Timer timer = null;
    private final int delay = 30;
    public enum Direction{LEFT, RIGHT, UP, DOWN};
    private ArrayList<Object> currectFlowList;
    private TrafficManagement trafficManagement;

    public CarFlow(Terrain t, Draw map, int direction){
        this.aTerrain = t;
        this.map = map;
        this.flowDirection = direction;
        timer = new Timer(delay, null);
    }
    
    public CarFlow(Terrain t, Draw map, int direction, TrafficManagement trafficManagement){
        this.aTerrain = t;
        this.map = map;
        this.flowDirection = direction;
        timer = new Timer(delay, null);
        if (flowDirection == 1)
            currectFlowList = this.aTerrain.getForwardListFlow();
        else
            currectFlowList = this.aTerrain.getBackwardListFlow();
        this.trafficManagement = trafficManagement;
    }
    
    /**
     * Resume the cars flow through the system
     * Repaint the map whilst moving
     */
    private void startFlow(){
        try {
            //while (this.isThisTerrainBusy()){}

            if (this.flowDirection == 1) {

                if (isThereATrafficLight(this.aTerrain.getForwardListFlow()))
                    while (!checkIfTrafficLightIsGreen(((TrafficLights) aTerrain.getForwardListFlow().get(0)))) {}

                for (Object o : currectFlowList) {
                    if (o instanceof Vehicle) {
                    	 
                    	moveThisVehicleToTheNextCorrectStack((Vehicle) o);
                    	
                        System.out.println("Runner");

                        //int len = this.aTerrain.getLenght();
                        //final int roadSteps = this.aTerrain.getLenght() / c.getLength();
                        Terrain terrain = null;

                        if(this.aTerrain instanceof CornerRoad){
                            System.out.println("Corner");
                            terrain = (CornerRoad)this.aTerrain;

                            moveObject((Car)o, terrain);
                        }else if(this.aTerrain instanceof StraightRoad){
                            objectStraightRoad((Car)o, (StraightRoad)this.aTerrain);
                        }else if(this.aTerrain instanceof SquareJunction){
                            System.out.println("SquareJunction");
                            terrain = (SquareJunction)this.aTerrain;

                            //MAYBE CREATE THREAD POOL TO FIX OUR OF MEMORY ISSUE
                            moveObject((Car)o, terrain);
                        }

                        //c.move(sRoad.getxStart(), sRoad.getYStart(), roadSteps);
                    } else {
                        System.out.println("Else "+o.toString());
                    }
                }
            } else {
                if (isThereATrafficLight(this.aTerrain.getBackwardListFlow()))
                    while (!checkIfTrafficLightIsGreen(((TrafficLights) aTerrain.getBackwardListFlow().get(0)))) {}

                for (Object o : currectFlowList) {
                    if (o instanceof Vehicle) {

                        // TODO: 16/03/2016 here should be the timer, and when timer stops run the following
                        moveThisVehicleToTheNextCorrectStack((Vehicle) o);
                        System.out.println("Runner vehicle Backwards");
                    } else {

                    }
                }
            }
//            map.revalidate();
//            map.repaint();
//            Thread.sleep(1000);

        } catch(Exception e) {
            System.out.println("Error: "+e.getLocalizedMessage());
        }
    }

    public void moveThisVehicleToTheNextCorrectStack(Vehicle v){

        String threadName  = Thread.currentThread().getName();

        if (
                (trafficManagement.getTerrainList().indexOf(this.aTerrain) != 1 && this.flowDirection == 1)
                ||
                (trafficManagement.getTerrainList().indexOf(this.aTerrain) != 0 && this.flowDirection == 0)

                )
        { //exit node on flow -> and  <-

            v.getPerson().decide(this.aTerrain.getNeighboursTerrainList());
            int decision = v.getPerson().getDecision();

            if (this.aTerrain instanceof SquareJunction)
                if (decision%2 ==0)
                    this.aTerrain.getNeighboursTerrainList().get(decision).getForwardListFlow().add(v);
                else
                    this.aTerrain.getNeighboursTerrainList().get(decision).getBackwardListFlow().add(v);
            else{
                if (this.flowDirection == 1)
                    this.aTerrain.getNeighboursTerrainList().get(decision).getForwardListFlow().add(v);
                else
                    this.aTerrain.getNeighboursTerrainList().get(decision).getBackwardListFlow().add(v);
            }

            try {
                v.getPerson().setMyPreviousTerrainPosition(this.aTerrain);
                System.out.println("Flow: " +this.flowDirection + " Delete "+v.toString()
                            + " from Node: " + trafficManagement.getTerrainList().indexOf(this.aTerrain)
                            + " and move to Node: " + this.trafficManagement.getTerrainList().indexOf(aTerrain.getNeighboursTerrainList().get(decision))
                            + " and worker is: " + threadName);
            } catch (Exception e){
                System.out.println("Thread: "+ threadName + " cant removed!");
            }

        }else
            System.out.println("EXIT NODE: Delete "+v.toString() + " and worker is: "+threadName);
        this.currectFlowList.remove(v);
    }

    public void printALL(){
        System.out.println(Thread.currentThread().getName());
    }    
    
    private void objectStraightRoad(final Car car, final StraightRoad road){
        final int roadLength = road.getLenght();
        final Direction directionPath = getDirection(car, road);

        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean stop = false;
                System.out.println("Straight road. Direction is "+directionPath);

                //Road should say if Y++ of X
                car.move(directionPath);
                if(roadLength == car.get_pos_x()) { stop = true; }

                //SET DELAY BEFORE NEXT CAR STARTS
                map.repaint();

                if(stop) {
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    //Possibly create this for each road so the if..else isnt checked every time the timer is ran
    private void moveObject(final Car car, final Terrain terrain){
        final int roadLength = terrain.getLenght();
        final Direction directionPath = getDirection(car, terrain);

        //MAYBE CREATE THREAD POOL TO FIX OUR OF MEMORY ISSUE
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean stop = false;

                if(terrain instanceof CornerRoad) {
                    System.out.println("Corner road");
                    car.turn("Left"); //Pass in direction path
                    //car.turn("Right");
                }else{ //Square junction
                    System.out.println("Square junction");

                    //Scenario 1 - Turn left
                    //Turn
                    if(car.rotate == 0) {
                        car.turn("Left");
                    }

                    //Check the car has turned and move into the next road
                    if(car.rotate != 0) {
                        car.move(directionPath);
                    }

                    //Scenario 2 - Turn right


                    //If the carX is not the roadLenght + carLenght - keep moving
//                    if(car.get_pos_x() != (roadLength) ){
//                        System.out.println("Move ahead abit");
//                        car.move();
//                    }else{
//                        //Turn left and add to the next road
//                        System.out.println("Turn car");
//                        car.turn("Left");
//                        stop = true;
//                    }
                }

                //SET DELAY BEFORE NEXT CAR STARTS
                map.repaint();

                //Stop timer when the car is JUST past the end of the road
                //terrain.getxStart()+terrain.getLenght()
                //if(terrain.getNextTerrainList().get(0).getYStart() == car.get_pos_x() ) {
                //if(roadLenght == car.get_pos_x()) {

                //If the car is at the end of the road
                if(stop) {
                    //reset the rotation
                    car.rotate = 0;

                    System.out.println("Timer stopped.");
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    /**
     * Work out the direction the car needs to move
     * @param c
     * @param t
     * @return
     */
    private Direction getDirection(Car c, Terrain t){
        Direction dir = null;
        System.out.println("Terrain x "+t.getxStart());
        System.out.println("Terrain y "+t.getYStart());

        if(c.get_pos_x() == t.getxStart()){
            if(t.getxStart() < c.get_pos_x()){
                dir = Direction.LEFT;
            }else {
                dir = Direction.RIGHT;
            }
        }else if(c.get_pos_y() == t.getYStart()){
            if(t.getYStart() < c.get_pos_y()){
                dir = Direction.UP;
            }else {
                dir = Direction.DOWN;
            }
        }

        return dir;
    }

    public boolean isThereATrafficLight(ArrayList<Object> objectArrayList){

        for (Object o : objectArrayList)
            if (o instanceof TrafficLights)
                return true;
        return false;

    }

    public void checkDelay(Car c){
        c.draw = false;
    }

    //policy
    //and
    //do the proper method inside the traffic light class
/*

do the following method int, because if we  have a recless driver, the driver should speedup when we have YELLOW.
So
Red = 0
Yellow = 1
Green = 2
Yellow = 3

 */
    public boolean checkIfTrafficLightIsGreen(TrafficLights trafficLight){

        if (trafficLight.getCurrentColour() == 3) //green color = 3
            return true;
//        return false;
        return true; //debug to have only true everywhere
    }

    //policy
    public boolean isThisTerrainBusy(){

        int foo = 0;

        if (this.flowDirection == 1) {
            //put code for the forward  flow / 100
            //Parse the aTerrain.getForwardListFlow() and find how many vehicles are in there
            //and return the proper boolean statement

            if (foo == 1)
                return true;
            else
                return false;

        }else{
            //put code for the backward  flow / 100
            //Parse the aTerrain.getBackwardListFlow() and find how many vehicles are in there
            //and return the proper boolean statement

            if (foo == 1)
                return true;
            else
                return false;

        }

    }


    public void run() {

//      System.out.println(Thread.currentThread().getName() + " Started");

//      while (!cancelled) {
          try {
              startFlow();
              Thread.sleep(1000);
          } catch (Exception e) {
              e.printStackTrace();
              System.out.println("here I am");
          }
//      }
//      System.out.println(Thread.currentThread().getName() + " Ended");

  }
}