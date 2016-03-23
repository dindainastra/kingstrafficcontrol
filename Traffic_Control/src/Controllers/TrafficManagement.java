package Controllers;


import Objects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TrafficManagement extends JFrame {

    private volatile ArrayList<Person>  aPersonList;
    private volatile ArrayList<Vehicle> aVehicleList;
    private volatile ArrayList<Terrain> aTerrainList;
    private Random rand;
    private JFrame frame;
    private Draw map;
    private ExecutorService executor;
    private ArrayList<Runnable> runnableArrayList = new ArrayList<Runnable>();

    GridLayout gd=new GridLayout(0,2);

    //buttons
    private JButton pauseButton, playButton, replayButton, stopButton;
    /*private Container contentPane = getContentPane();
    JPanel jPanel = new JPanel();
    JPanel panel = new JPanel();
    GroupLayout jPanelLayout = new GroupLayout(jPanel);
    GroupLayout layout = new GroupLayout(contentPane);
*/

    public TrafficManagement(){

        rand = new Random();

        aPersonList = new ArrayList<Person>();
        aVehicleList = new ArrayList<Vehicle>();
        aTerrainList = new ArrayList<Terrain>();

        //for buttons
        //
        //
        // initComponents();



    }


    private void initComponents() {
        //Create JButton
        replayButton = new JButton();
        pauseButton = new JButton();
        playButton = new JButton();
        stopButton = new JButton();
        //Create JLabel
        //

        URL stopURL = getClass().getResource("../Resources/stop.PNG");
        URL rewindURL = getClass().getResource("../Resources/rewind.PNG");
        URL playURL = getClass().getResource("../Resources/play.PNG");
        URL pauseURL = getClass().getResource("../Resources/pause.PNG");

        //set play, pause, stop and replay button icon and ActionListener
        replayButton.setIcon(new ImageIcon(rewindURL));
        playButton.setIcon(new ImageIcon(playURL));
        pauseButton.setIcon(new ImageIcon(pauseURL));
        stopButton.setIcon(new ImageIcon(stopURL));

        //contentPane.setLayout(new FlowLayout());
        //contentPane.add(replayButton);


        //setLayout(new FlowLayout());

    }

    public ArrayList<Terrain> getTerrainList(){
        return this.aTerrainList;
    }

    public void printNetwork(){

        int counter = 0;
        for(Terrain terrain : this.aTerrainList){
            System.out.println(counter++);
            System.out.println("Road: " + terrain.toString());
            System.out.println("Forward list: " + terrain.getForwardListFlow());
            System.out.println("Backward list" + terrain.getBackwardListFlow());
        }

    }
    
    public void run(){

        createPersons(10);
        createVehicles();

        staticMapCreator();
        //initComponents();

        //first init the trafficlights to have the firsts position in the Collection
        initializeStaticTrafficLights();

        //init all the vehicles
        initializeForwardAndBackwardLists();

        //do the graph to know every node their closest nodes
        initializeNeighboursTerrainLists();
        
        map = new Draw(aTerrainList);


        drawTheMap(map);
        start();
    }

    public void run(int foo){

        createPersons(foo);

        createVehicles();

        randomMapCreator();

        //first init the trafficlights to have the firsts position in the Collection
        initializeRandomTrafficLights();

        //init all the vehicles
        initializeForwardAndBackwardLists();

        map = new Draw(aTerrainList);

        drawTheMap(map);

    }

    public ArrayList<Runnable> getRunnableArrayList() {
        return runnableArrayList;
    }

    public void runExcectutor(Runnable worker){
        executor.execute(worker);
    }

    
    /**
     * Start the flow of cars in the node system
     * For each car in the vehicle list, create a thread for the cars flow
     */
    public void start(){

        for (Terrain t : aTerrainList){
            new Thread(new CarFlow(t,map,1,this)).start();
            new Thread(new CarFlow(t,map,0,this)).start();
        }
        System.out.println("Finished all threads");

    }


    public void createVehicles(){
        for (Person p : aPersonList) {
            if (!p.isPedestrian()) {
                if (new Random().nextBoolean())
                    aVehicleList.add(new Car(p, 0,330));
                else
                    aVehicleList.add(new Car(p, 1350, 405));
            }
        }
    }

    public void createPersons(int aNumber){
        for(int i=0; i<aNumber; i++){
            aPersonList.add(new Person("Person "+i, rand.nextInt(10), false, null));
        }
    }

    public void initializeForwardAndBackwardLists(){
        for (Vehicle v : this.aVehicleList)
            if (v.get_pos_x()==0)
                this.aTerrainList.get(0).setForwardListFlow(v);  //insert vehicle in the enter node direction list -->
            else
                this.aTerrainList.get(1).setBackwardListFlow(v);  //insert vehicle in the exit node direction list <--
    }

    public void initializeStaticTrafficLights(){

        int whichRoad = 0;
        // direction ---->
        aTerrainList.get(whichRoad).setForwardListFlow(new TrafficLights());
        //or
        // direction <----
        aTerrainList.get(whichRoad).setBackwardListFlow(new TrafficLights());

        /*
        the whichRoad variable is actually the road by the static network
        for example if you want to add a Traffic Light for the new StraightRoad(150,325,11,2,2,0,500)
        the whichRoad variable is whichRoad = 6 .
        See Above!


        CAUTION::  None of the SquareJunction should have Traffic Lights.
        CAUTION::  ONLY 1 Traffic Light each Road direction!
         */

    }

    public void initializeNeighboursTerrainLists(){

        //Graph
        this.aTerrainList.get(0).setNeighboursTerrainList(this.aTerrainList.get(23));

        this.aTerrainList.get(1).setNeighboursTerrainList(this.aTerrainList.get(22));

        this.aTerrainList.get(2).setNeighboursTerrainList(this.aTerrainList.get(12));
        this.aTerrainList.get(2).setNeighboursTerrainList(this.aTerrainList.get(5));

        this.aTerrainList.get(3).setNeighboursTerrainList(this.aTerrainList.get(6));
        this.aTerrainList.get(3).setNeighboursTerrainList(this.aTerrainList.get(2));

        this.aTerrainList.get(4).setNeighboursTerrainList(this.aTerrainList.get(13));
        this.aTerrainList.get(4).setNeighboursTerrainList(this.aTerrainList.get(3));

        this.aTerrainList.get(5).setNeighboursTerrainList(this.aTerrainList.get(7));
        this.aTerrainList.get(5).setNeighboursTerrainList(this.aTerrainList.get(4));

        this.aTerrainList.get(6).setNeighboursTerrainList(this.aTerrainList.get(23));
        this.aTerrainList.get(6).setNeighboursTerrainList(this.aTerrainList.get(2));

        this.aTerrainList.get(7).setNeighboursTerrainList(this.aTerrainList.get(22));
        this.aTerrainList.get(7).setNeighboursTerrainList(this.aTerrainList.get(4));

        this.aTerrainList.get(8).setNeighboursTerrainList(this.aTerrainList.get(25));
        this.aTerrainList.get(8).setNeighboursTerrainList(this.aTerrainList.get(20));

        this.aTerrainList.get(9).setNeighboursTerrainList(this.aTerrainList.get(24));
        this.aTerrainList.get(9).setNeighboursTerrainList(this.aTerrainList.get(21));

        this.aTerrainList.get(10).setNeighboursTerrainList(this.aTerrainList.get(19));
        this.aTerrainList.get(10).setNeighboursTerrainList(this.aTerrainList.get(24));

        this.aTerrainList.get(11).setNeighboursTerrainList(this.aTerrainList.get(18));
        this.aTerrainList.get(11).setNeighboursTerrainList(this.aTerrainList.get(25));

        this.aTerrainList.get(12).setNeighboursTerrainList(this.aTerrainList.get(25));
        this.aTerrainList.get(12).setNeighboursTerrainList(this.aTerrainList.get(5));

        this.aTerrainList.get(13).setNeighboursTerrainList(this.aTerrainList.get(3));
        this.aTerrainList.get(13).setNeighboursTerrainList(this.aTerrainList.get(24));

        this.aTerrainList.get(14).setNeighboursTerrainList(this.aTerrainList.get(20));
        this.aTerrainList.get(14).setNeighboursTerrainList(this.aTerrainList.get(23));

        this.aTerrainList.get(15).setNeighboursTerrainList(this.aTerrainList.get(23));
        this.aTerrainList.get(15).setNeighboursTerrainList(this.aTerrainList.get(21));

        this.aTerrainList.get(16).setNeighboursTerrainList(this.aTerrainList.get(22));
        this.aTerrainList.get(16).setNeighboursTerrainList(this.aTerrainList.get(19));

        this.aTerrainList.get(17).setNeighboursTerrainList(this.aTerrainList.get(18));
        this.aTerrainList.get(17).setNeighboursTerrainList(this.aTerrainList.get(22));

        this.aTerrainList.get(18).setNeighboursTerrainList(this.aTerrainList.get(11));
        this.aTerrainList.get(18).setNeighboursTerrainList(this.aTerrainList.get(17));

        this.aTerrainList.get(19).setNeighboursTerrainList(this.aTerrainList.get(16));
        this.aTerrainList.get(19).setNeighboursTerrainList(this.aTerrainList.get(10));

        this.aTerrainList.get(20).setNeighboursTerrainList(this.aTerrainList.get(8));
        this.aTerrainList.get(20).setNeighboursTerrainList(this.aTerrainList.get(14));

        this.aTerrainList.get(21).setNeighboursTerrainList(this.aTerrainList.get(9));
        this.aTerrainList.get(21).setNeighboursTerrainList(this.aTerrainList.get(15));

        this.aTerrainList.get(22).setNeighboursTerrainList(this.aTerrainList.get(1));
        this.aTerrainList.get(22).setNeighboursTerrainList(this.aTerrainList.get(7));
        this.aTerrainList.get(22).setNeighboursTerrainList(this.aTerrainList.get(17));
        this.aTerrainList.get(22).setNeighboursTerrainList(this.aTerrainList.get(16));

        this.aTerrainList.get(23).setNeighboursTerrainList(this.aTerrainList.get(6));
        this.aTerrainList.get(23).setNeighboursTerrainList(this.aTerrainList.get(0));
        this.aTerrainList.get(23).setNeighboursTerrainList(this.aTerrainList.get(14));
        this.aTerrainList.get(23).setNeighboursTerrainList(this.aTerrainList.get(15));

        this.aTerrainList.get(24).setNeighboursTerrainList(this.aTerrainList.get(10));
        this.aTerrainList.get(24).setNeighboursTerrainList(this.aTerrainList.get(9));
        this.aTerrainList.get(24).setNeighboursTerrainList(this.aTerrainList.get(13));

        this.aTerrainList.get(25).setNeighboursTerrainList(this.aTerrainList.get(11));
        this.aTerrainList.get(25).setNeighboursTerrainList(this.aTerrainList.get(12));
        this.aTerrainList.get(25).setNeighboursTerrainList(this.aTerrainList.get(8));

    }
    
    public void initializeRandomTrafficLights(){

    }

    public void staticMapCreator(){

        //create FIRSTLY the entry and exit roads
        aTerrainList.add(new StraightRoad(10,325,01,2,2,0,40,this));//entry road    //0
        aTerrainList.add(new StraightRoad(1180,325,10,1,3,0,170,this));//exit road  //1

        //next, create the rest map

        //add roundabout
        aTerrainList.add(new CornerRoad(640,250,90,1,this));                         //2
        aTerrainList.add(new CornerRoad(640,250,180,1,this));                        //3
        aTerrainList.add(new CornerRoad(640,250,270,1,this));                        //4
        aTerrainList.add(new CornerRoad(640,250,360,1,this));                        //5

        //add horizontal road
        //Added another traffic light color parameter to SRoad
        aTerrainList.add(new StraightRoad(150,325,11,2,2,0,500,this));               //6   <-----
        aTerrainList.add(new StraightRoad(880,325,11,1,3,0,200,this));
        aTerrainList.add(new StraightRoad(150,10,01,2,2,0,565,this));
        aTerrainList.add(new StraightRoad(150,590,01,2,2,0,565,this));
        aTerrainList.add(new StraightRoad(815,590,10,2,2,0,265,this));
        aTerrainList.add(new StraightRoad(815,10,10,2,2,0,265,this));

        //add vertical roads
        aTerrainList.add(new StraightRoad(815,110,11,1,1,90,150,this));              //12
        aTerrainList.add(new StraightRoad(815,490,11,2,2,90,101,this));
        aTerrainList.add(new StraightRoad(150,110,01,2,2,90,215,this));              //14
        aTerrainList.add(new StraightRoad(150,425,10,2,2,90,165,this));
        aTerrainList.add(new StraightRoad(1180,425,10,2,2,90,165,this));
        aTerrainList.add(new StraightRoad(1180,110,01,2,2,90,215,this));

        //add curved roads
        aTerrainList.add(new CornerRoad(980,10,360,0,this));                         //18
        aTerrainList.add(new CornerRoad(980,490,270,0,this));                        //19
        aTerrainList.add(new CornerRoad(50,10,90,0,this));                           //20
        aTerrainList.add(new CornerRoad(50,490,180,0,this));                         //21
        //aTerrainList.add(new CornerRoad(300,75,90));

        //add junctions
        aTerrainList.add(new SquareJunction(1080,325,this));
        aTerrainList.add(new SquareJunction(50,325,this));                           //23
        aTerrainList.add(new SquareJunction(715,590,this));
        aTerrainList.add(new SquareJunction(715,10,this));

    }

    public void randomMapCreator(){

    }

    public void drawTheMap(final Draw aDraw){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame = new JFrame();

                frame.setLayout(new BorderLayout());
                frame.add(new Buttons(), BorderLayout.PAGE_END);
                frame.add(new Slider(), BorderLayout.EAST);
                frame.add(aDraw, BorderLayout.CENTER);

                //frame.add();
                //frame.setLayout(gd);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.setSize(1500, 1000);
            }
        });
    }

    public Terrain yourTerrain(Car car){


        return null;
    }
}
