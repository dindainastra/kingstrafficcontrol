
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

        if(flowDirection == 1){
            currectFlowList = this.aTerrain.getForwardListFlow();
        }else{
            currectFlowList = this.aTerrain.getBackwardListFlow();
        }

        this.trafficManagement = trafficManagement;

        //Set the number of thread needed per road - CHECK THE REQUIRED SIZE
        exec = Executors.newFixedThreadPool(10);
    }

    public void moveToEnd(Car car, int terrainLenght, Direction dir){
        for(int i=0; i<terrainLenght; i++){
            car.move(dir);
            map.repaint();
            try {
                Thread.sleep(trafficManagement.getTimeGranularity());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //------TRYING THE TIMER METHOD
//        final Car c = car;
//        final Direction d = dir;
//        final int tLen = terrainLenght;
//        Timer timer2 = new Timer(trafficManagement.getTimeGranularity(), null);
//        timer2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Boolean stop = false;
//                int i = 0;
//                System.out.println("Runnniing=+=======++");
//                //Road should say if Y++ of X
//                c.move(d);
//                if(i>=tLen) { stop = true; }
//                map.repaint();
//                i++;
//
//                if(stop) {
//                    timer.stop();
//                }
//            }
//        });
//        timer.start();
    }

    public void moveToEnd(Car car, Terrain terrain, Direction dir, int angle){

        if(dir == Direction.RIGHT || dir == Direction.LEFT) { //Check on the X axis
            int x;
            if(dir == Direction.RIGHT){
                x = terrain.getYStart() + terrain.getLenght();
            }else{
                x = terrain.getYStart();
            }

            while(car.get_pos_x() != x){ car.move(dir); }

        }else if(dir == Direction.UP || dir == Direction.DOWN) { //Check on the Y axis
            int y;
            if(dir == Direction.UP){
                y = terrain.getYStart();
            }else{
                y = terrain.getYStart()+terrain.getLenght();
            }

            while(car.get_pos_x() != y){ car.move(dir); }

        }
    }

    public void turnCorner(Car car, CornerRoad terrain, double angle, double endAngle, Direction direction, int radius, int centerX, int centerY){

//Working code
//        int centerX = (currentTerrain.getxStart())+(currentTerrain.getLenght()/2);
//        int centerY = (currentTerrain.getYStart());

        System.out.println("Turn Corner Road - X is "+car.get_pos_x()+" Y is "+car.get_pos_y());

//        int centerX = (terrain.getxStart())+(terrain.getLenght()/2);
//        int centerY = (terrain.getYStart())+10;
        double nAngle = angle;

        if(radius == 100) { //Top flow
            while (angle <= endAngle) {
//                int centerX = (terrain.getxStart())+(terrain.getLenght()/2);
//                int centerY = (terrain.getYStart())+10;
//                System.out.println("+++++++++++++++++++turning... "+angle+" endangle "+endAngle);
                System.out.println("++++++++++++++++++++++++++CenterX "+centerX+" centerY "+centerY+" tLenght "+terrain.getLenght());

                angle += 0.1;

                car.turnCorner(angle, centerX, centerY, radius);
                car.bend(direction, 5.625);
                map.repaint();

//            car.turnNew(Direction.RIGHT, angle);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }else{
//            int centerX = (terrain.getxStart())+(terrain.getLenght()/2)+10;
//            int centerY = (terrain.getYStart());

            while (angle >= endAngle) {
                System.out.println("+++++++++++++++++++turning... "+angle+" endangle "+endAngle);

                angle -= 0.1;
                nAngle += 0.1;

                car.turnCorner(nAngle, centerX, centerY, radius);
                car.bend(direction, 5.625);
                map.repaint();

//            car.turnNew(Direction.RIGHT, angle);

                try {
                    Thread.sleep(trafficManagement.getTimeGranularity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //take points to end of corner road
//        for (int i=1; i<currentTerrain.getLenght(); i++) {
//            System.out.println("Turning..");
//            System.out.println("CarX start: "+car.get_pos_x());
//            System.out.println("X start: "+aTerrain.getxStart());
//
//            angle += 0.1;
//            if (angle > (Math.PI/2)) {
//                angle = 0.0;
//            }
//
////            car.turnCorner(startPoint, endPoint, interval);
//            car.turnCorner(angle, endPoint, interval);
//            interval += 0.01;
//            map.repaint();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }


        //turn towards the next destination
//        car.turn(direction);
//        map.repaint();

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

//                for(int i=0;i<this.aTerrain.getForwardListFlow().size();i++){
//                    String x = this.aTerrain.getForwardListFlow().get(i).toString();
//                    System.out.println("In node: "+ trafficManagement.getTerrainList().indexOf(this.aTerrain) +" in position "+i+" there is " +x);
//                }


//            	if (isThereATrafficLight(this.aTerrain.getForwardListFlow()))
//            		while (checkIfTrafficLightIsGreen(((TrafficLights) aTerrain.getForwardListFlow().get(0)))) {}

                //trafic check

                for (Object o : this.currectFlowList){
                    if (o instanceof Vehicle){
                        Car tmpCar = (Car) o;
                        System.out.println("Node:  "+ this.trafficManagement.getTerrainList().indexOf(this.aTerrain)+ " Flow : 1" + "  Car Name:  "+ o.toString());

                        if(this.aTerrain instanceof  StraightRoad){
                            //System.out.println("Is this terrain : " + aTerrain.getxStart()  + ", " + aTerrain.getYStart()+ "busy or not: " + isThisTerrainBusy());
                           // System.out.println("I am StraightRoad:  ");

                            final Direction dir = getDirection(tmpCar, this.aTerrain); //If delayed the direction changes
                            tmpCar.setCurrentDirection(dir);
//                            System.out.println("================== dir is "+dir);

                            moveToEnd(tmpCar, this.aTerrain.getLenght(), dir);
                        	  if (isThereATrafficLight(this.aTerrain.getForwardListFlow())){
                          			
                        		  	while (!checkIfTrafficLightIsGreen(((TrafficLights) aTerrain.getForwardListFlow().get(0)))) {
                        		  		
                          		}
                        	  }

                            moveThisVehicleToTheNextCorrectStack(tmpCar);
//                            System.out.println("Lenght: "+ this.aTerrain.getLenght() + " this road: " + this.aTerrain);
                        }else if (this.aTerrain instanceof CornerRoad){
                            CornerRoad cornerRoad = (CornerRoad) this.aTerrain;
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
//                            Point startPoint = new Point(aTerrain.getxStart(), (aTerrain.getYStart()+aTerrain.getLenght()));
//                            Point endPoint = new Point(aTerrain.getxStart()+aTerrain.getLenght(), aTerrain.getYStart());

                            double angle, endAngle;
                            Direction direction;
                            int radius = 100;
                            int centerX;
                            int centerY;

                            if(cornerRoad.getStartAngle()==90){ //Top Left - WORKS
                                angle = Math.toRadians(180);
                                endAngle = Math.toRadians(270);
                                direction = Direction.RIGHT;

                                centerX = cornerRoad.getxStart()+cornerRoad.getLenght()/2;
                                centerY = cornerRoad.getYStart()+10;
                            }else if(cornerRoad.getStartAngle()==180){ //Bottom Left
                                angle = Math.toRadians(90);
                                endAngle = Math.toRadians(180);
                                direction = Direction.UP;

                                centerX = cornerRoad.getxStart()+10;
                                centerY = cornerRoad.getYStart();
                            }else if(cornerRoad.getStartAngle()==270){ //Bottom Right - WORKS
                                angle = Math.toRadians(0);
                                endAngle = Math.toRadians(90);
                                direction = Direction.LEFT;

                                centerX = cornerRoad.getxStart();
                                centerY = cornerRoad.getYStart()-15;
                            }else{ //Top Right - WORKS BUT BENDS WRONG WAY
                                angle = Math.toRadians(270);
                                endAngle = Math.toRadians(360);
                                direction = Direction.DOWN;

                                centerX = cornerRoad.getxStart();
                                centerY = cornerRoad.getYStart();
                            }

                            turnCorner(tmpCar, cornerRoad, angle, endAngle, direction, radius, centerX, centerY);
                            moveThisVehicleToTheNextCorrectStack(tmpCar);
                        }else { //SquareJunction
                            System.out.println("I am SquareJunction:  ");

                            tmpCar.getPerson().decide(this.aTerrain.getNeighboursTerrainList());
                            int myDecision = tmpCar.getPerson().getDecision();

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
                    }

                    else if (o instanceof TrafficLights){
                        map.repaint();

                        //if the terrain busy then set the delay for this traffic light's greenlight longer
                        //        if(isThisTerrainBusy()){
                        //            o.setGreenLightDelay(8);
                        //        }
                    }
                }
            }else {  //flow == 0

//                for(int i=0;i<this.aTerrain.getBackwardListFlow().size();i++){
//                    String x = this.aTerrain.getBackwardListFlow().get(i).toString();
//                    System.out.println("In node "+ trafficManagement.getTerrainList().indexOf(this.aTerrain) +" in position "+i+" there is " +x);
//                    System.out.println("Array size: " + this.aTerrain.getBackwardListFlow().size());
//                }
                
                for (Object o : this.currectFlowList){
                    if (o instanceof Vehicle){
                        Car tmpCar = (Car) o;
                        System.out.println("Node:  "+ this.trafficManagement.getTerrainList().indexOf(this.aTerrain)+ " Flow : 0" + "  Car Name:  "+ o.toString());
                        if (this.aTerrain instanceof  StraightRoad){
                            Direction dir = getDirection(tmpCar, this.aTerrain);
                            tmpCar.setCurrentDirection(dir);

                            System.out.println("I am StraightRoad:  flow is " + flowDirection);
                            if(trafficManagement.getTerrainList().indexOf(this.aTerrain) == 1) {
                                moveToEnd(tmpCar, this.aTerrain.getLenght()-tmpCar.getLength(), dir);
                            }else{
                                moveToEnd(tmpCar, this.aTerrain.getLenght(), dir);
                            }

                            if (isThereATrafficLight(this.aTerrain.getBackwardListFlow())){
                          		
                        		  	while (!checkIfTrafficLightIsGreen(((TrafficLights) aTerrain.getBackwardListFlow().get(0)))) {
                        		  		
                          		}
                        	}

                            moveThisVehicleToTheNextCorrectStack(tmpCar);

                            System.out.println("Length: "+ this.aTerrain.getLenght() + " this road: " + this.aTerrain);
                        }else if (this.aTerrain instanceof CornerRoad){
                            CornerRoad cornerRoad = (CornerRoad) this.aTerrain;
                            double angle, endAngle;
                            Direction direction;
                            int radius = 50;
                            int centerX;
                            int centerY;

                            if(cornerRoad.getStartAngle()==90){ //Top Left
                                angle = Math.toRadians(180);
                                endAngle = Math.toRadians(90);
                                direction = Direction.LEFT;

                                centerX = (cornerRoad.getxStart()+cornerRoad.getLenght()/2);
                                centerY = cornerRoad.getYStart()+10;
                            }else if(cornerRoad.getStartAngle()==180){ //Bottom Left - WORKS
                                angle = Math.toRadians(270);
                                endAngle = Math.toRadians(180);
                                direction = Direction.LEFT;

                                centerX = (cornerRoad.getxStart()+cornerRoad.getLenght()/2)+15;
                                centerY = cornerRoad.getYStart()-10;
                            }else if(cornerRoad.getStartAngle()==270){ //Bottom Right
                                angle = Math.toRadians(360);
                                endAngle = Math.toRadians(270);
                                direction = Direction.LEFT;
                                centerX = cornerRoad.getxStart()+10;
                                centerY = cornerRoad.getYStart()-10;
                            }else{ //Top Right - WORKS
                                angle = Math.toRadians(90);
                                endAngle = Math.toRadians(0);
                                direction = Direction.LEFT;
                                centerX = cornerRoad.getxStart()-25;
                                centerY = cornerRoad.getYStart()+15;
                            }

                            turnCorner(tmpCar, cornerRoad, angle, endAngle, direction, radius, centerX, centerY);
                            moveThisVehicleToTheNextCorrectStack(tmpCar);
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
                    }
                    else if (o instanceof TrafficLights){

                        //if the terrain busy then set the delay for this traffic light's greenlight longer
                        //        if(isThisTerrainBusy()){
                        //            o.setGreenLightDelay(8);
                        //        }
                        map.repaint();
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

    public void moveThisVehicleToTheNextCorrectStack(Vehicle v){
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

            	if (decision % 2 == 0) {
                    System.out.println("Decision is "+decision);
                    //Move straight or left
                    this.aTerrain.getNeighboursTerrainList().get(decision).getForwardListFlow().add(v);
                } else {
                    //move straight then turn right - i.e. moving across the junction and taking the side route
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
//                System.out.println("Flow: " +this.flowDirection + " Delete "+v.toString()
//                            + " from Node: " + trafficManagement.getTerrainList().indexOf(this.aTerrain)
//                            + " and move to Node: " + this.trafficManagement.getTerrainList().indexOf(aTerrain.getNeighboursTerrainList().get(decision))
//                            + " and worker is: " + threadName);
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

        moveToEnd(car, car.getLength(), dirJunction);

        car.turn(dir, directionJunction);
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
        int lenght;
//        if(trafficManagement.){
//            lenght = myCurrentTerrain.getLenght()+car.getLength();
//        }else{
//            lenght = myCurrentTerrain.getLenght()+car.getLength();
//        }

        for (int i = 0; i<(myCurrentTerrain.getLenght()-car.getLength()+10); i++) {
            car.move(dirJunction);
            map.repaint();
//            System.out.println("moving dirJunction: "+dirJunction);
            try {
                Thread.sleep(trafficManagement.getTimeGranularity());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        moveToEnd(car, myCurrentTerrain.getLenght()+car.getLength(), dirJunction);

        double angle = 10;

        for(int i=0; i<9; i++) {
            car.turn(dir, directionJunction, Math.toRadians(angle));
            map.repaint();
            angle+=10;
            try {
                Thread.sleep(trafficManagement.getTimeGranularity()+50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


//        car.set_pos_x(car.get_pos_x()-car.getWidth());
//        car.set_pos_y(car.get_pos_y()-car.getWidth());

//        moveToEnd(car, myCurrentTerrain.getLenght(), dir);
//        car.setCurrentDirection(dir);
//
//        car.setRotate();

        for (int i = 0; i < (myCurrentTerrain.getLenght()-car.getLength()); i++) {
            car.move(dir);
            map.repaint();
//            System.out.println("moving dir: "+dir);
            try {
                Thread.sleep(trafficManagement.getTimeGranularity());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Reset the car X values - X and Y are wrong

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


 */
    public boolean checkIfTrafficLightIsGreen(TrafficLights trafficLight){

        if (trafficLight.getCurrentColour() == 3) //green color = 3
        	return true;
        return false; 
    }

    //policy

    /**
     * To check whether the road is busy or not
     * @return
     */
    public boolean isThisTerrainBusy(){
        int numberOfCars = 0;
        int threshold = 4;
        int roadLength;

        //put code for the forward  flow / 100
        //Parse the aTerrain.getForwardListFlow() and find how many vehicles are in there
        //and return the proper boolean statement
        if (this.flowDirection == 1) {
            currectFlowList = this.aTerrain.getForwardListFlow();
            if(this.aTerrain instanceof StraightRoad){
                roadLength = aTerrain.getLenght();
                threshold = (roadLength/60);
            }
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
            if(this.aTerrain instanceof StraightRoad){
                roadLength = aTerrain.getLenght();
                threshold = (roadLength/60);
            }

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
        if (this.flowDirection == 1){
            for (Object o : this.currectFlowList) {
                if(o instanceof TrafficLights){
                    System.out.println("Running traffic light");
                    startTraffic(o);
                }
            }
        } else if (this.flowDirection == 0){
            for (Object o : this.currectFlowList) {
                if(o instanceof TrafficLights){
                    System.out.println("Running traffic light");
                    startTraffic(o);
                }
            }
        }

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