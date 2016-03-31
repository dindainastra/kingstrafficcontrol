package Controllers;


import Frames.Buttons;
import Frames.Slider;
import Interfaces.Terrain;
import Interfaces.Vehicle;
import Nodes.CornerRoad;
import Nodes.SquareJunction;
import Nodes.StraightRoad;
import Objects.Car;
import Objects.Person;
import Objects.TrafficLights;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class TrafficManagement extends JFrame {


    private int option;
    private volatile ArrayList<Person> aPersonList;
    private volatile ArrayList<Vehicle> aVehicleList;
    private volatile ArrayList<Terrain> aTerrainList;
    private Random rand;
    private JFrame frame;
    private Draw map;
    private ArrayList<Runnable> runnableArrayList = new ArrayList<Runnable>();
    private int timeGranularity;

    private int selectMap;
    private int tlDelay;

    public TrafficManagement() {

        rand = new Random();

        aPersonList = new ArrayList<Person>();
        aVehicleList = new ArrayList<Vehicle>();
        aTerrainList = new ArrayList<Terrain>();
        timeGranularity = 20;
        tlDelay = 3000;
        option = 0;


    }

    /**
     *  Returns the Terrain List /  In other words, all the nodes
     *
     * @return aTerrainList
     */
    public ArrayList<Terrain> getTerrainList() {
        return this.aTerrainList;
    }

    /**
     * Deletes Vehicles randomly from the Node and from the All vehicle list
     *
     * @param number of how many Vehicle will be randomly deleted
     */
    public void deleteVehicle(int number) {

        for (int i = 0; i < number; i++) {

            int random_index = rand.nextInt(this.aVehicleList.size());
            Vehicle v = this.aVehicleList.get(random_index);
            this.aVehicleList.remove(v);

            for (Terrain t : aTerrainList)
                t.removeVehicleFromList(v);
        }

    }

    /**
     * Creates vehicles and add them in the 0 or 1 node entry randomly
     *
     * @param number of how many vehicle to be created
     */
    public void factoryVehicle(int number) {

        ArrayList<Person> tmpPersonList = new ArrayList<Person>();

        for (int i = 0; i < number; i++) {
            Person tmpP = new Person("Person " + i, rand.nextInt(10), false, null);
            aPersonList.add(tmpP);
            tmpPersonList.add(tmpP);
        }

        for (Person p : tmpPersonList) {
            if (!p.isPedestrian()) {
                if (new Random().nextBoolean()) {
                    Vehicle v = new Car(p, 30, 330);
                    aVehicleList.add(v);
                    this.aTerrainList.get(0).setForwardListFlow(v);
                } else {
                    Car v = new Car(p, 1400, 395);
                    v.setRotate(180);
                    aVehicleList.add(v);
                    this.aTerrainList.get(1).setBackwardListFlow(v);
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Creates vehicles and add them in the 0 or 1 node entry randomly with a set priority
     *
     * @param number of how many vehicle to be created
     * @param priority_flag if it is 1 is it priority
     */
    public void factoryVehicle(int number, int priority_flag) {

        ArrayList<Person> tmpPersonList = new ArrayList<Person>();

        for (int i = 0; i < number; i++) {
            Person tmpP = new Person("Person " + i, rand.nextInt(10), false, null);
            aPersonList.add(tmpP);
            tmpPersonList.add(tmpP);
        }

        for (Person p : tmpPersonList) {
            if (!p.isPedestrian()) {
                if (new Random().nextBoolean()) {
                    Vehicle v = new Car(p, 30, 330);
                    if (priority_flag == 1)
                        v.setPriority(1);
                    aVehicleList.add(v);
                    this.aTerrainList.get(0).setForwardListFlow(v);
                } else {
                    Car v = new Car(p, 1400, 395);
                    v.setRotate(180);
                    if (priority_flag == 1)
                        v.setPriority(1);
                    aVehicleList.add(v);
                    this.aTerrainList.get(1).setBackwardListFlow(v);
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void mapSelector(int mapNo) {
        this.selectMap = mapNo;

    }

    /**
     * Runnable method that run and initialize everything. Also initializes the proper graphs and traffic lights dependencies
     */
    public void run() {
        //createPersons(10);
        //createVehicles();

        if (option == 0) {

            staticMapCreatorCrossRoad();
            initializeNeighboursTerrainListsForCrossRoad();

        } else if (option == 1) {

            staticMapCreatorTown();
            initializeNeighboursTerrainListsForTown();


        } else {

            staticMapCreatorRoundabout();
            initializeNeighboursTerrainListsForRoundabout();
        }


        //first init the trafficlights to have the firsts position in the Collection
//        initializeStaticTrafficLights();

        //init all the vehicles
//        initializeForwardAndBackwardLists();

        //do the graph to know every node their closest nodes

        map = new Draw(aTerrainList);


        drawTheMap(map);

        start();

    }

    /**
     * Start the flow of cars in the node system
     * For each car in the vehicle list, create a thread for the cars flow
     */
    public void start() {

        for (Terrain t : aTerrainList) {
            new Thread(new VehicleFlow(t, map, 1, this)).start();
            new Thread(new VehicleFlow(t, map, 0, this)).start();
        }

    }

    public void createVehicles() {
        for (Person p : aPersonList) {
            if (!p.isPedestrian()) {
                if (new Random().nextBoolean()) {
                    Car c = new Car(p, 1400, 395);
//                    c.setRotate(180);
                    aVehicleList.add(c);
                } else {
                    Car c = new Car(p, 1380, 420);
                    c.setRotate(180);
                    aVehicleList.add(c);
                }
            }
        }
    }

    /**
     * Creates a Persons
     * @param aNumber of how many persons has to be created
     */
    public void createPersons(int aNumber) {
        for (int i = 0; i < aNumber; i++) {
            aPersonList.add(new Person("Person " + i, rand.nextInt(10), false, null));
        }
    }

    /**
     * initialize the forward and backward flow lists
     */
    public void initializeForwardAndBackwardLists() {
        for (Vehicle v : this.aVehicleList)
            if (v.get_pos_x() == 30)
                this.aTerrainList.get(0).setForwardListFlow(v);  //insert vehicle in the enter node direction list -->
            else
                this.aTerrainList.get(1).setBackwardListFlow(v);  //insert vehicle in the exit node direction list <--

    }

    /**
     * Initialize Node Neighbours - Relationships for CrossRoad Map
     */
    public void initializeNeighboursTerrainListsForCrossRoad() {

        this.aTerrainList.get(0).setNeighboursTerrainList(this.aTerrainList.get(4));

        this.aTerrainList.get(1).setNeighboursTerrainList(this.aTerrainList.get(4));

        this.aTerrainList.get(2).setNeighboursTerrainList(this.aTerrainList.get(4));

        this.aTerrainList.get(3).setNeighboursTerrainList(this.aTerrainList.get(4));

        this.aTerrainList.get(4).setNeighboursTerrainList(this.aTerrainList.get(1));
        this.aTerrainList.get(4).setNeighboursTerrainList(this.aTerrainList.get(0));
        this.aTerrainList.get(4).setNeighboursTerrainList(this.aTerrainList.get(2));
        this.aTerrainList.get(4).setNeighboursTerrainList(this.aTerrainList.get(3));

    }

    /**
     * Initialize Node Neighbours - Relationships for Roundabout
     */
    public void initializeNeighboursTerrainListsForRoundabout() {

        Terrain t = null;

        this.aTerrainList.get(0).setNeighboursTerrainList(this.aTerrainList.get(17));

        this.aTerrainList.get(1).setNeighboursTerrainList(this.aTerrainList.get(16));

        this.aTerrainList.get(2).setNeighboursTerrainList(this.aTerrainList.get(19));

        this.aTerrainList.get(3).setNeighboursTerrainList(this.aTerrainList.get(18));

        this.aTerrainList.get(4).setNeighboursTerrainList(this.aTerrainList.get(19));
        this.aTerrainList.get(4).setNeighboursTerrainList(this.aTerrainList.get(14));

        this.aTerrainList.get(5).setNeighboursTerrainList(this.aTerrainList.get(15));
        this.aTerrainList.get(5).setNeighboursTerrainList(this.aTerrainList.get(18));

        this.aTerrainList.get(6).setNeighboursTerrainList(this.aTerrainList.get(18));
        this.aTerrainList.get(6).setNeighboursTerrainList(this.aTerrainList.get(13));

        this.aTerrainList.get(7).setNeighboursTerrainList(this.aTerrainList.get(12));
        this.aTerrainList.get(7).setNeighboursTerrainList(this.aTerrainList.get(19));

        this.aTerrainList.get(8).setNeighboursTerrainList(this.aTerrainList.get(14));
        this.aTerrainList.get(8).setNeighboursTerrainList(this.aTerrainList.get(17));

        this.aTerrainList.get(9).setNeighboursTerrainList(this.aTerrainList.get(17));
        this.aTerrainList.get(9).setNeighboursTerrainList(this.aTerrainList.get(15));

        this.aTerrainList.get(10).setNeighboursTerrainList(this.aTerrainList.get(13));
        this.aTerrainList.get(10).setNeighboursTerrainList(this.aTerrainList.get(16));

        this.aTerrainList.get(11).setNeighboursTerrainList(this.aTerrainList.get(16));
        this.aTerrainList.get(11).setNeighboursTerrainList(this.aTerrainList.get(12));

        this.aTerrainList.get(12).setNeighboursTerrainList(this.aTerrainList.get(11));
        this.aTerrainList.get(12).setNeighboursTerrainList(this.aTerrainList.get(7));

        this.aTerrainList.get(13).setNeighboursTerrainList(this.aTerrainList.get(6));
        this.aTerrainList.get(13).setNeighboursTerrainList(this.aTerrainList.get(10));

        this.aTerrainList.get(14).setNeighboursTerrainList(this.aTerrainList.get(4));
        this.aTerrainList.get(14).setNeighboursTerrainList(this.aTerrainList.get(8));

        this.aTerrainList.get(15).setNeighboursTerrainList(this.aTerrainList.get(9));
        this.aTerrainList.get(15).setNeighboursTerrainList(this.aTerrainList.get(5));

        this.aTerrainList.get(16).setNeighboursTerrainList(this.aTerrainList.get(1));
        this.aTerrainList.get(16).setNeighboursTerrainList(this.aTerrainList.get(11));
        this.aTerrainList.get(16).setNeighboursTerrainList(this.aTerrainList.get(10));

        this.aTerrainList.get(17).setNeighboursTerrainList(this.aTerrainList.get(8));
        this.aTerrainList.get(17).setNeighboursTerrainList(this.aTerrainList.get(0));
        this.aTerrainList.get(17).setNeighboursTerrainList(t);
        this.aTerrainList.get(17).setNeighboursTerrainList(this.aTerrainList.get(9));

        this.aTerrainList.get(18).setNeighboursTerrainList(this.aTerrainList.get(5));
        this.aTerrainList.get(18).setNeighboursTerrainList(this.aTerrainList.get(6));
        this.aTerrainList.get(18).setNeighboursTerrainList(t);
        this.aTerrainList.get(18).setNeighboursTerrainList(this.aTerrainList.get(3));

        this.aTerrainList.get(19).setNeighboursTerrainList(this.aTerrainList.get(7));
        this.aTerrainList.get(19).setNeighboursTerrainList(this.aTerrainList.get(4));
        this.aTerrainList.get(19).setNeighboursTerrainList(this.aTerrainList.get(2));

    }

    /**
     * Initialize Node Neighbours - Relationships for Town Map
     */
    public void initializeNeighboursTerrainListsForTown() {

        this.aTerrainList.get(0).setNeighboursTerrainList(this.aTerrainList.get(19));

        this.aTerrainList.get(1).setNeighboursTerrainList(this.aTerrainList.get(18));

        this.aTerrainList.get(2).setNeighboursTerrainList(this.aTerrainList.get(22));
        this.aTerrainList.get(2).setNeighboursTerrainList(this.aTerrainList.get(19));

        this.aTerrainList.get(3).setNeighboursTerrainList(this.aTerrainList.get(18));
        this.aTerrainList.get(3).setNeighboursTerrainList(this.aTerrainList.get(22));

        this.aTerrainList.get(4).setNeighboursTerrainList(this.aTerrainList.get(21));
        this.aTerrainList.get(4).setNeighboursTerrainList(this.aTerrainList.get(16));

        this.aTerrainList.get(5).setNeighboursTerrainList(this.aTerrainList.get(20));
        this.aTerrainList.get(5).setNeighboursTerrainList(this.aTerrainList.get(17));

        this.aTerrainList.get(6).setNeighboursTerrainList(this.aTerrainList.get(20));
        this.aTerrainList.get(6).setNeighboursTerrainList(this.aTerrainList.get(15));

        this.aTerrainList.get(7).setNeighboursTerrainList(this.aTerrainList.get(14));
        this.aTerrainList.get(7).setNeighboursTerrainList(this.aTerrainList.get(21));

        this.aTerrainList.get(8).setNeighboursTerrainList(this.aTerrainList.get(21));
        this.aTerrainList.get(8).setNeighboursTerrainList(this.aTerrainList.get(22));

        this.aTerrainList.get(9).setNeighboursTerrainList(this.aTerrainList.get(22));
        this.aTerrainList.get(9).setNeighboursTerrainList(this.aTerrainList.get(20));

        this.aTerrainList.get(10).setNeighboursTerrainList(this.aTerrainList.get(16));
        this.aTerrainList.get(10).setNeighboursTerrainList(this.aTerrainList.get(19));

        this.aTerrainList.get(11).setNeighboursTerrainList(this.aTerrainList.get(19));
        this.aTerrainList.get(11).setNeighboursTerrainList(this.aTerrainList.get(17));

        this.aTerrainList.get(12).setNeighboursTerrainList(this.aTerrainList.get(18));
        this.aTerrainList.get(12).setNeighboursTerrainList(this.aTerrainList.get(15));

        this.aTerrainList.get(13).setNeighboursTerrainList(this.aTerrainList.get(14));
        this.aTerrainList.get(13).setNeighboursTerrainList(this.aTerrainList.get(18));

        this.aTerrainList.get(14).setNeighboursTerrainList(this.aTerrainList.get(7));
        this.aTerrainList.get(14).setNeighboursTerrainList(this.aTerrainList.get(13));

        this.aTerrainList.get(15).setNeighboursTerrainList(this.aTerrainList.get(12));
        this.aTerrainList.get(15).setNeighboursTerrainList(this.aTerrainList.get(6));

        this.aTerrainList.get(16).setNeighboursTerrainList(this.aTerrainList.get(4));
        this.aTerrainList.get(16).setNeighboursTerrainList(this.aTerrainList.get(10));

        this.aTerrainList.get(17).setNeighboursTerrainList(this.aTerrainList.get(11));
        this.aTerrainList.get(17).setNeighboursTerrainList(this.aTerrainList.get(5));

        this.aTerrainList.get(18).setNeighboursTerrainList(this.aTerrainList.get(1));
        this.aTerrainList.get(18).setNeighboursTerrainList(this.aTerrainList.get(3));
        this.aTerrainList.get(18).setNeighboursTerrainList(this.aTerrainList.get(12));
        this.aTerrainList.get(18).setNeighboursTerrainList(this.aTerrainList.get(13));

        this.aTerrainList.get(19).setNeighboursTerrainList(this.aTerrainList.get(2));
        this.aTerrainList.get(19).setNeighboursTerrainList(this.aTerrainList.get(0));
        this.aTerrainList.get(19).setNeighboursTerrainList(this.aTerrainList.get(10));
        this.aTerrainList.get(19).setNeighboursTerrainList(this.aTerrainList.get(11));

        this.aTerrainList.get(20).setNeighboursTerrainList(this.aTerrainList.get(5));
        this.aTerrainList.get(20).setNeighboursTerrainList(this.aTerrainList.get(6));
        this.aTerrainList.get(20).setNeighboursTerrainList(this.aTerrainList.get(9));

        this.aTerrainList.get(21).setNeighboursTerrainList(this.aTerrainList.get(7));
        this.aTerrainList.get(21).setNeighboursTerrainList(this.aTerrainList.get(4));
        this.aTerrainList.get(21).setNeighboursTerrainList(this.aTerrainList.get(8));

        this.aTerrainList.get(22).setNeighboursTerrainList(this.aTerrainList.get(3));
        this.aTerrainList.get(22).setNeighboursTerrainList(this.aTerrainList.get(2));
        this.aTerrainList.get(22).setNeighboursTerrainList(this.aTerrainList.get(8));
        this.aTerrainList.get(22).setNeighboursTerrainList(this.aTerrainList.get(9));
    }

    /**
     * Initialize Node Neighbours - Relationships for Random Map
     */
    public void initializeRandomTrafficLights() {

    }

    /**
     * Create the Terrains - Nodes for Roundabout
     */
    public void staticMapCreatorRoundabout() {

        //create FIRSTLY the entry and exit roads
        aTerrainList.add(new StraightRoad(50, 325, 0, 100, this));//entry road    //0
        aTerrainList.add(new StraightRoad(1280, 325, 0, 100, this));//exit road  //1

        aTerrainList.add(new StraightRoad(665, 10, 90, 100, this));//entry road #2    //2
        aTerrainList.add(new StraightRoad(665, 640, 90, 100, this));//exit road #3 //3
        //next, create the rest map

        //add horizontal road
        aTerrainList.add(new StraightRoad(250, 110, 0, 415, this));//4-moved down 100
        aTerrainList.add(new StraightRoad(250, 540, 0, 415, this));//5-move up by 50
        aTerrainList.add(new StraightRoad(765, 540, 0, 415, this));//6-move up by 50
        aTerrainList.add(new StraightRoad(765, 110, 0, 415, this));//7-moved down 100

        //add vertical roads
        aTerrainList.add(new StraightRoad(150, 210, 90, 115, this));//8-moved down 100 decrease size by 100
        aTerrainList.add(new StraightRoad(150, 425, 90, 115, this));//9-decrease size by 50
        aTerrainList.add(new StraightRoad(1180, 425, 90, 115, this));//10 -decrease size by 50
        aTerrainList.add(new StraightRoad(1180, 210, 90, 115, this));//11-moved down 100 decrease size by 100

//        //add curved roads
//        aTerrainList.add(new CornerRoad(1080, 110, 360, 0, this));//13-moved down 100
//        aTerrainList.add(new CornerRoad(1080, 440, 270, 0, this)); //14-move up by 50
//        aTerrainList.add(new CornerRoad(150, 110, 90, 0, this));  //15-moved down 100
//        aTerrainList.add(new CornerRoad(150, 440, 180, 0, this));//16-move up by 50

//        //add junctions (0,0,4,3: to close the square junctions)
        aTerrainList.add(new SquareJunction(1180, 110, this, 0, 2));//18 (top right)
        aTerrainList.add(new SquareJunction(1180, 540, this, 0, 4)); //19 (bottom right)
        aTerrainList.add(new SquareJunction(150, 110, this, 0, 1));//20-move up by 50 (top left)
        aTerrainList.add(new SquareJunction(150, 540, this, 0, 3));//21 -moved down 100 (bottom left)

        //add junctions (0,0,4,3: to close the square junctions)
        aTerrainList.add(new SquareJunction(1180, 325, this, 2, 0));//18
        aTerrainList.add(new SquareJunction(150, 325, this, 1, 0)); //19
        aTerrainList.add(new SquareJunction(665, 540, this, 3, 0));//20-move up by 50
        aTerrainList.add(new SquareJunction(665, 110, this, 4, 0));//21 -moved down 100

        //junction16
        TrafficLights thirteenthTL = new TrafficLights(1280, 325, 2, 90, 3000);
        TrafficLights seventhTL = new TrafficLights(1280, 375, 1, 0, 3000);
        TrafficLights fourteenthTL = new TrafficLights(1230, 425, 1, 90, 3000);
        thirteenthTL.previousTrafficLightIs(fourteenthTL);
        seventhTL.previousTrafficLightIs(thirteenthTL);
        fourteenthTL.previousTrafficLightIs(seventhTL);
        thirteenthTL.nextTrafficLightIs(seventhTL);
        seventhTL.nextTrafficLightIs(fourteenthTL);
        fourteenthTL.nextTrafficLightIs(thirteenthTL);

        //junction 17
        TrafficLights thirdTL = new TrafficLights(250, 325, 2, 90, 3000);
        //TrafficLights firstTL = new TrafficLights(250, 375, 1, 0, 3000);
        TrafficLights fourthTL = new TrafficLights(200, 425, 1, 90, 3000);
        TrafficLights secondTL = new TrafficLights(150, 325, 1, 0, 3000);
        thirdTL.previousTrafficLightIs(secondTL);
        //firstTL.previousTrafficLightIs(thirdTL);
        fourthTL.previousTrafficLightIs(thirdTL);
        secondTL.previousTrafficLightIs(fourthTL);
        thirdTL.nextTrafficLightIs(fourthTL);
        //firstTL.nextTrafficLightIs(fourthTL);
        fourthTL.nextTrafficLightIs(secondTL);
        secondTL.nextTrafficLightIs(thirdTL);

        //junction18
        TrafficLights eleventhTL = new TrafficLights(765, 590, 2, 0, 3000);
        TrafficLights tenthTL = new TrafficLights(665, 540, 1, 0, 3000);
        eleventhTL.previousTrafficLightIs(tenthTL);
        tenthTL.previousTrafficLightIs(eleventhTL);
        eleventhTL.nextTrafficLightIs(tenthTL);
        tenthTL.nextTrafficLightIs(eleventhTL);

        //junction21
        TrafficLights ninthTL = new TrafficLights(665, 110, 2, 0, 3000);
        TrafficLights twelfthTL = new TrafficLights(765, 160, 1, 0, 3000);

        ninthTL.previousTrafficLightIs(twelfthTL);
        twelfthTL.previousTrafficLightIs(ninthTL);
        ninthTL.nextTrafficLightIs(twelfthTL);
        twelfthTL.nextTrafficLightIs(ninthTL);

        //junction16
        aTerrainList.get(1).setBackwardListFlow(seventhTL); //
        aTerrainList.get(11).setForwardListFlow(thirteenthTL); //down
        aTerrainList.get(10).setBackwardListFlow(fourteenthTL); //up

        //junction17
        aTerrainList.get(9).setForwardListFlow(fourthTL); //down supposed to be backward
        aTerrainList.get(0).setForwardListFlow(secondTL); //supposed to be backward
        aTerrainList.get(8).setBackwardListFlow(thirdTL); //up

        //junction18
        aTerrainList.get(6).setForwardListFlow(eleventhTL);
        aTerrainList.get(5).setBackwardListFlow(tenthTL);

        //junction21
        aTerrainList.get(4).setForwardListFlow(ninthTL);
        aTerrainList.get(7).setBackwardListFlow(twelfthTL); //supposed to be backward
    }

    /**
     * Create the Terrains - Nodes for CrossRoad Map
     */
    public void staticMapCreatorCrossRoad() {

        //create FIRSTLY the horizontal entry and exit roads
        aTerrainList.add(new StraightRoad(50, 325, 0, 650, this));//entry road    //0
        aTerrainList.add(new StraightRoad(800, 325, 0, 580, this));//exit road  //1
        //create the vertical entry and exit roads
        aTerrainList.add(new StraightRoad(700, 60, 90, 265, this)); //2
        aTerrainList.add(new StraightRoad(700, 425, 90, 265, this));//3

        //next, create the rest map

        //add square junction
        aTerrainList.add(new SquareJunction(700, 325, this, 0, 0));//4

        //draw traffic lights
        //junction22
        TrafficLights eighthTL = new TrafficLights(800, 375, 2, 0, 3000);
        TrafficLights fifthTL = new TrafficLights(700, 325, 1, 0, 3000);

        eighthTL.nextTrafficLightIs(fifthTL);
        fifthTL.nextTrafficLightIs(eighthTL);
        eighthTL.previousTrafficLightIs(fifthTL);
        fifthTL.previousTrafficLightIs(eighthTL);


        aTerrainList.get(1).setBackwardListFlow(eighthTL); //supposed to be backward
        aTerrainList.get(0).setForwardListFlow(fifthTL);
    }

    /**
     * Create the Terrains - Nodes for Town Map
     */
    public void staticMapCreatorTown() {

        //create FIRSTLY the entry and exit roads
        aTerrainList.add(new StraightRoad(50, 325, 0, 100, this));//entry road    //0
        aTerrainList.add(new StraightRoad(1280, 325, 0, 100, this));//exit road  //1

        //next, create the rest map

        //add horizontal road
        aTerrainList.add(new StraightRoad(250, 325, 0, 565, this));     //2
        aTerrainList.add(new StraightRoad(915, 325, 0, 265, this));//3
        aTerrainList.add(new StraightRoad(250, 10, 0, 565, this));//4
        aTerrainList.add(new StraightRoad(250, 590, 0, 565, this));//5
        aTerrainList.add(new StraightRoad(915, 590, 0, 265, this));//6
        aTerrainList.add(new StraightRoad(915, 10, 0, 265, this));//7

        //add vertical roads
        aTerrainList.add(new StraightRoad(815, 110, 90, 215, this)); //8
        aTerrainList.add(new StraightRoad(815, 425, 90, 165, this));//9
        aTerrainList.add(new StraightRoad(150, 110, 90, 215, this));   //10
        aTerrainList.add(new StraightRoad(150, 425, 90, 165, this));//11
        aTerrainList.add(new StraightRoad(1180, 425, 90, 165, this));//12
        aTerrainList.add(new StraightRoad(1180, 110, 90, 215, this));//13

        //add curved roads
        aTerrainList.add(new CornerRoad(1080, 10, 360, 0, this));        //14
        aTerrainList.add(new CornerRoad(1080, 490, 270, 0, this));           //15
        aTerrainList.add(new CornerRoad(150, 10, 90, 0, this));                  //16
        aTerrainList.add(new CornerRoad(150, 490, 180, 0, this));//17

        //add junctions (0,0,4,3: to close the square junctions)
        aTerrainList.add(new SquareJunction(1180, 325, this, 0, 0));//18
        aTerrainList.add(new SquareJunction(150, 325, this, 0, 0));     //19
        aTerrainList.add(new SquareJunction(815, 590, this, 4, 0));//20
        aTerrainList.add(new SquareJunction(815, 10, this, 3, 0));//21
        aTerrainList.add(new SquareJunction(815, 325, this, 0, 0));//22

        //junction21
        TrafficLights ninthTL = new TrafficLights(815, 10, 2, 0, 3000);
        TrafficLights twelfthTL = new TrafficLights(915, 60, 1, 0, 3000);
        TrafficLights eighteenthTL = new TrafficLights(865, 110, 1, 90, 3000);

        ninthTL.previousTrafficLightIs(eighteenthTL);
        twelfthTL.previousTrafficLightIs(ninthTL);
        eighteenthTL.previousTrafficLightIs(twelfthTL);
        ninthTL.nextTrafficLightIs(twelfthTL);
        twelfthTL.nextTrafficLightIs(eighteenthTL);
        eighteenthTL.nextTrafficLightIs(ninthTL);


//        //junction 19
        TrafficLights thirdTL = new TrafficLights(250, 325, 2, 90, 3000);
        TrafficLights firstTL = new TrafficLights(250, 375, 1, 0, 3000);
        TrafficLights fourthTL = new TrafficLights(200, 425, 1, 90, 3000);
        TrafficLights secondTL = new TrafficLights(150, 325, 1, 0, 3000);
        thirdTL.previousTrafficLightIs(secondTL);
        firstTL.previousTrafficLightIs(thirdTL);
        fourthTL.previousTrafficLightIs(firstTL);
        secondTL.previousTrafficLightIs(fourthTL);
        thirdTL.nextTrafficLightIs(firstTL);
        firstTL.nextTrafficLightIs(fourthTL);
        fourthTL.nextTrafficLightIs(secondTL);
        secondTL.nextTrafficLightIs(thirdTL);


//        //junction22
        TrafficLights fifteenTL = new TrafficLights(915, 325, 2, 90, 3000);
        TrafficLights eighthTL = new TrafficLights(915, 375, 1, 0, 3000);
        TrafficLights sixteenthTL = new TrafficLights(865, 425, 1, 90, 3000);
        TrafficLights fifthTL = new TrafficLights(815, 325, 1, 0, 3000);
        fifteenTL.previousTrafficLightIs(fifthTL);
        eighthTL.previousTrafficLightIs(fifteenTL);
        sixteenthTL.previousTrafficLightIs(eighthTL);
        fifthTL.previousTrafficLightIs(sixteenthTL);
        fifteenTL.nextTrafficLightIs(eighthTL);
        eighthTL.nextTrafficLightIs(sixteenthTL);
        sixteenthTL.nextTrafficLightIs(fifthTL);
        fifthTL.nextTrafficLightIs(fifteenTL);
//
//
//        //junction18
        TrafficLights thirteenthTL = new TrafficLights(1280, 325, 2, 90, 3000);
        TrafficLights seventhTL = new TrafficLights(1280, 375, 1, 0, 3000);
        TrafficLights fourteenthTL = new TrafficLights(1230, 425, 1, 90, 3000);
        TrafficLights sixthTL = new TrafficLights(1180, 325, 1, 0, 3000);
        thirteenthTL.previousTrafficLightIs(sixthTL);
        seventhTL.previousTrafficLightIs(thirteenthTL);
        fourteenthTL.previousTrafficLightIs(seventhTL);
        sixthTL.previousTrafficLightIs(fourteenthTL);
        thirteenthTL.nextTrafficLightIs(seventhTL);
        seventhTL.nextTrafficLightIs(fourteenthTL);
        fourteenthTL.nextTrafficLightIs(sixthTL);
        sixthTL.nextTrafficLightIs(thirteenthTL);
//
//
//        //junction20

        TrafficLights seventeenthTL = new TrafficLights(915, 590, 2, 90, 3000);
        TrafficLights eleventhTL = new TrafficLights(915, 640, 1, 0, 3000);
        TrafficLights tenthTL = new TrafficLights(815, 590, 1, 0, 3000);
        seventeenthTL.previousTrafficLightIs(tenthTL);
        eleventhTL.previousTrafficLightIs(seventeenthTL);
        tenthTL.previousTrafficLightIs(eleventhTL);
        seventeenthTL.nextTrafficLightIs(eleventhTL);
        eleventhTL.nextTrafficLightIs(tenthTL);
        tenthTL.nextTrafficLightIs(seventeenthTL);

        //junction21
        aTerrainList.get(4).setForwardListFlow(ninthTL);
        aTerrainList.get(7).setBackwardListFlow(twelfthTL); //supposed to be backward
        aTerrainList.get(8).setForwardListFlow(eighteenthTL);//up supposed to be backward
//
//        //junction19
        aTerrainList.get(11).setForwardListFlow(fourthTL); //down supposed to be backward
        aTerrainList.get(0).setForwardListFlow(secondTL); //supposed to be backward
        aTerrainList.get(10).setBackwardListFlow(thirdTL); //up
        aTerrainList.get(2).setBackwardListFlow(firstTL);
//
//        //jun22
        aTerrainList.get(8).setBackwardListFlow(fifteenTL);//down
        aTerrainList.get(3).setBackwardListFlow(eighthTL); //supposed to be backward
        aTerrainList.get(9).setForwardListFlow(sixteenthTL);//up
        aTerrainList.get(2).setForwardListFlow(fifthTL);

        //junction18
        aTerrainList.get(3).setForwardListFlow(sixthTL);
        aTerrainList.get(1).setBackwardListFlow(seventhTL); //
        aTerrainList.get(13).setForwardListFlow(thirteenthTL); //down
        aTerrainList.get(12).setBackwardListFlow(fourteenthTL); //up

        //junction20
        aTerrainList.get(9).setBackwardListFlow(seventeenthTL);//down
        aTerrainList.get(6).setForwardListFlow(eleventhTL);
        aTerrainList.get(5).setBackwardListFlow(tenthTL);
    }

    /**
     * Create the Terrains - Nodes for Random Map
     */
    public void randomMapCreator() {

    }

    /**
     * Draw the map
     */
    public void drawTheMap(final Draw aDraw) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame = new JFrame("King's Traffic Control Simulation");
                frame.setLayout(new BorderLayout());
//                frame.add(new Buttons(selectMap), BorderLayout.PAGE_END);
                frame.add(new Buttons(selectMap), BorderLayout.PAGE_END);
                frame.add(new Slider(TrafficManagement.this), BorderLayout.EAST);
//                Buttons buttons = new Buttons( selectMap);
                Buttons buttons = new Buttons(selectMap);
                buttons.setPreferredSize(new Dimension(100, 50));
                frame.add(buttons, BorderLayout.SOUTH);
                Slider slider = new Slider(TrafficManagement.this);
                slider.setPreferredSize(new Dimension(250, 0));
                frame.add(slider, BorderLayout.EAST);
                frame.add(aDraw, BorderLayout.CENTER);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.setSize(1400, 700);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
            }
        });
    }

    //this for how fast or how slow the system goes
    public int getTimeGranularity() {
        return timeGranularity;
    }

    /**
     * set the time Granularity
     */
    public void setTimeGranularity(int timeGranularity) {
        this.timeGranularity = timeGranularity;
    }

    public void setTlDelay(int tlDelay) {
        this.tlDelay = tlDelay;
    }

    public int getVehicleListSize() {
        return this.aVehicleList.size();
    }

    public int getPersonListSize() {
        return this.aPersonList.size();
    }

    public ArrayList<Vehicle> getVehicleList() {
        return this.aVehicleList;
    }

    /**
     * Get which map has to be selected
     */
    public int getOption() {
        return option;
    }

    /**
     * Select which map
     */
    public void setOption(int i) {
        option = i;
    }

}