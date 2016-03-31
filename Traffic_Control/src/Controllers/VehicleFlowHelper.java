package Controllers;

import Interfaces.Terrain;
import Interfaces.Vehicle;
import Nodes.CornerRoad;
import Nodes.SquareJunction;
import Nodes.StraightRoad;
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
    private volatile int offset;

    public VehicleFlowHelper(Vehicle vehicle, Draw aMap, int flowDirection, TrafficManagement trafficManagement, Terrain aTerrain, VehicleFlow vfl) {

        this.vehicle = vehicle;
        map = aMap;
        this.trafficManagement = trafficManagement;
        this.flowDirection = flowDirection;
        this.aTerrain = aTerrain;
        vehicleflow = vfl;
        offset = 0;

        if (flowDirection == 1) {
            currentObjectList = this.aTerrain.getForwardListFlow();
        } else {
            currentObjectList = this.aTerrain.getBackwardListFlow();
        }
    }

    /**
     * Drives a vehicle to a specified point on it current terrain
     *
     * @param vehicle
     * @param terrainLenght
     * @param dir
     */
    public void moveToPoint(Vehicle vehicle, int terrainLenght, Direction dir) {
        for (int i = 0; i < terrainLenght; i++) {
            vehicle.move(dir);
            map.repaint();
            try {
                Thread.sleep(trafficManagement.getTimeGranularity());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Drives the vehicle to the remaining length of the road based on the offset
     * calculated after the traffic light went red.
     *
     * @param vehicle
     * @param dir
     * @param offset
     */
    public void moveToRoadOffset(Vehicle vehicle, Direction dir, int offset) {
        int distance = 0;

        if (dir == Direction.RIGHT || dir == Direction.LEFT) {
            if (dir == Direction.RIGHT) {
                distance = vehicle.get_pos_x() + offset;
            } else if (dir == Direction.LEFT) {
                distance = vehicle.get_pos_x() - offset;
            }

            while (vehicle.get_pos_x() != distance) {
                vehicle.move(dir);

                try {
                    Thread.sleep(trafficManagement.getTimeGranularity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } else if (dir == Direction.DOWN || dir == Direction.UP) {
            if (dir == Direction.DOWN) {
                distance = vehicle.get_pos_y() + offset;
            } else if (dir == Direction.UP) {
                distance = vehicle.get_pos_y() - offset;
            }

            //while traffic light is red - loop
            while (vehicle.get_pos_y() != distance) {
                vehicle.move(dir);

                try {
                    Thread.sleep(trafficManagement.getTimeGranularity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        map.repaint();
    }

    public synchronized void moveToDestination(Vehicle vehicle, Terrain terrain, Direction dir) throws InterruptedException {
        if (dir == Direction.RIGHT || dir == Direction.LEFT) {
            int x;
            if (dir == Direction.RIGHT) {
                x = ((terrain.getxStart() + terrain.getLenght()) - vehicle.getLength());
            } else {
                x = (terrain.getxStart() + vehicle.getLength());
            }

            while (vehicle.get_pos_x() != x) {
                vehicle.move(dir);

                if (isThereATrafficLight(currentObjectList)) {
                    if (!checkIfTrafficLightIsGreen(((TrafficLights) currentObjectList.get(0))) && offset == 0) {
                        // TODO: 30/03/2016 Patrick change this 30 into a variable when you do the different vehicles
                        if (currentObjectList.indexOf(vehicle) > Math.floor(aTerrain.getLenght() / 30))
                            offset = (int) (Math.floor(aTerrain.getLenght() / 30 - 1) * 30);
                        else offset = (currentObjectList.indexOf(vehicle) - 1) * 30;

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

            if (isThereATrafficLight(currentObjectList)) {
                while (!checkIfTrafficLightIsGreen(((TrafficLights) currentObjectList.get(0))) && offset != 0) {
                    Thread.sleep(10);
                }

                if (offset != 0) {
                    moveToRoadOffset(vehicle, dir, offset);
                }
            }

        } else if (dir == Direction.UP || dir == Direction.DOWN) {
            int y;

            if (dir == Direction.UP) {
                y = terrain.getYStart() + vehicle.getLength();
            } else {
                y = (terrain.getYStart() + terrain.getLenght()) - vehicle.getLength();
            }

            while (vehicle.get_pos_y() != y) {
                vehicle.move(dir);

                if (isThereATrafficLight(currentObjectList)) {
                    //If the road has more than half of the road max. - set the road to green light
                    if (!checkIfTrafficLightIsGreen(((TrafficLights) currentObjectList.get(0))) && offset == 0) {

                        // TODO: 30/03/2016 Patrick change this 30 into a variable when you do the different vehicles
                        if (currentObjectList.indexOf(vehicle) > Math.floor(aTerrain.getLenght() / 30))
                            offset = (int) (Math.floor(aTerrain.getLenght() / 30 - 1) * 30);
                        else offset = (currentObjectList.indexOf(vehicle) - 1) * 30;

                        //Remove the offset from our destination
                        if (dir == Direction.UP) {
                            y = y + offset;
                        } else {
                            y = y - offset;
                        }

                    }
                }

                try {
                    Thread.sleep(trafficManagement.getTimeGranularity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (isThereATrafficLight(currentObjectList)) {
                while (!checkIfTrafficLightIsGreen(((TrafficLights) currentObjectList.get(0))) && offset != 0) {
                    Thread.sleep(10);
                }

                if (offset != 0) {
                    moveToRoadOffset(vehicle, dir, offset);
                }
            }

        }


    }

    /**
     * Drive the vehicle past the corner road
     *
     * @param vehicle
     * @param angle
     * @param endAngle
     * @param direction
     * @param radius
     * @param centerX
     * @param centerY
     */
    public void turnCorner(Vehicle vehicle, double angle, double endAngle, Direction direction, int radius, int centerX, int centerY) {

        double nAngle = angle;

        if (radius == 100) { //Top flow
            while (angle <= endAngle) {
                angle += 0.1;
                vehicle.turnCorner(angle, centerX, centerY, radius);
                vehicle.bend(direction, 5.625);
                map.repaint();

                try {
                    Thread.sleep(trafficManagement.getTimeGranularity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {

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

    public void moveThisVehicleToTheNextCorrectStack(Vehicle v) {

        if (
                (
                        (
                                (trafficManagement.getTerrainList().indexOf(this.aTerrain) == 1 && this.flowDirection == 1)
                                        ||
                                        (trafficManagement.getTerrainList().indexOf(this.aTerrain) == 0 && this.flowDirection == 0)

                        )
                                && this.trafficManagement.getOption() == 1
                )


                ) {
            System.out.println("EXIT NODE: Delete " + v.toString() + " and worker is: " + Thread.currentThread().getName());
        } else if
                (
                (
                        (
                                (trafficManagement.getTerrainList().indexOf(this.aTerrain) == 1 && this.flowDirection == 1)
                                        ||
                                        (trafficManagement.getTerrainList().indexOf(this.aTerrain) == 0 && this.flowDirection == 0)
                                        ||
                                        (trafficManagement.getTerrainList().indexOf(this.aTerrain) == 2 && this.flowDirection == 1)
                                        ||
                                        (trafficManagement.getTerrainList().indexOf(this.aTerrain) == 3 && this.flowDirection == 0)

                        )
                                && this.trafficManagement.getOption() == 0
                )
                ) {
            System.out.println("EXIT NODE: Delete " + v.toString() + " and worker is: " + Thread.currentThread().getName());
        } else if
                (
                (
                        (
                                (trafficManagement.getTerrainList().indexOf(this.aTerrain) == 1 && this.flowDirection == 1)
                                        ||
                                        (trafficManagement.getTerrainList().indexOf(this.aTerrain) == 0 && this.flowDirection == 0)
                                        ||
                                        (trafficManagement.getTerrainList().indexOf(this.aTerrain) == 2 && this.flowDirection == 1)
                                        ||
                                        (trafficManagement.getTerrainList().indexOf(this.aTerrain) == 3 && this.flowDirection == 0)

                        )
                                && this.trafficManagement.getOption() == 0
                )
                ) {
            System.out.println("EXIT NODE: Delete " + v.toString() + " and worker is: " + Thread.currentThread().getName());
        } else if
                (
                (
                        (
                                (trafficManagement.getTerrainList().indexOf(this.aTerrain) == 1 && this.flowDirection == 1)
                                        ||
                                        (trafficManagement.getTerrainList().indexOf(this.aTerrain) == 0 && this.flowDirection == 0)
                                        ||
                                        (trafficManagement.getTerrainList().indexOf(this.aTerrain) == 2 && this.flowDirection == 1)
                                        ||
                                        (trafficManagement.getTerrainList().indexOf(this.aTerrain) == 3 && this.flowDirection == 0)

                        )
                                && this.trafficManagement.getOption() == 2
                )
                ) {
            System.out.println("EXIT NODE: Delete " + v.toString() + " and worker is: " + Thread.currentThread().getName());
        } else {

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

        }
        vehicleflow.deleteFromCurrentList(vehicle);
        // TODO: 29/03/2016  to a deletevehicle (Vehicle v)
    }

    /**
     * Calculates whether the combination of directions the car is about to take leads to
     * the first or second exit.
     * Returns true if it is the first exit
     *
     * @param curD
     * @param nextD
     * @return
     */
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
     * Calculate the direction the vehicle needs to move
     * based on the end and start points of the terrain
     *
     * @param vehicle
     * @param t
     * @return
     */
    public synchronized Direction getDirection(Vehicle vehicle, Terrain t) {
        Direction dir = null;

        if (t instanceof SquareJunction) {
            dir = vehicle.getCurrentDirection();
        } else {
            //Rotation 0 is on the X-axis and 1 is on the Y-axis
            if (t.getRotation() == 0) {
                int xEnd = (t.getxStart() + t.getLenght()) - vehicle.getLength();
                int xStart = t.getxStart() + vehicle.getLength();

                if (xStart < vehicle.get_pos_x()) {
                    dir = Direction.LEFT;
                } else if (xEnd > vehicle.get_pos_x()) {
                    dir = Direction.RIGHT;
                }
            } else if (t.getRotation() != 0) {
                int yEnd = (t.getYStart() + t.getLenght()) - vehicle.getLength();
                int yStart = t.getYStart() + vehicle.getLength();
                if (yStart < vehicle.get_pos_y()) {
                    dir = Direction.UP;
                } else if (yEnd > vehicle.get_pos_y()) {
                    dir = Direction.DOWN;
                }
            }
        }

        return dir;
    }

    /**
     * Drives a vehicle out of its current terrain, into the junction and places
     * it in the terrain it chose which in this case is the first possible exit
     * from the junction
     *
     * @param vehicle
     * @param direction
     * @param dirJunction
     * @return
     */
    public Boolean firstExit(Vehicle vehicle, Direction direction, Direction dirJunction) {

        moveToPoint(vehicle, vehicle.getLength(), dirJunction);

        vehicle.turn(direction, 0);
        map.repaint();

        return true;
    }

    /**
     * Drives the car from its current terrain and into the third exit from the junction
     *
     * @param vehicle
     * @param myCurrentTerrain
     * @param dir
     * @param directionJunction
     * @return
     */
    public Boolean thirdExit(Vehicle vehicle, SquareJunction myCurrentTerrain, Direction dir, Direction directionJunction) {

        if (directionJunction == Direction.RIGHT || directionJunction == Direction.LEFT) { //Check on the X axis
            int x;

            if (directionJunction == Direction.RIGHT) {
                x = (myCurrentTerrain.getxStart() + (myCurrentTerrain.getLenght() / 2)) + vehicle.getLength();
            } else {
                x = (myCurrentTerrain.getxStart() + (myCurrentTerrain.getLenght() / 2)) - vehicle.getLength();
            }

            while (vehicle.get_pos_x() != x) {
                vehicle.move(directionJunction);
                try {
                    Thread.sleep(trafficManagement.getTimeGranularity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (directionJunction == Direction.UP || directionJunction == Direction.DOWN) { //Check on the Y axis
            int y;

            if (directionJunction == Direction.UP) {
                y = (myCurrentTerrain.getYStart() + (myCurrentTerrain.getLenght() / 2)) - vehicle.getLength();
            } else {
                y = (myCurrentTerrain.getYStart() + (myCurrentTerrain.getLenght() / 2)) + vehicle.getLength();
            }

            while (vehicle.get_pos_y() != y) {
                vehicle.move(directionJunction);

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
            vehicle.turn(dir, Math.toRadians(angle));
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
                x = (myCurrentTerrain.getxStart() + myCurrentTerrain.getLenght()) - vehicle.getLength();
            } else {
                x = (myCurrentTerrain.getxStart()) - vehicle.getLength();
            }

            while (vehicle.get_pos_x() != x) {
                vehicle.move(dir);

                try {
                    Thread.sleep(trafficManagement.getTimeGranularity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (dir == Direction.UP || dir == Direction.DOWN) { //Check on the Y axis
            int y;

            if (dir == Direction.UP) {
                y = myCurrentTerrain.getYStart() - vehicle.getLength();
            } else {
                y = (myCurrentTerrain.getYStart() + myCurrentTerrain.getLenght()) - vehicle.getLength();
            }

            while (vehicle.get_pos_y() != y) {
                vehicle.move(dir);

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

                Vehicle tmpCar = vehicle;
                System.out.println("Node:  " + this.trafficManagement.getTerrainList().indexOf(this.aTerrain) + " Flow : 1" + "  Car Name:  " + vehicle.toString());

                if (this.aTerrain instanceof StraightRoad) {

                    final Direction dir = getDirection(tmpCar, this.aTerrain); //If delayed the direction changes
                    tmpCar.setCurrentDirection(dir);
                    moveToDestination(tmpCar, this.aTerrain, dir);

                    if (isThereATrafficLight(this.aTerrain.getForwardListFlow())) {
                        //If the road has more than half of the road max. - set the road to green light
                        if (isThisTerrainBusy())
                            ((TrafficLights) aTerrain.getForwardListFlow().get(0)).setGreenLightDelay(8);
                        while (!checkIfTrafficLightIsGreen(((TrafficLights) aTerrain.getForwardListFlow().get(0)))) {
                            Thread.sleep(500);
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

                    turnCorner(tmpCar, angle, endAngle, direction, radius, centerX, centerY);
                    moveThisVehicleToTheNextCorrectStack(tmpCar);
                } else { //SquareJunction

                    tmpCar.getPerson().decide(this.aTerrain.getNeighboursTerrainList());
                    int myDecision = tmpCar.getPerson().getDecision();

                    Terrain nextTerrain = aTerrain.getNeighboursTerrainList().get(myDecision);

//                            System.out.println("Which Node: "+  trafficManagement.getTerrainList().indexOf(nextTerrain));

                    Direction dir = getDirection(tmpCar, nextTerrain);
                    Direction dirJunction = getDirection(tmpCar, this.aTerrain);

                    if (dir.equals(dirJunction)) {
                        moveToPoint(tmpCar, this.aTerrain.getLenght(), dir);
                    } else if (isFirstExit(dirJunction, dir)) {
                        firstExit(tmpCar, dir, dirJunction);
                    } else {
                        thirdExit(tmpCar, (SquareJunction) this.aTerrain, dir, dirJunction);
                    }

                    moveThisVehicleToTheNextCorrectStack(tmpCar);
                }
                map.repaint();
            } else {  //flow == 0

                Vehicle tmpCar = vehicle;
                System.out.println("Node:  " + this.trafficManagement.getTerrainList().indexOf(this.aTerrain) + " Flow : 0" + "  Car Name:  " + vehicle.toString());
                if (this.aTerrain instanceof StraightRoad) {

                    Direction dir = getDirection(tmpCar, this.aTerrain);
                    tmpCar.setCurrentDirection(dir);

                    moveToDestination(tmpCar, this.aTerrain, dir);

                    if (isThereATrafficLight(this.aTerrain.getBackwardListFlow())) {
                        //If the road has more than half of the road max. - set the road to green light
                        if (isThisTerrainBusy())
                            ((TrafficLights) aTerrain.getBackwardListFlow().get(0)).setGreenLightDelay(8);
                        while (!checkIfTrafficLightIsGreen(((TrafficLights) aTerrain.getBackwardListFlow().get(0)))) {
                            Thread.sleep(500);
                        }
                    }
                    moveThisVehicleToTheNextCorrectStack(tmpCar);

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

                    turnCorner(tmpCar, angle, endAngle, direction, radius, centerX, centerY);
                    moveThisVehicleToTheNextCorrectStack(tmpCar);
                } else {//SquareJunction

                    tmpCar.getPerson().decide(this.aTerrain.getNeighboursTerrainList());
                    int myDecision = tmpCar.getPerson().getDecision();

                    Terrain nextTerrain = aTerrain.getNeighboursTerrainList().get(myDecision);

                    Direction dir = getDirection(tmpCar, nextTerrain);
                    Direction dirJunction = tmpCar.getCurrentDirection();

                    if (dir.equals(dirJunction)) {
                        moveToPoint(tmpCar, this.aTerrain.getLenght(), dir);
                    } else if (isFirstExit(dirJunction, dir)) {
                        firstExit(tmpCar, dir, dirJunction);
                    } else {
                        thirdExit(tmpCar, (SquareJunction) this.aTerrain, dir, dirJunction);
                    }

                    moveThisVehicleToTheNextCorrectStack(tmpCar);
                }

                map.repaint();

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }

    //policy

    /**
     * To check whether the road is busy or not
     *
     * @return
     */
    public boolean isThisTerrainBusy() {
        int numberOfCars = 0;
        int threshold = 4;
        int roadLength;

        //put code for the forward  flow / 100
        //Parse the aTerrain.getForwardListFlow() and find how many vehicles are in there
        //and return the proper boolean statement
        if (this.flowDirection == 1) {

            if (this.aTerrain instanceof StraightRoad) {
                roadLength = aTerrain.getLenght();
                threshold = (roadLength / 60);
            }
            for (Object o : this.aTerrain.getForwardListFlow()) {

                if (o instanceof Vehicle) {
                    numberOfCars++;
                }
            }

            if (numberOfCars > threshold) {
                return true;
            } else {
                return false;
            }
        } else {
            //put code for the backward  flow / 100
            //Parse the aTerrain.getBackwardListFlow() and find how many vehicles are in there
            //and return the proper boolean statement
            if (this.aTerrain instanceof StraightRoad) {
                roadLength = aTerrain.getLenght();
                threshold = (roadLength / 60);
            }

            for (Object o : this.aTerrain.getBackwardListFlow()) {
                if (o instanceof Vehicle) {
                    numberOfCars++;
                }
            }

            if (numberOfCars > threshold) {
                return true;
            } else {
                return false;
            }
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
