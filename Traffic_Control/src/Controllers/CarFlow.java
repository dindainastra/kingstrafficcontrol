
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
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
        exec = Executors.newFixedThreadPool(10);
    }

    public synchronized void moveToEnd(Car car, int terrainLenght, Direction dir){
        for(int i=0; i<terrainLenght; i++){
            car.move(dir);
            map.repaint();
            try {
                Thread.sleep(trafficManagement.getTimeGranularity());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void moveToEnd(Car car, int terrainLenght, Direction dir,int angle){

        for(int i=0; i< terrainLenght; i++){
            car.move(dir);
            map.repaint();
            try {
                Thread.sleep(trafficManagement.getTimeGranularity());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turnCorner(Car car, Terrain currentTerrain, Point startPoint, Point endPoint){
        Direction direction = getDirection(car, currentTerrain);
        double interval = 0.01;

        //take points to end of corner road
        for (int i=1; i<currentTerrain.getLenght(); i++) {
            System.out.println("Turning..");
            System.out.println("CarX start: "+car.get_pos_x());
            System.out.println("X start: "+aTerrain.getxStart());

            car.turnCorner(startPoint, endPoint, interval);
            interval += 0.01;
            map.repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //turn towards the next destination
        car.turn(direction);
        map.repaint();

        //move to next stack
        //moveToEndOfRoad(car, )
    }
    
    /**
     * Resume the cars flow through the system
     * Repaint the map whilst moving
     */

    public void startFlow(){
        try {
            //while (this.isThisTerrainBusy()){}

            if (this.flowDirection == 1){
            	
            	if (isThereATrafficLight(this.aTerrain.getForwardListFlow()))
            		while (!checkIfTrafficLightIsGreen(((TrafficLights) aTerrain.getForwardListFlow().get(0)))) {}

                //trafic check

                for (Object o : this.currectFlowList){
                    if (o instanceof Vehicle){

                        Car tmpCar = (Car) o;

                        if(this.aTerrain instanceof  StraightRoad){
                            //System.out.println("Is this terrain : " + aTerrain.getxStart()  + ", " + aTerrain.getYStart()+ "busy or not: " + isThisTerrainBusy());
                           // System.out.println("I am StraightRoad:  ");
                            final Direction dir = getDirection(tmpCar,this.aTerrain); //If delayed the direction changes
                            tmpCar.setCurrentDirection(dir);
//                            System.out.println("================== dir is "+dir);

                            moveToEnd(tmpCar, this.aTerrain.getLenght() - (tmpCar.getLength()/2), dir);
                            moveThisVehicleToTheNextCorrectStack(tmpCar);
//                            System.out.println("Lenght: "+ this.aTerrain.getLenght() + " this road: " + this.aTerrain);
                        }else if (this.aTerrain instanceof CornerRoad){
//                            CornerRoad tmpCR = (CornerRoad) this.aTerrain;
//                            Terrain nextTerrain = aTerrain.getNeighboursTerrainList().get(0);
//                            System.out.println("CarX start: "+tmpCar.get_pos_x());
//                            System.out.println("X start: "+aTerrain.getxStart());
//
//                            int terrainXEnd = aTerrain.getxStart()+aTerrain.getLenght();
//
//                            Point startPoint = new Point(tmpCar.get_pos_x(), tmpCar.get_pos_y());
////                                Point endPoint = new Point(this.aTerrain.getxStart(), this.aTerrain.getYStart());
//                            Point endPoint = new Point(terrainXEnd, aTerrain.getYStart()-150);
//
//                            System.out.println("Car X "+tmpCar.get_pos_x()+" car Y "+ tmpCar.get_pos_y());
//                            System.out.println("Terrain X end "+terrainXEnd+" terrains Y "+ aTerrain.getYStart());
//
//                            turnCorner(tmpCar, this.aTerrain, startPoint, endPoint);

//                            if (tmpCR.getRotation()==90){ //Top Left
//                                Point startPoint = new Point(aTerrain.getxStart(), (aTerrain.getYStart()+aTerrain.getLenght()));
//                                Point endPoint = new Point(aTerrain.getxStart()+aTerrain.getLenght(), aTerrain.getYStart());
//
//                                turnCorner(tmpCar, tmpCR, startPoint, endPoint);
//                            }else if (tmpCR.getRotation()==180){ //Bottom Left
//
//
//                            }else if (tmpCR.getRotation()==270){ //Bottom Right
//
//                            }else{ //Top Right
//
//                            }
//
//                            moveThisVehicleToTheNextCorrectStack(tmpCar);

                        }else {  //SquareJunction
//                            System.out.println("I am SquareJunction:  ");
//
                            tmpCar.getPerson().decide(this.aTerrain.getNeighboursTerrainList());
                            int myDecision = tmpCar.getPerson().getDecision();
//
                            Terrain nextTerrain = aTerrain.getNeighboursTerrainList().get(myDecision);

//                            System.out.println("Which Node: "+  trafficManagement.getTerrainList().indexOf(nextTerrain));

                            Direction dir = getDirection(tmpCar, nextTerrain);
                            Direction dirJunction = getDirection(tmpCar, this.aTerrain);

//                            System.out.println("dir: " + dir + " dirJun: "+ dirJunction);

                            if (dir.equals(dirJunction)){
                                System.out.println("Go Straight!");
                                moveToEnd(tmpCar, this.aTerrain.getLenght(), dir); //-tmpCar.getLength()
                            } else if(isFirstExit(dirJunction, dir)){ //Check this method
                                System.out.println("First Exit!");
                                firstExit(tmpCar, nextTerrain, this.aTerrain, dir, dirJunction);
                            }else{
                                System.out.println("Second Exit!");
                                secondExit(tmpCar, nextTerrain, this.aTerrain, dir, dirJunction);
                            }
                            moveThisVehicleToTheNextCorrectStack(tmpCar);
                        }
                    }else if (o instanceof TrafficLights){
                        startTraffic(o);
                    }
                }
            }else {  //flow == 0

            	if (isThereATrafficLight(this.aTerrain.getBackwardListFlow()))
            		while (!checkIfTrafficLightIsGreen(((TrafficLights) aTerrain.getBackwardListFlow().get(0)))) {}

                for (Object o : this.currectFlowList){
                    if (o instanceof Vehicle){
                        Car tmpCar = (Car) o;

                        if (this.aTerrain instanceof  StraightRoad){
                            Direction dir = getDirection(tmpCar, this.aTerrain);
                            tmpCar.setCurrentDirection(dir);
                            System.out.println("I am StraightRoad:  flow is " + flowDirection);
                            moveToEnd(tmpCar, this.aTerrain.getLenght() - (tmpCar.getLength()/2), dir);
                            moveThisVehicleToTheNextCorrectStack(tmpCar);

                            System.out.println("Lenght: "+ this.aTerrain.getLenght() + " this road: " + this.aTerrain);
                        }else if (this.aTerrain instanceof CornerRoad){
//
//                            CornerRoad tmpCR = (CornerRoad) this.aTerrain;
//
//
//                            if (tmpCR.getRotation()==90){
//
//
//                            }else if (tmpCR.getRotation()==180){
//
//
//                            }else if (tmpCR.getRotation()==270){
//
//                            }else{
//
//                            }
                        }else {//SquareJunction

                            System.out.println("I am SquareJunction:  ");

                            tmpCar.getPerson().decide(this.aTerrain.getNeighboursTerrainList());
                            int myDecision = tmpCar.getPerson().getDecision();

                            Terrain nextTerrain = aTerrain.getNeighboursTerrainList().get(myDecision);

                            System.out.println("Which Node: "+  trafficManagement.getTerrainList().indexOf(nextTerrain));

                            Direction dir = getDirection(tmpCar, nextTerrain);
                            Direction dirJunction = tmpCar.getCurrentDirection();

                            System.out.println("dir: " + dir + " dirJun: "+ dirJunction);

                            if(dir.equals(dirJunction)){
                                System.out.println("Go Straight!");
                                moveToEnd(tmpCar, this.aTerrain.getLenght(), dir);
                            }else if(isFirstExit(dirJunction, dir)){
                                System.out.println("First Exit!");
                                firstExit(tmpCar, nextTerrain, this.aTerrain, dir, dirJunction);
                            }else{
                                System.out.println("Second Exit!");
                                secondExit(tmpCar, nextTerrain, this.aTerrain, dir, dirJunction);
                            }
                            moveThisVehicleToTheNextCorrectStack(tmpCar);
                        }
                    }else if (o instanceof TrafficLights){
//                        System.out.println("Im a traffic Light dude! " + Thread.currentThread().getName());
//                        TrafficLights tLOne = (TrafficLights)o;
//                        //tLOne.run();
//                        Thread t = new Thread(tLOne);
//                        t.start();
                        startTraffic(o);
                    }
                }
            }
        } catch(Exception e) {
            System.out.println("Error: "+e.getLocalizedMessage());
        }
    }

    private void startTraffic(Object o) {
//        System.out.println("Im a traffic Light dude! " + Thread.currentThread().getName());
        TrafficLights tLOne = (TrafficLights)o;
        //tLOne.run();
        map.repaint();
        //if the terrain busy then set the delay for this traffic light's greenlight longer
//        if(isThisTerrainBusy()){
//            tLOne.setGreenLightDelay(5);
//        }
        Thread t = new Thread(tLOne);
        t.start();
    }

    /**
     * Checking if the current car object is in the current terrain object
     * @param car
     * @param t
     */
    public Boolean inCurrentTerrain(Car car, Terrain t){
        //Rotation 0 is on the X-axis and 1 is on the Y-axis

        for(Object o: t.getForwardListFlow()){
            if(o instanceof Car){
                if(car.getName() == ((Car) o).getName()){
                    return true;
                }
            }
        }

        return false;
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

            if(!(this.aTerrain instanceof SquareJunction)) {
                v.getPerson().decide(this.aTerrain.getNeighboursTerrainList());
            }

            int decision = v.getPerson().getDecision();

            if (this.aTerrain instanceof SquareJunction) {
                //move car to end of this.aTerrain and into decision
                if (decision % 2 == 0) {
                    System.out.println("Decision is "+decision);
                    //Move straight or left

                    //moveIntoDecisionSquare((Car)v, this.aTerrain, this.aTerrain.getNeighboursTerrainList().get(decision));
                    this.aTerrain.getNeighboursTerrainList().get(decision).getForwardListFlow().add(v);
                } else {
                    //move straight then turn right - i.e. moving across the junction and taking the side route

                    //moveIntoDecisionSquare((Car)v, this.aTerrain, this.aTerrain.getNeighboursTerrainList().get(decision));
                    this.aTerrain.getNeighboursTerrainList().get(decision).getBackwardListFlow().add(v);
                }
            }else{
                if (this.flowDirection == 1) {
                    //Move straight in direction of nextTerrain
                    //moveIntoDecision((Car)v, this.aTerrain, this.aTerrain.getNeighboursTerrainList().get(decision));
                    this.aTerrain.getNeighboursTerrainList().get(decision).getForwardListFlow().add(v);
                }else {
                    //Move straight in direction of nextTerrain
                    //moveIntoDecision((Car)v, this.aTerrain, this.aTerrain.getNeighboursTerrainList().get(decision));
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

    public Boolean isFirstExit(Direction curD, Direction nextD){
        if(curD == Direction.RIGHT && nextD == Direction.UP){
            return true;
        }else if(curD == Direction.DOWN && nextD == Direction.RIGHT){
            return true;
        }else if(curD == Direction.LEFT && nextD == Direction.DOWN){
            return true;
        }if(curD == Direction.UP && nextD == Direction.LEFT){
            return true;
        }

        return false;
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

    private synchronized void moveIntoDecision(final Car car, final Terrain curTerrain, final Terrain nextTerrain){
        //move the car into the decision road
        final int roadLength = nextTerrain.getLenght();
        final Direction directionPath = getDirection(car, nextTerrain);
        //System.out.println("Thread is "+threadName + "car is "+car.toString());
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
    public synchronized Direction getDirection(Car c, Terrain t){
        Direction dir = null;

        //System.out.println("Car x "+c.get_pos_x() + " terrain "+t.getxStart());
        //System.out.println("Car y "+c.get_pos_y() + " terrain "+t.getYStart());
        //System.out.println("GETTING DIRECTIONS: Terrain X is "+t.getxStart() + " and Car X is "+c.get_pos_x());
        //System.out.println("ROTATION IS  "+t.getRotation());
        if(t instanceof SquareJunction) {
            dir = c.getCurrentDirection();
        }else{
            //Rotation 0 is on the X-axis and 1 is on the Y-axis
            if (t.getRotation() == 0) {
                int xEnd = (t.getxStart() + t.getLenght()) - c.getLength();
                int xStart = t.getxStart() + c.getLength();

                //System.out.println("CHECKING 0");
                if (xStart < c.get_pos_x()) {
                    dir = Direction.LEFT;
                } else if (xEnd > c.get_pos_x()) {
                    dir = Direction.RIGHT;
                }
            } else if (t.getRotation() != 0) {
                int yEnd = (t.getYStart() + t.getLenght()) - c.getLength();
                int yStart = t.getYStart() + c.getLength();
                //System.out.println("CHECKING 1");
                if (yStart < c.get_pos_y()) {
                    dir = Direction.UP;
                } else if (yEnd > c.get_pos_y()) {
                    dir = Direction.DOWN;
                }
            }
        }
        //System.out.println("getDirection: "+t.toString()+" and direction is "+dir);

        return dir;
    }


    public Boolean firstExit(Car car, Terrain nextTerrain, Terrain myCurrentTerrain, Direction direction, Direction directionJunction) {
        System.out.println("Running - FIRST EXIT");

        Direction dirJunction = directionJunction;
        Direction dir = direction;

        for (int i = 0; i <((myCurrentTerrain.getLenght()/2 - car.getLength() )); i++) {
            car.move(dirJunction);
            map.repaint();
            System.out.println("moving dirJunction: "+dirJunction);
            try {
                Thread.sleep(trafficManagement.getTimeGranularity());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        car.turn(dir);
        map.repaint();
//        
//        for (int i = 0; i < (myCurrentTerrain.getLenght()/2)-car.getLength(); i++) {
//
//            car.move(dir);
//            map.repaint();
//            System.out.println("moving dir: "+dir);
//            try {
//                Thread.sleep(trafficManagement.getTimeGranularity());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }

        return true;
    }

    public Boolean secondExit(Car car, Terrain nextTerrain, Terrain myCurrentTerrain, Direction direction, Direction directionJunction) {
        System.out.println("Running - SECOND EXIT");

        Direction dirJunction = directionJunction;
        Direction dir = direction;

        for (int i = 0; i<(myCurrentTerrain.getLenght() - (car.getLength()/2)); i++) {
            car.move(dirJunction);
            map.repaint();
            System.out.println("moving dirJunction: "+dirJunction);
            try {
                Thread.sleep(trafficManagement.getTimeGranularity());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        car.turn(dir);
        map.repaint();

        //moveToEnd(car, myCurrentTerrain.getLenght(), dir);

        for (int i = 0; i < (myCurrentTerrain.getLenght()-car.getLength()); i++) {
            car.move(dir);
            map.repaint();
            System.out.println("moving dir: "+dir);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return true;
    }
//
//    public void firstExitTimer(final Car car, final Terrain nextTerrain, final Terrain junction) {
//        System.out.println("Running - firstExitTimer - ");
//
//        ActionListener timerTask = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Boolean stop;
//
//                stop = firstExit(car, nextTerrain, junction);
//
//                if(stop) {
//                    System.out.println("Stopping - straight road. Thread is "+threadName);
//
//                    car.setDestination(true);
//                    timer.stop();
//
//                    //moveThisVehicleToTheNextCorrectStack(car);
//                }
//            }
//        };
//        timer = new Timer(50, timerTask);
//        timer.start();
//
//        //return true;
//    }

//    public void exitTimer(final Car car, final Terrain nextTerrain, final Terrain junction, final String exit) {
//        System.out.println("Running - Exit is - "+exit);
//
//        ActionListener timerTask = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Boolean stop;
//
//                if(exit.equals("First")) {
//                    stop = firstExit(car, nextTerrain, junction);
//                }else{
//                    stop = secondExit(car, nextTerrain, junction);
//                }
//
//                if(stop) {
//                    System.out.println("Stopping - exitTimer road. Thread is "+threadName);
//                    car.setDestination(true);
//                    moveThisVehicleToTheNextCorrectStack(car);
//                    timer.stop();
//                    try {
//                        wait();
//                    } catch (InterruptedException e1) {
//                        e1.printStackTrace();
//                    }
//
//                }
//            }
//        };
//        timer = new Timer(50, timerTask);
//        timer.start();
//
//        //return true;
//    }

    public boolean isThereATrafficLight(ArrayList<Object> objectArrayList){

        for (Object o : objectArrayList)
            if (o instanceof TrafficLights)
                return true;
        return false;

    }

    //Give it the car to move, the direction to move, and how far to move
    public synchronized Boolean moveToDestination(Car car, Direction dir, int destination){
        //System.out.println("Car X is "+car.get_pos_x()+" , Y is "+car.get_pos_y());
        //System.out.println("moveToDestination(): Destination is "+destination);
        System.out.println("moveToDestination(): isCarAtDestination "+car.getDestination()+" - Direction is "+dir);
        if(car.get_pos_x() <= destination){
            //System.out.println("moving car "+dir + " car X is "+car.get_pos_x());
            //car.move();
            car.move(dir);
            map.repaint();
            return false;
        }

        return true;
    }

    //Give it the car to move, the direction to move, and how far to move
    public synchronized Boolean moveToDestination(Car car, int destination){
        System.out.println("Car X is "+car.get_pos_x()+" , Y is "+car.get_pos_y());
        System.out.println("Destination is "+destination);
        if(car.get_pos_x() <= destination){
            //System.out.println("moving car "+dir + " car X is "+car.get_pos_x());
            //car.move();

            car.move(car.getCurrentDirection());
            map.repaint();
            return false;
        }

        return true;
    }

    public synchronized Boolean moveToDestinationTimer(final Car car, final Direction dir, final int destination, Terrain t){
        //Lock lock = new ReentrantLock();
        System.out.println("Thread entry into moveToDestinationTimer() - Thread is "+threadName);
        if(!car.getDestination() && inCurrentTerrain(car, t)) {
            ActionListener timerTask = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Boolean stop = false;

                    //if(!car.getDestination()) {
                    stop = moveToDestination(car, dir, destination);
                    //}

                    if(stop){
                        System.out.println("Stopping Straight movement - moveToDestinationTimer()");
                        car.setDestination(true);
                        moveThisVehicleToTheNextCorrectStack(car);
                        timer.stop();
                        try {
                            wait();
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        //moveThisVehicleToTheNextCorrectStack(car);
                    }
                }
            };
            timer = new Timer(50, timerTask);
            timer.start();
        }

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

    public Boolean moveToDecision(Car car, Direction dirDestination, Direction dirJunction, int destination, Terrain t)
    {

        //Move into the juntion
        for(int i=0; i<car.getLength(); i++ ){
            car.move(dirJunction);
        }

//        car.turn(dirDestination);

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
        return true; //is has to be true
    }

    //policy

    /**
     * To check whether the road is busy or not
     * @return
     */
    public boolean isThisTerrainBusy(){
        int numberOfCars = 0;
        int threshold = 5;

        //put code for the forward  flow / 100
        //Parse the aTerrain.getForwardListFlow() and find how many vehicles are in there
        //and return the proper boolean statement
        if (this.flowDirection == 1) {
            currectFlowList = this.aTerrain.getForwardListFlow();
            for (Object o : this.currectFlowList){
                //System.out.println(this.getPerson().getName().substring("Person".length()));
                //System.out.println(o.get);
                if (o instanceof Vehicle) {
                    numberOfCars++;
//                    System.out.println("Car: "+ ((Vehicle) o).getPerson().getName().substring("Person".length()));
                }
            }
//            System.out.println("Number of cars are: " + numberOfCars + " while the threshold is: " + threshold);

            if(numberOfCars>threshold){
                return true;
            } else {
                return false;
            }
        } else {
            //put code for the backward  flow / 100
            //Parse the aTerrain.getBackwardListFlow() and find how many vehicles are in there
            //and return the proper boolean statement

            currectFlowList = this.aTerrain.getBackwardListFlow();
            for (Object o : this.currectFlowList){
                if (o instanceof Vehicle) {
                    numberOfCars++;
//                    System.out.println("Car: "+ ((Vehicle) o).getPerson().getName().substring("Person".length()));
                }
            }
//            System.out.println("Number of cars are: " + numberOfCars + " while the threshold is: " + threshold);

            if(numberOfCars>threshold){
                return true;
            } else {
                return false;
            }
        }

    }


    public void run() {

//      System.out.println(Thread.currentThread().getName() + " Started");
        for (;;)
//      while (!cancelled) {
          try {
              startFlow();
              Thread.sleep(trafficManagement.getTimeGranularity());
          } catch (Exception e) {
              e.printStackTrace();
              System.out.println("here I am");
          }
//      }
//      System.out.println(Thread.currentThread().getName() + " Ended");

    }

}