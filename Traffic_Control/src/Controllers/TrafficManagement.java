package Controllers;


import Objects.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class TrafficManagement extends JFrame {

    private volatile ArrayList<Person> aPersonList;
    private volatile ArrayList<Vehicle> aVehicleList;
    private volatile ArrayList<Terrain> aTerrainList;
    private Random rand;
    private JFrame frame;
    private Draw map;
    private ArrayList<Runnable> runnableArrayList = new ArrayList<Runnable>();
    private int timeGranularity;

    private int tlDelay;

    public TrafficManagement() {

        rand = new Random();

        aPersonList = new ArrayList<Person>();
        aVehicleList = new ArrayList<Vehicle>();
        aTerrainList = new ArrayList<Terrain>();
        timeGranularity = 20;
        tlDelay = 3000;

    }

    public ArrayList<Terrain> getTerrainList() {
        return this.aTerrainList;
    }

    public void printNetwork() {

        int counter = 0;
        for (Terrain terrain : this.aTerrainList) {
            System.out.println(counter++);
            System.out.println("Road: " + terrain.toString());
            System.out.println("Forward list: " + terrain.getForwardListFlow());
            System.out.println("Backward list" + terrain.getBackwardListFlow());
        }

    }

    public void deleteVehicle(int number) {

        for (int i = 0; i < number; i++) {

            int random_index = rand.nextInt(this.aVehicleList.size());
            Vehicle v = this.aVehicleList.get(random_index);
            this.aVehicleList.remove(v);

            for (Terrain t : aTerrainList)
                t.removeVehicleFromList(v);
        }

    }

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
                    Car v = new Car(p, 1380, 405);
                    v.setRotate(180);
                    aVehicleList.add(v);
                    this.aTerrainList.get(1).setBackwardListFlow(v);
                }
            }
        }

    }

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
                    Car v = new Car(p, 1380, 405);
                    v.setRotate(180);
                    if (priority_flag == 1)
                        v.setPriority(1);
                    aVehicleList.add(v);
                    this.aTerrainList.get(1).setBackwardListFlow(v);
                }
            }
        }

    }


    public void run() {
        //createPersons(10);
        //createVehicles();

        staticMapCreator();

        //first init the trafficlights to have the firsts position in the Collection
        initializeStaticTrafficLights();

        //init all the vehicles
//        initializeForwardAndBackwardLists();

        //do the graph to know every node their closest nodes
        initializeNeighboursTerrainLists();

        map = new Draw(aTerrainList);


        drawTheMap(map);
        printNetwork();

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
                    Car c = new Car(p, 30, 330);
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

    public void createPersons(int aNumber) {
        for (int i = 0; i < aNumber; i++) {
            aPersonList.add(new Person("Person " + i, rand.nextInt(10), false, null));
        }
    }

    public void initializeForwardAndBackwardLists() {
        for (Vehicle v : this.aVehicleList)
            if (v.get_pos_x() == 30)
                this.aTerrainList.get(0).setForwardListFlow(v);  //insert vehicle in the enter node direction list -->
            else
                this.aTerrainList.get(1).setBackwardListFlow(v);  //insert vehicle in the exit node direction list <--

    }

    public void initializeStaticTrafficLights() {

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

    public void initializeNeighboursTerrainLists() {

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

    public void initializeRandomTrafficLights() {

    }

    public void staticMapCreator() {

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
        aTerrainList.add(new SquareJunction(1180, 325, this, 0));//18
        aTerrainList.add(new SquareJunction(150, 325, this, 0));     //19
        aTerrainList.add(new SquareJunction(815, 590, this, 4));//20
        aTerrainList.add(new SquareJunction(815, 10, this, 3));//21

        aTerrainList.add(new SquareJunction(815, 325, this, 0));//22
    }

    public void randomMapCreator() {

    }

    public void drawTheMap(final Draw aDraw) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame = new JFrame("King's Traffic Control Simulation");
                frame.setLayout(new BorderLayout());
                frame.add(new Buttons(), BorderLayout.PAGE_END);
                frame.add(new Slider(TrafficManagement.this), BorderLayout.EAST);
                Buttons buttons = new Buttons();
                buttons.setPreferredSize(new Dimension(100, 50));
                frame.add(buttons, BorderLayout.SOUTH);
                Slider slider = new Slider(TrafficManagement.this);
                slider.setPreferredSize(new Dimension(250, 0));
                frame.add(slider, BorderLayout.EAST);
                frame.add(aDraw, BorderLayout.CENTER);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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


    public void setTimeGranularity(int timeGranularity) {
        this.timeGranularity = timeGranularity;
    }

    public void setTlDelay(int tlDelay) {
        this.tlDelay = tlDelay;
    }

}