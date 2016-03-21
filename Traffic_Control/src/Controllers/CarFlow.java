package Controllers;

import Objects.Draw;
import Objects.Terrain;
import Objects.Vehicle;
import Objects.TrafficLights;
import Objects.Car;
import Objects.StraightRoad;
import Objects.CornerRoad;
import Objects.SquareJunction;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

public class CarFlow implements Runnable {

    private Draw map;
    private Terrain aTerrain;
    private int flowDirection;   // 0 <---   and 1 ---->
    private volatile Timer timer = null;
    private final int delay = 30;
    public enum Direction{LEFT, RIGHT, UP, DOWN};
    private ArrayList<Object> currectFlowList;
    private TrafficManagement trafficManagement;
    private String threadName;
    private ExecutorService exec;

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

        if (flowDirection == 1) {
            currectFlowList = this.aTerrain.getForwardListFlow();
        }else {
            currectFlowList = this.aTerrain.getBackwardListFlow();
        }

        this.trafficManagement = trafficManagement;

        //Set the number of thread needed per road - CHECK THE REQUIRED SIZE
        exec = Executors.newFixedThreadPool(5);
    }
    
    /**
     * Resume the cars flow through the system
     * Repaint the map whilst moving
     */
    private void startFlow(){
        try {
            //while (this.isThisTerrainBusy()){}

            if (this.flowDirection == 1) {

                if (isThereATrafficLight(this.aTerrain.getForwardListFlow())) {
                    while (!checkIfTrafficLightIsGreen(((TrafficLights) aTerrain.getForwardListFlow().get(0)))) {
                    }
                }

                for (Object o : currectFlowList) {
                    if (o instanceof Vehicle) {
                        //System.out.println("START FLOW - Runner Vehicle");
                        //moveThisVehicleToTheNextCorrectStack((Vehicle) o);

                        //Destination - end of the road
                        final int destination = (this.aTerrain.getxStart() + this.aTerrain.getLenght()) - ((Car)o).getLength();
                        final Car car = (Car)o;
                        final Terrain t = this.aTerrain;
                        final Direction dir = getDirection((Car)o, this.aTerrain);

                        //Testing - Node 14
                        //final Terrain nextTerrain = this.aTerrain.getNeighboursTerrainList().get(1);
                        //final Terrain junctionTerrain = this.aTerrain;

                        //WORKING PART
//                        final Terrain nextTerrain = this.aTerrain.getNeighboursTerrainList().get(0).getNeighboursTerrainList().get(2);
//                        final Terrain junctionTerrain = this.aTerrain.getNeighboursTerrainList().get(0);

//                        moveToEndOfRoad(car, this.aTerrain);
//                        System.out.println("Done moving to end of road");
//                        firstExit(car, nextTerrain, junctionTerrain);

//                        if(t instanceof SquareJunction ){
//                            //car.getPerson().decide(this.aTerrain.getNeighboursTerrainList());
//                            car.getPerson().setDecision(2);
//                        }

                        System.out.println("This terrain is "+this.aTerrain.toString()+" x is "+t.getxStart()+" Y is "+t.getYStart());
                        //System.out.println("This NEXT terrain X is "+nextTerrain.getxStart()+" and Y is "+nextTerrain.getYStart());

                        exec.execute(new Runnable() {
                            public void run() {
                                //System.out.println("Asynchronous task "+Thread.currentThread().getName());
                                 //Boolean stop = moveToDestination(car, dir, destination);
                                 Boolean stop;

                                if(t instanceof SquareJunction) {
//                                    car.getPerson().setDecision(2);
//                                    final Direction dirDestination = getDirection(car, t.getNeighboursTerrainList().get(2));
                                    //stop = true;
                                    //stop = moveToDecision(car, dirDestination, dir, 0, t);
                                    System.out.println("==========CALLING SQUARE INSTANCE");
                                    Terrain nextTerrain = t.getNeighboursTerrainList().get(2);
                                    //System.out.println("FIRST EXIT | JUNCTION: next terrain is X is "+nextTerrain.getxStart()+" and Y is "+nextTerrain.getYStart());
                                    stop = firstExitTimer(car, nextTerrain, t);
                                }else{
                                    System.out.println("==========CALLING STRAIGHT INSTANCE");
                                    //Move to the end of the road
                                    stop = moveToDestinationTimer(car, dir, destination);
                                }

                                if(stop){
                                    //stop thread
                                    //add to the next stack
                                    moveThisVehicleToTheNextCorrectStack(car);
                                    System.out.println("Into next stack");
                                }
                            }
                        });

                        //moveObject((Car)o, this.aTerrain);

                        //int len = this.aTerrain.getLenght();
                        //final int roadSteps = this.aTerrain.getLenght() / c.getLength();
//                        Terrain terrain = null;
//
//                        if(this.aTerrain instanceof CornerRoad){
//                            System.out.println("Corner");
//                            terrain = (CornerRoad)this.aTerrain;
//
//                            moveObject((Car)o, terrain);
//                        }else if(this.aTerrain instanceof StraightRoad){
//                            objectStraightRoad((Car)o, (StraightRoad)this.aTerrain);
//                        }else if(this.aTerrain instanceof SquareJunction){
//                            System.out.println("SquareJunction");
//                            terrain = (SquareJunction)this.aTerrain;
//
//                            //MAYBE CREATE THREAD POOL TO FIX OUR OF MEMORY ISSUE
//                            moveObject((Car)o, terrain);
//                        }

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

    public synchronized void moveThisVehicleToTheNextCorrectStack(Vehicle v){
        System.out.println("METHOD CALL -- moveThisVehicleToTheNextCorrectStack() running....");
        threadName  = Thread.currentThread().getName();

        if (
                (trafficManagement.getTerrainList().indexOf(this.aTerrain) != 1 && this.flowDirection == 1)
                ||
                (trafficManagement.getTerrainList().indexOf(this.aTerrain) != 0 && this.flowDirection == 0)

                )
        { //exit node on flow -> and  <-

            //if(this.aTerrain instanceof SquareJunction ) {
                v.getPerson().decide(this.aTerrain.getNeighboursTerrainList());
            //}

            int decision = v.getPerson().getDecision();

            //TESTING -- REMOVE LATER
            if(this.aTerrain instanceof SquareJunction ) {
                decision =  2;
            }

            if (this.aTerrain instanceof SquareJunction) {
                //move car to end of this.aTerrain and into decision
                if (decision % 2 == 0) {
                    System.out.println("Decision is "+decision);
                    //Move straight or left

                    moveIntoDecisionSquare((Car)v, this.aTerrain, this.aTerrain.getNeighboursTerrainList().get(decision));
                    this.aTerrain.getNeighboursTerrainList().get(decision).getForwardListFlow().add(v);
                } else {
                    //move straight then turn right - i.e. moving across the junction and taking the side route

                    moveIntoDecisionSquare((Car)v, this.aTerrain, this.aTerrain.getNeighboursTerrainList().get(decision));
                    this.aTerrain.getNeighboursTerrainList().get(decision).getBackwardListFlow().add(v);
                }
            }else{
                if (this.flowDirection == 1) {
                    //Move straight in direction of nextTerrain
                    moveIntoDecision((Car)v, this.aTerrain, this.aTerrain.getNeighboursTerrainList().get(decision));
                    this.aTerrain.getNeighboursTerrainList().get(decision).getForwardListFlow().add(v);
                }else {
                    //Move straight in direction of nextTerrain
                    moveIntoDecision((Car)v, this.aTerrain, this.aTerrain.getNeighboursTerrainList().get(decision));
                    this.aTerrain.getNeighboursTerrainList().get(decision).getBackwardListFlow().add(v);
                }
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
                System.out.println("Terrain is "+trafficManagement.getTerrainList().indexOf(terrain) + " car is "+car.toString() + " Thread is "+threadName);

                car.move();
                if(car.get_pos_x() >= terrain.getLenght()+terrain.getxStart()) {
                    stop = true;
                }
//                if(terrain instanceof CornerRoad) {
//                    System.out.println("Corner road");
//                    //car.turn("Left"); //Pass in direction path
//                    //car.turn("Right");
//                }else{ //Square junction
//                    System.out.println("Square junction");
//
//                    //Scenario 1 - Turn left
//                    //Turn
//                    if(car.rotate == 0) {
//                      //  car.turn("Left");
//                    }
//
//                    //Check the car has turned and move into the next road
//                    if(car.rotate != 0) {
//                        car.move(directionPath);
//                    }
//
//                    //Scenario 2 - Turn right
//
//
//                    //If the carX is not the roadLenght + carLenght - keep moving
////                    if(car.get_pos_x() != (roadLength) ){
////                        System.out.println("Move ahead abit");
////                        car.move();
////                    }else{
////                        //Turn left and add to the next road
////                        System.out.println("Turn car");
////                        car.turn("Left");
////                        stop = true;
////                    }
//                }

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
                    moveThisVehicleToTheNextCorrectStack(car);
                }
            }
        });
        timer.start();
    }

    private void moveIntoDecisionSquare(final Car car, final Terrain curTerrain, final Terrain nextTerrain){
        //move the car into the decision road
        final int roadLength = nextTerrain.getLenght();
        final Direction directionPath = getDirection(car, nextTerrain);

        System.out.println("Moving into direction");
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean stop = false;
                //MOVING LEFT - EXAMPLE

                //Move the car till inside of junction - then turn
//                if(car.get_pos_x() <= (nextTerrain.getxStart() + car.getLength()) ){
//                    car.move(directionPath);
//                    stop = true;
//                }
//
                //Move a little into the square junction
//                if(car.get_pos_x() <= (curTerrain.getxStart() + car.getLength()) ){
//                    System.out.println("Moving in");
//                    car.move();
//                    stop = true;
//                }

//                if(car.get_pos_x() == (car.getPerson().getMyPreviousTerrainPosition().getLenght() + car.getLength()) ){
//                    System.out.println("Turning up");
//                    car.turn(Direction.UP); //dir
//                }

                //Turn to direction direction
//                if(car.get_pos_x() != (curTerrain.getxStart() + curTerrain.getLenght()) ) {
//                    car.turn(Direction.UP);
//                    stop = true;
//                }

//                //Road should say if Y++ of X
//                if(roadLength == car.get_pos_x()) { stop = true; }


                //SET DELAY BEFORE NEXT CAR STARTS
                map.repaint();

                if(stop) {
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    private synchronized void moveIntoDecision(final Car car, final Terrain curTerrain, final Terrain nextTerrain){
        //move the car into the decision road
        final int roadLength = nextTerrain.getLenght();
        final Direction directionPath = getDirection(car, nextTerrain);
        System.out.println("Thread is "+threadName + "car is "+car.toString());
//        System.out.println("Moving into direction  ");
//        timer.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Boolean stop = false;
//                //MOVING LEFT - EXAMPLE
//                //Move till the end of my road + Car.length
//                //System.out.println("Thread is "+Thread.currentThread().getName());
//                //car.move(directionPath);
//                System.out.println("Car X "+car.get_pos_x() +" Thread is "+threadName);
//                //System.out.println("Terrain X "+curTerrain.getxStart() + " - name "+curTerrain.toString());
//
////                if(car.get_pos_x() == (curTerrain.getxStart() - car.getLength())){
////                    System.out.println("Stop");
////                    stop = true;
////                }
//
//                //Turn to direction direction
////                if(car.get_pos_x() != (curTerrain.getxStart() + curTerrain.getLenght()) ) {
////                    car.turn(Direction.UP);
////                    stop = true;
////                }
//
////                //Road should say if Y++ of X
////                if(roadLength == car.get_pos_x()) { stop = true; }
//
//
//                //SET DELAY BEFORE NEXT CAR STARTS
//                map.repaint();
//
//                if(stop) {
//                    System.out.println("Stoping - straight");
//                    timer.stop();
//                }
//            }
//        });
//        timer.start();
    }

    /**
     * Work out the direction the car needs to move
     * @param c
     * @param t
     * @return
     */
    private Direction getDirection(Car c, Terrain t){
        Direction dir = null;
        int xEnd = t.getxStart() + t.getLenght();
        int yEnd = t.getYStart() + t.getLenght();

        //System.out.println("Car x "+c.get_pos_x() + " terrain "+t.getxStart());
        //System.out.println("Car y "+c.get_pos_y() + " terrain "+t.getYStart());
        //System.out.println("GETTING DIRECTIONS: Terrain X is "+t.getxStart() + " and Car X is "+c.get_pos_x());
        //System.out.println("ROTATION IS  "+t.getRotation());
        //Rotation 0 is on the X-axis and 1 is on the Y-axis
        if(t.getRotation() == 0){
            //System.out.println("CHECKING 0");
            if(xEnd < c.get_pos_x()){
                dir = Direction.LEFT;
            }else if(xEnd > c.get_pos_x()){
                dir = Direction.RIGHT;
            }
        }else if(t.getRotation()!= 0){
            //System.out.println("CHECKING 1");
            if(yEnd < c.get_pos_y()){
                dir = Direction.UP;
            }else if(yEnd > c.get_pos_y()){
                dir = Direction.DOWN;
            }
        }

        //System.out.println("getDirection: "+t.toString()+" and direction is "+dir);

        return dir;
    }

    public Boolean firstExit(Car car, Terrain nextTerrain, Terrain junction) {
        System.out.println("Running - FIRSTEXIT");

        Direction dirJunction = getDirection(car, junction);
        Direction dir = getDirection(car, nextTerrain);

//        System.out.println("THIS TERRAIN IS "+nextTerrain.toString()+" Xstart is "+nextTerrain.getxStart()+ " Y is "+nextTerrain.getYStart());
//        System.out.println("THIS TERRAIN JUNCTION IS "+junction.toString()+" Xstart is "+junction.getxStart()+ " Y is "+junction.getYStart());
        //System.out.println("Next terrain direction is - "+dir + "  AND junction DIRECTION is "+dirJunction);


        //Move the car into the junction

        //If direction is UP or DOWN check X
        if(dirJunction == Direction.UP){
            int yInnerJunc2 = (junction.getxStart() + junction.getLenght()) - 10;
            if(car.get_pos_y() < yInnerJunc2 ) {//Check if the car is in the junction
                car.move(dirJunction);
                map.repaint();
                return false;
            }
        }else if(dirJunction == Direction.DOWN){
            int yInnerJunc = junction.getYStart() + 10;

            if(car.get_pos_y() < yInnerJunc ) {//Check if the car is in the junction
                car.move(dirJunction);
                map.repaint();
                return false;
            }
        }else if(dirJunction == Direction.LEFT){
            int xInnerJunc2 = (junction.getxStart() + junction.getLenght()) - 10;

            if(car.get_pos_x() < xInnerJunc2 ) {//Check if the car is in the junction
                car.move(dirJunction);
                map.repaint();
                return false;
            }
        }else if(dirJunction == Direction.RIGHT){
            int xInnerJunc = junction.getxStart() + 10;

            if(car.get_pos_x() < xInnerJunc) {//Check if the car is in the junction
                System.out.println("JUNCTION IS RIGHT");
                car.move(dirJunction);
                map.repaint();
                return false;
            }
        }

        //If the car is in the junction
        //Turn towards road
        //CHECK IF CAR IS TURNED

        if(car.rotateInt != nextTerrain.getRotation()) {
            car.turn(dir);
            car.setRotate(nextTerrain.getRotation());
            map.repaint();
            System.out.println("FirstExit: car turned ---");
        }

        if(dir == Direction.UP){
            int yOutJunc = (nextTerrain.getYStart() + nextTerrain.getLenght()) - car.getLength();

            if(car.get_pos_y() > yOutJunc) {//Check if the car is in the junction
                car.move(dir);
                map.repaint();
                return false;
            }
        }else if(dir == Direction.DOWN){
            int yOutJunc = nextTerrain.getYStart() + car.getLength();

            if(car.get_pos_y() < yOutJunc) {//Check if the car is in the junction
                car.move(dirJunction);
                map.repaint();
                return false;
            }
        }else if(dir == Direction.LEFT){
            int xOutJunc = (nextTerrain.getxStart() + nextTerrain.getLenght()) - car.getLength();

            if(car.get_pos_x() > xOutJunc) {//Check if the car is in the junction
                car.move(dirJunction);
                map.repaint();
                return false;
            }
        }else if(dir == Direction.RIGHT){
            int xOutJunc = nextTerrain.getxStart() + car.getLength();

            if(car.get_pos_x() < xOutJunc) {//Check if the car is in the junction
                car.move(dirJunction);
                map.repaint();
                return false;
            }
        }

        System.out.println("------------------------------RETURNING TRUE =======================");
        return true;
    }

    public synchronized Boolean firstExitTimer(final Car car, final Terrain nextTerrain, final Terrain junction) {
        System.out.println("Running - firstExitTimer");

        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean stop;

                stop = firstExit(car, nextTerrain, junction);

                if(stop) {
                    System.out.println("Stopping - straight road");
                    timer.stop();
                }
            }
        });
        timer.start();

        return false;
    }

    public boolean isThereATrafficLight(ArrayList<Object> objectArrayList){

        for (Object o : objectArrayList)
            if (o instanceof TrafficLights)
                return true;
        return false;

    }

    //Give it the car to move, the direction to move, and how far to move
    public synchronized Boolean moveToDestination(Car car, Direction dir, int destination){
        if(car.get_pos_x() <= destination){
            //System.out.println("moving car "+dir + " car X is "+car.get_pos_x());
            //car.move();
            car.move(dir);
            map.repaint();
            return false;
        }

        return true;
    }

    public synchronized Boolean moveToDestinationTimer(final Car car, final Direction dir, final int destination){
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean stop;

                stop = moveToDestination(car, dir, destination);

                if(stop) {
                    System.out.println("Stopping - straight");
                    timer.stop();
                }
            }
        });
        timer.start();

        return true;
    }

    public Boolean moveToDestination(Car car, Direction dir, Terrain t){
        int iLen = ((t.getxStart() + t.getLenght()));

        if(car.get_pos_x() <= iLen){
            //System.out.println("moving car "+dir+" car X is "+car.get_pos_x()+" and I is "+iLen);
            //System.out.println("");
            //car.move();
            car.move(dir);
            map.repaint();
            return false;
        }

        return true;
    }

    public Boolean moveToEndOfRoad(Car car, Terrain t){
        Direction dir = getDirection(car, t);
        int endOfRoad = (t.getxStart() + t.getLenght()) - car.getLength();

        while(car.get_pos_x() != endOfRoad){
            car.move(dir);
        }

        return true;
    }

    public Boolean moveToDecision(Car car, Direction dirDestination, Direction dirJunction, int destination, Terrain t){

        //Move into the juntion
        for(int i=0; i<car.getLength(); i++ ){
            car.move(dirJunction);
        }

        car.turn(dirDestination);

        for(int i=0; i<car.getLength(); i++ ) {
            car.move(dirDestination);
        }

        return true;
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