package Controllers;

import Interfaces.Terrain;
import Interfaces.Vehicle;
import Nodes.CornerRoad;
import Nodes.SquareJunction;
import Nodes.StraightRoad;
import Objects.Car;
import Objects.TrafficLights;

import java.util.ArrayList;

public class VehicleFlowHelper implements Runnable {

    private Vehicle vehicle;
    private Draw map;
    private int flowDirection;

    private TrafficManagement trafficManagement;
    private Terrain aTerrain;
    private VehicleFlow vehicleflow;
    private ArrayList<Object> currentObjectList = new ArrayList<Object>();

    public VehicleFlowHelper(Vehicle vehicle, Draw aMap, int flowDirection, TrafficManagement trafficManagement, Terrain aTerrain, VehicleFlow vfl) {

        this.vehicle = vehicle;
        map = aMap;
        this.trafficManagement = trafficManagement;
        this.flowDirection = flowDirection;
        this.aTerrain = aTerrain;
        vehicleflow = vfl;

    }

    public void moveToEnd(Vehicle vehicle, int terrainLenght, Direction dir){
        for(int i=0; i<terrainLenght; i++){
            vehicle.move(dir);
            map.repaint();
            try {
                Thread.sleep(trafficManagement.getTimeGranularity());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public int calculateOffset(ArrayList<Object> flowList, Vehicle vehicle){
        int offset = 0;

        if (flowList.size() > 2) {
            offset = (flowList.size() - 2) * vehicle.getLength();
        }

        return offset;
    }

    public void moveToEndChanged(Vehicle vehicle, Terrain terrain, Direction dir){
        if(dir == Direction.RIGHT || dir == Direction.LEFT) { //Check on the X axis
            int x;
            if(dir == Direction.RIGHT){
                x = ((terrain.getxStart() + terrain.getLenght()) - vehicle.getLength());
            }else{
                x = (terrain.getxStart() + vehicle.getLength());
            }

            int offset = 0;
//            int offset = ((this.aTerrain.getBackwardListFlow().size() - 1) * 20);

            while(vehicle.get_pos_x() != x){
                vehicle.move(dir);

                if (flowDirection==1) {
                    //If the traffic light is red and we don't already have an offset - calculate it
                    if(!checkIfTrafficLightIsGreen(((TrafficLights) aTerrain.getForwardListFlow().get(0))) && offset != 0) {
                        //If you're not the only vehicle on the road, calculate my offset
//                        if (this.aTerrain.getForwardListFlow().size() > 2) {
//                            offset = (this.aTerrain.getForwardListFlow().size() - 2) * vehicle.getLength();
//                        }
                        offset = calculateOffset(this.aTerrain.getForwardListFlow(), vehicle);

                        //Remove the offset from our destination
                        if (dir == Direction.RIGHT) {
                            x = x - offset;
                        } else {
                            x = x + offset;
                        }
                    }
                }

                try {
                    Thread.sleep(trafficManagement.getTimeGranularity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } else if (dir == Direction.UP || dir == Direction.DOWN) { //Check on the Y axis
            int y;

            if(dir == Direction.UP){
                y = terrain.getYStart() + vehicle.getLength();
            }else{
                y = (terrain.getYStart()+terrain.getLenght())- vehicle.getLength();
            }

            while(vehicle.get_pos_y() != y){
                vehicle.move(dir);

                try {
                    Thread.sleep(trafficManagement.getTimeGranularity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void turnCorner(Vehicle vehicle, CornerRoad terrain, double angle, double endAngle, Direction direction, int radius, int centerX, int centerY){

//        System.out.println("Turn Corner Road - X is "+vehicle.get_pos_x()+" Y is "+vehicle.get_pos_y());

        double nAngle = angle;

        if (radius == 100) { //Top flow

            while (angle <= endAngle) {
                angle += 0.1;
                vehicle.turnCorner(angle, centerX, centerY, radius);
                vehicle.bend(direction, 5.625);
                map.repaint();

//            car.turnNew(Direction.RIGHT, angle);

                try {
                    Thread.sleep(trafficManagement.getTimeGranularity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }else{

            while (angle >= endAngle) {
                angle -= 0.1;
                nAngle += 0.1;
                vehicle.turnCorner(nAngle, centerX, centerY, radius);
                vehicle.bend(direction, 5.625);
                map.repaint();

                try {
                    Thread.sleep(trafficManagement.getTimeGranularity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Checking if the current car object is in the current terrain object
     *
     * @param car
     * @param t
     */
    public Boolean inCurrentTerrain(Car car, Terrain t) {
        //Rotation 0 is on the X-axis and 1 is on the Y-axis

        for (Object o : t.getForwardListFlow()) {
            if (o instanceof Car) {
                if (car.getName() == ((Car) o).getName()) {
                    return true;
                }
            }
        }

        return false;
    }

    public void moveThisVehicleToTheNextCorrectStack(Vehicle v) {
//        System.out.println("METHOD CALL -- moveThisVehicleToTheNextCorrectStack() running....");
//        threadName  = Thread.currentThread().getName();

        if (
                (trafficManagement.getTerrainList().indexOf(this.aTerrain) != 1 && this.flowDirection == 1)
                        ||
                        (trafficManagement.getTerrainList().indexOf(this.aTerrain) != 0 && this.flowDirection == 0)

                ) { //exit node on flow -> and  <-

            if (!(this.aTerrain instanceof SquareJunction)) {
                v.getPerson().decide(this.aTerrain.getNeighboursTerrainList());
            }

            int decision = v.getPerson().getDecision();

            if (this.aTerrain instanceof SquareJunction) {

                if (decision % 2 == 0) {
//                    System.out.println("Decision is " + decision);
                    //Move straight or left
                    this.aTerrain.getNeighboursTerrainList().get(decision).getForwardListFlow().add(v);
                } else {
                    //move straight then turn right - i.e. moving across the junction and taking the side route
                    this.aTerrain.getNeighboursTerrainList().get(decision).getBackwardListFlow().add(v);
                }
            } else {
                if (this.flowDirection == 1) {
                    //Move straight in direction of nextTerrain
                    //moveIntoDecision((Car)v, this.aTerrain, this.aTerrain.getNeighboursTerrainList().get(decision));
                    this.aTerrain.getNeighboursTerrainList().get(decision).getForwardListFlow().add(v);
                } else {
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
            } catch (Exception e) {
//                System.out.println("Thread: "+ threadName + " cant removed!");
            }

        } else
//            System.out.println("EXIT NODE: Delete " + v.toString() + " and worker is: " + Thread.currentThread().getName());
        vehicleflow.deleteFromCurrentList(vehicle);
        // TODO: 29/03/2016  to a deletevehicle (Vehicle v)
    }

    public Boolean isFirstExit(Direction curD, Direction nextD) {
        if (curD == Direction.RIGHT && nextD == Direction.UP) {
            return true;
        } else if (curD == Direction.DOWN && nextD == Direction.RIGHT) {
            return true;
        } else if (curD == Direction.LEFT && nextD == Direction.DOWN) {
            return true;
        }
        return curD == Direction.UP && nextD == Direction.LEFT;

    }

    /**
     * Work out the direction the car needs to move
     *
     * @param c
     * @param t
     * @return
     */
    public synchronized Direction getDirection(Car c, Terrain t) {
        Direction dir = null;

        if (t instanceof SquareJunction) {
            dir = c.getCurrentDirection();
        } else {
            //Rotation 0 is on the X-axis and 1 is on the Y-axis
            if (t.getRotation() == 0) {
                int xEnd = (t.getxStart() + t.getLenght()) - c.getLength();
                int xStart = t.getxStart() + c.getLength();

                if (xStart < c.get_pos_x()) {
                    dir = Direction.LEFT;
                } else if (xEnd > c.get_pos_x()) {
                    dir = Direction.RIGHT;
                }
            } else if (t.getRotation() != 0) {
                int yEnd = (t.getYStart() + t.getLenght()) - c.getLength();
                int yStart = t.getYStart() + c.getLength();
                if (yStart < c.get_pos_y()) {
                    dir = Direction.UP;
                } else if (yEnd > c.get_pos_y()) {
                    dir = Direction.DOWN;
                }
            }
        }

        return dir;
    }

    public Boolean firstExit(Car car, Terrain nextTerrain, Terrain myCurrentTerrain, Direction direction, Direction directionJunction) {
//        System.out.println("Running - FIRST EXIT");

        Direction dirJunction = directionJunction;
        Direction dir = direction;

        moveToEnd(car, car.getLength(), dirJunction);

        car.turn(dir, directionJunction);
        map.repaint();

        return true;
    }

    public Boolean secondExit(Car car, Terrain nextTerrain, Terrain myCurrentTerrain, Direction direction, Direction directionJunction) {
//        System.out.println("Running - SECOND EXIT");

        Direction dirJunction = directionJunction;
        Direction dir = direction;
        int lenght;
//        if(trafficManagement.){
//            lenght = myCurrentTerrain.getLenght()+car.getLength();
//        }else{
//            lenght = myCurrentTerrain.getLenght()+car.getLength();
//        }

        for (int i = 0; i < (myCurrentTerrain.getLenght() - car.getLength() + 10); i++) {
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

        for (int i = 0; i < 9; i++) {
            car.turn(dir, directionJunction, Math.toRadians(angle));
            map.repaint();
            angle += 10;
            try {
                Thread.sleep(trafficManagement.getTimeGranularity());
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

        for (int i = 0; i < (myCurrentTerrain.getLenght() - car.getLength()); i++) {
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

    // TODO: 30/03/2016  Patrick DELETE IT! :D
    public Boolean secondExitChanged(Car car, Terrain nextTerrain, SquareJunction myCurrentTerrain, Direction direction, Direction directionJunction) {
//        System.out.println("Running - SECOND EXIT");

        Direction dirJunction = directionJunction;
        Direction dir = direction;
        int lenght;

        if (directionJunction == Direction.RIGHT || directionJunction == Direction.LEFT) { //Check on the X axis
            int x;

            if (directionJunction == Direction.RIGHT) {
                x = (myCurrentTerrain.getxStart() + (myCurrentTerrain.getLenght() / 2)) + car.getLength();
            } else {
                x = (myCurrentTerrain.getxStart() + (myCurrentTerrain.getLenght() / 2)) - car.getLength();
            }

            while (car.get_pos_x() != x) {
                car.move(directionJunction);
//                System.out.println("Moving to the middle - Car x is " + car.get_pos_x() + " jX is " + myCurrentTerrain.getxStart());
                try {
                    Thread.sleep(trafficManagement.getTimeGranularity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (directionJunction == Direction.UP || directionJunction == Direction.DOWN) { //Check on the Y axis
            int y;

            if (directionJunction == Direction.UP) {
                y = (myCurrentTerrain.getYStart() + (myCurrentTerrain.getLenght() / 2)) - car.getLength();
            } else {
                y = (myCurrentTerrain.getYStart() + (myCurrentTerrain.getLenght() / 2)) + car.getLength();
            }

            while (car.get_pos_y() != y) {
                car.move(directionJunction);

                try {
                    Thread.sleep(trafficManagement.getTimeGranularity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        //Rotate the car towards the next road
        double angle = 10;
        for (int i = 0; i < 9; i++) {
            car.turn(dir, directionJunction, Math.toRadians(angle));
            map.repaint();
            angle += 10;
            try {
                Thread.sleep(trafficManagement.getTimeGranularity());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (dir == Direction.RIGHT || dir == Direction.LEFT) { //Check on the X axis
            int x;

            if (dir == Direction.RIGHT) {
                x = (myCurrentTerrain.getxStart() + myCurrentTerrain.getLenght()) - car.getLength();
            } else {
                x = (myCurrentTerrain.getxStart()) - car.getLength();
            }

            while (car.get_pos_x() != x) {
                car.move(dir);

                try {
                    Thread.sleep(trafficManagement.getTimeGranularity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (dir == Direction.UP || dir == Direction.DOWN) { //Check on the Y axis
            int y;

            if (dir == Direction.UP) {
                y = myCurrentTerrain.getYStart() - car.getLength();
            } else {
                y = (myCurrentTerrain.getYStart() + myCurrentTerrain.getLenght()) - car.getLength();
            }

            while (car.get_pos_y() != y) {
                car.move(dir);

                try {
                    Thread.sleep(trafficManagement.getTimeGranularity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

    public boolean isThereATrafficLight(ArrayList<Object> objectArrayList) {

        for (Object o : objectArrayList)
            if (o instanceof TrafficLights)
                return true;
        return false;

    }

    //Give it the car to move, the direction to move, and how far to move
    public synchronized Boolean moveToDestination(Car car, Direction dir, int destination) {

//        System.out.println("moveToDestination(): isCarAtDestination " + car.getDestination() + " - Direction is " + dir);
        if (car.get_pos_x() <= destination) {
            car.move(dir);
            map.repaint();
            return false;
        }
        return true;

    }

    //Give it the car to move, the direction to move, and how far to move
    public synchronized Boolean moveToDestination(Car car, int destination) {

        if (car.get_pos_x() <= destination) {
            car.move(car.getCurrentDirection());
            map.repaint();
            return false;
        }

        return true;
    }

    public boolean checkIfTrafficLightIsGreen(TrafficLights trafficLight) {

        return trafficLight.getCurrentColour() == 3;
    }

    /**
     * Resume the cars flow through the system
     * Repaint the map whilst moving
     */
    public void startFlow() {
        try {
            //while (this.isThisTerrainBusy()){}

            if (this.flowDirection == 1) {

                Car tmpCar = (Car) vehicle;
//                System.out.println("Node:  " + this.trafficManagement.getTerrainList().indexOf(this.aTerrain) + " Flow : 1" + "  Car Name:  " + vehicle.toString());

                if (this.aTerrain instanceof StraightRoad) {

                    final Direction dir = getDirection(tmpCar, this.aTerrain); //If delayed the direction changes
                    tmpCar.setCurrentDirection(dir);
                    moveToEndChanged(tmpCar, this.aTerrain, dir);

                    if (isThereATrafficLight(this.aTerrain.getForwardListFlow())) {
                        while (!checkIfTrafficLightIsGreen(((TrafficLights) aTerrain.getForwardListFlow().get(0)))) {
                            System.out.println("DEBUG2");
                        }
                    }

                    moveThisVehicleToTheNextCorrectStack(tmpCar);
                } else if (this.aTerrain instanceof CornerRoad) {
                    CornerRoad cornerRoad = (CornerRoad) this.aTerrain;

                    double angle, endAngle;
                    Direction direction;
                    int radius = 100;
                    int centerX;
                    int centerY;

                    if (cornerRoad.getStartAngle() == 90) { //Top Left - WORKS
                        angle = Math.toRadians(180);
                        endAngle = Math.toRadians(270);
                        direction = Direction.RIGHT;

                        centerX = cornerRoad.getxStart() + cornerRoad.getLenght() / 2;
                        centerY = cornerRoad.getYStart() + 10;
                    } else if (cornerRoad.getStartAngle() == 180) { //Bottom Left
                        angle = Math.toRadians(90);
                        endAngle = Math.toRadians(180);
                        direction = Direction.UP;

                        centerX = cornerRoad.getxStart() + (this.aTerrain.getLenght() / 2);
                        centerY = cornerRoad.getYStart();
                    } else if (cornerRoad.getStartAngle() == 270) { //Bottom Right - WORKS
                        angle = Math.toRadians(0);
                        endAngle = Math.toRadians(90);
                        direction = Direction.LEFT;

                        centerX = cornerRoad.getxStart();
                        centerY = cornerRoad.getYStart() - 15;
                    } else { //Top Right - WORKS BUT BENDS WRONG WAY
                        angle = Math.toRadians(270);
                        endAngle = Math.toRadians(360);
                        direction = Direction.DOWN;

                        centerX = cornerRoad.getxStart();
                        centerY = cornerRoad.getYStart();
                    }

                    turnCorner(tmpCar, cornerRoad, angle, endAngle, direction, radius, centerX, centerY);
                    moveThisVehicleToTheNextCorrectStack(tmpCar);
                } else { //SquareJunction
//                    System.out.println("I am SquareJunction:  ");

                    tmpCar.getPerson().decide(this.aTerrain.getNeighboursTerrainList());
                    int myDecision = tmpCar.getPerson().getDecision();

                    Terrain nextTerrain = aTerrain.getNeighboursTerrainList().get(myDecision);

//                            System.out.println("Which Node: "+  trafficManagement.getTerrainList().indexOf(nextTerrain));

                    Direction dir = getDirection(tmpCar, nextTerrain);
                    Direction dirJunction = getDirection(tmpCar, this.aTerrain);

//                            System.out.println("dir: " + dir + " dirJun: "+ dirJunction);

                    if (dir.equals(dirJunction)) {
//                        System.out.println("Go Straight!");
                        moveToEnd(tmpCar, this.aTerrain.getLenght(), dir); //-tmpCar.getLength()
                    } else if (isFirstExit(dirJunction, dir)) { //Check this method
//                        System.out.println("First Exit!");
                        firstExit(tmpCar, nextTerrain, this.aTerrain, dir, dirJunction);
                    } else {
//                        System.out.println("Second Exit!");
                        secondExitChanged(tmpCar, nextTerrain, (SquareJunction) this.aTerrain, dir, dirJunction);
                    }

                    moveThisVehicleToTheNextCorrectStack(tmpCar);
                }
                map.repaint();
            } else {  //flow == 0

                Car tmpCar = (Car) vehicle;
//                System.out.println("Node:  " + this.trafficManagement.getTerrainList().indexOf(this.aTerrain) + " Flow : 0" + "  Car Name:  " + vehicle.toString());
                if (this.aTerrain instanceof StraightRoad) {
//                            int offset = ((this.aTerrain.getBackwardListFlow().size() - 1) * 20);

                    Direction dir = getDirection(tmpCar, this.aTerrain);
                    tmpCar.setCurrentDirection(dir);

//                    System.out.println("I am StraightRoad:  flow is " + flowDirection);
                    if (trafficManagement.getTerrainList().indexOf(this.aTerrain) == 1) {
//                                moveToEnd(tmpCar, this.aTerrain.getLenght()-tmpCar.getLength(), dir);
                        moveToEndChanged(tmpCar, this.aTerrain, dir);
                    } else {
//                                moveToEnd(tmpCar, this.aTerrain.getLenght(), dir);
                        moveToEndChanged(tmpCar, this.aTerrain, dir);
                    }

                    if (isThereATrafficLight(this.aTerrain.getBackwardListFlow())) {
//                        System.out.println("DEBUG11");
                        while (!checkIfTrafficLightIsGreen(((TrafficLights) aTerrain.getBackwardListFlow().get(0)))) {
                            System.out.println("DEBUG22");
                        }
                    }
                    moveThisVehicleToTheNextCorrectStack(tmpCar);

//                    System.out.println("Length: " + this.aTerrain.getLenght() + " this road: " + this.aTerrain);
                } else if (this.aTerrain instanceof CornerRoad) {
                    CornerRoad cornerRoad = (CornerRoad) this.aTerrain;
                    double angle, endAngle;
                    Direction direction;
                    int radius = 50;
                    int centerX;
                    int centerY;

                    if (cornerRoad.getStartAngle() == 90) { //Top Left
                        angle = Math.toRadians(180);
                        endAngle = Math.toRadians(90);
                        direction = Direction.LEFT;

                        centerX = (cornerRoad.getxStart() + cornerRoad.getLenght() / 2);
                        centerY = cornerRoad.getYStart() + 10;
                    } else if (cornerRoad.getStartAngle() == 180) { //Bottom Left - WORKS
                        angle = Math.toRadians(270);
                        endAngle = Math.toRadians(180);
                        direction = Direction.LEFT;

                        centerX = (cornerRoad.getxStart() + cornerRoad.getLenght() / 2) + 10;
                        centerY = cornerRoad.getYStart() - 15;
                    } else if (cornerRoad.getStartAngle() == 270) { //Bottom Right
                        angle = Math.toRadians(360);
                        endAngle = Math.toRadians(270);
                        direction = Direction.LEFT;
                        centerX = cornerRoad.getxStart() + 10;
                        centerY = cornerRoad.getYStart();
                    } else { //Top Right - WORKS
                        angle = Math.toRadians(90);
                        endAngle = Math.toRadians(0);
                        direction = Direction.LEFT;
                        centerX = cornerRoad.getxStart() - 25;
                        centerY = cornerRoad.getYStart() + 15;
                    }

                    turnCorner(tmpCar, cornerRoad, angle, endAngle, direction, radius, centerX, centerY);
                    moveThisVehicleToTheNextCorrectStack(tmpCar);
                } else {//SquareJunction

//                    System.out.println("I am SquareJunction:  ");

                    tmpCar.getPerson().decide(this.aTerrain.getNeighboursTerrainList());
                    int myDecision = tmpCar.getPerson().getDecision();

                    Terrain nextTerrain = aTerrain.getNeighboursTerrainList().get(myDecision);

//                    System.out.println("Which Node: " + trafficManagement.getTerrainList().indexOf(nextTerrain));

                    Direction dir = getDirection(tmpCar, nextTerrain);
                    Direction dirJunction = tmpCar.getCurrentDirection();

//                    System.out.println("dir: " + dir + " dirJun: " + dirJunction);

                    if (dir.equals(dirJunction)) {
//                        System.out.println("Go Straight!");
                        moveToEnd(tmpCar, this.aTerrain.getLenght(), dir);
                    } else if (isFirstExit(dirJunction, dir)) {
//                        System.out.println("First Exit!");
                        firstExit(tmpCar, nextTerrain, this.aTerrain, dir, dirJunction);
                    } else {
//                        System.out.println("Second Exit!");
                        secondExitChanged(tmpCar, nextTerrain, (SquareJunction) this.aTerrain, dir, dirJunction);
                    }

                    moveThisVehicleToTheNextCorrectStack(tmpCar);
                }

                map.repaint();

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void run() {

        vehicle.setLock(true);
        vehicle.setThatIAmMoving(true);
        startFlow();
        vehicle.setThatIAmMoving(false);
        vehicle.setLock(false);

    }

    public enum Direction {LEFT, RIGHT, UP, DOWN}
}
