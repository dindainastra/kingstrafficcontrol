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
    private int timeGranularity;



    private int tlDelay;
    private int sliderPersons;


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
        timeGranularity = 50;
        tlDelay = 3000;
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
        printNetwork();
//        System.exit(1);
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

        //junction25
//        TrafficLights ninthTL = new TrafficLights(715,10,3,1,0,getTlDelay());
//        TrafficLights twelfthTL = new TrafficLights(815,60,3,9,0,getTlDelay());
//        TrafficLights eighteenthTL = new TrafficLights(765,110,3,5,90,getTlDelay());
        TrafficLights ninthTL = new TrafficLights(815,10,3,1,0,getTlDelay());
        TrafficLights twelfthTL = new TrafficLights(915,60,3,9,0,getTlDelay());
        TrafficLights eighteenthTL = new TrafficLights(865,110,3,5,90,getTlDelay());

        //junction 23
        TrafficLights thirdTL = new TrafficLights(250,325,4, 1,90,getTlDelay());
        TrafficLights firstTL = new TrafficLights(250,375,4, 13,0,getTlDelay());
        TrafficLights fourthTL = new TrafficLights(200,425, 4, 9,90,getTlDelay());
        TrafficLights secondTL = new TrafficLights(150,325,4, 5,0,getTlDelay());

        //roundabout
        TrafficLights fifteenTL = new TrafficLights(915,260,4, 1,90,getTlDelay());
        TrafficLights eighthTL = new TrafficLights(980,375,4, 13,0,getTlDelay());
        TrafficLights sixteenthTL = new TrafficLights(865,490,4 ,9,90,getTlDelay());
        TrafficLights fifthTL = new TrafficLights(750,325,4, 5,0,getTlDelay());


        //junction22
        TrafficLights thirteenthTL = new TrafficLights(1280,325,4, 1,90,getTlDelay());
        TrafficLights seventhTL = new TrafficLights(1280,375,4, 13,0,getTlDelay());
        TrafficLights fourteenthTL = new TrafficLights(1230,425,4,9,90,getTlDelay());
        TrafficLights sixthTL = new TrafficLights(1180,325,4,5,0,getTlDelay());


        //junction24

        TrafficLights seventeenthTL = new TrafficLights(915,590,3,1,90,getTlDelay());
        TrafficLights eleventhTL = new TrafficLights(915,640,3,9,0,getTlDelay());
        TrafficLights tenthTL = new TrafficLights(815,590,3,5,0,getTlDelay());

        // direction ---->

        //junction25
        aTerrainList.get(8).setForwardListFlow(ninthTL);
        aTerrainList.get(11).setForwardListFlow(twelfthTL); //supposed to be backward
        aTerrainList.get(12).setForwardListFlow(eighteenthTL);//up supposed to be backward

        //junction23
        aTerrainList.get(14).setForwardListFlow(thirdTL); //down supposed to be backward
        aTerrainList.get(6).setBackwardListFlow(firstTL); //supposed to be backward
        aTerrainList.get(15).setForwardListFlow(fourthTL);
        aTerrainList.get(0).setForwardListFlow(secondTL);

        //roundabout
        aTerrainList.get(12).setForwardListFlow(fifteenTL);//down
        aTerrainList.get(7).setBackwardListFlow(eighthTL); //supposed to be backward
        aTerrainList.get(13).setForwardListFlow(sixteenthTL);//up
        aTerrainList.get(6).setForwardListFlow(fifthTL);

        //junction22
        aTerrainList.get(7).setForwardListFlow(sixthTL);
        aTerrainList.get(1).setForwardListFlow(seventhTL);
        aTerrainList.get(17).setForwardListFlow(thirteenthTL); //down
        aTerrainList.get(16).setForwardListFlow(fourteenthTL); //up

        //junction24
        aTerrainList.get(13).setBackwardListFlow(seventeenthTL);//down
        aTerrainList.get(10).setBackwardListFlow(eleventhTL);
        aTerrainList.get(9).setForwardListFlow(tenthTL);

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
        //aTerrainList.add(new StraightRoad(10,325,0,40,this));//entry road    //0
        //aTerrainList.add(new StraightRoad(1180,325,0,170,this));//exit road  //1
        aTerrainList.add(new StraightRoad(50,325,0,100,this));//entry road    //0
        aTerrainList.add(new StraightRoad(1280,325,0,100,this));//exit road  //1

        //next, create the rest map

        //add roundabout
        aTerrainList.add(new CornerRoad(740,250,90,1,this));                         //2
        aTerrainList.add(new CornerRoad(740,250,180,1,this));                        //3
        aTerrainList.add(new CornerRoad(740,250,270,1,this));                        //4
        aTerrainList.add(new CornerRoad(740,250,360,1,this));                        //5

        //add horizontal road
        //Added another traffic light color parameter to SRoad
        aTerrainList.add(new StraightRoad(250,325,0,500,this));               //6   <-----
        aTerrainList.add(new StraightRoad(980,325,0,200,this));
        aTerrainList.add(new StraightRoad(250,10,0,565,this));
        aTerrainList.add(new StraightRoad(250,590,0,565,this));
        aTerrainList.add(new StraightRoad(915,590,0,265,this));
        aTerrainList.add(new StraightRoad(915,10,0,265,this));

        //add vertical roads
        aTerrainList.add(new StraightRoad(815,110,90,150,this));              //12
        aTerrainList.add(new StraightRoad(815,490,90,101,this));
        aTerrainList.add(new StraightRoad(150,110,90,215,this));              //14
        aTerrainList.add(new StraightRoad(150,425,90,165,this));
        aTerrainList.add(new StraightRoad(1180,425,90,165,this));
        aTerrainList.add(new StraightRoad(1180,110,90,215,this));

        //add curved roads
        aTerrainList.add(new CornerRoad(1080,10,360,0,this));                         //18
        aTerrainList.add(new CornerRoad(1080,490,270,0,this));                        //19
        aTerrainList.add(new CornerRoad(150,10,90,0,this));                           //20
        aTerrainList.add(new CornerRoad(150,490,180,0,this));                         //21
        //aTerrainList.add(new CornerRoad(300,75,90));

        //add junctions (0,0,4,3: to close the square junctions)
        aTerrainList.add(new SquareJunction(1180,325,this,0));
        aTerrainList.add(new SquareJunction(150,325,this,0));                          //23
        aTerrainList.add(new SquareJunction(815,590,this,4));
        aTerrainList.add(new SquareJunction(815,10,this,3));
//        aTerrainList.add(new SquareJunction(50,325,this,0));                          //23
//        aTerrainList.add(new SquareJunction(715,590,this,4));
//        aTerrainList.add(new SquareJunction(715,10,this,3));

    }

    public void randomMapCreator(){

    }

    public void drawTheMap(final Draw aDraw){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame = new JFrame();
                frame.setLayout(new BorderLayout());

                frame.add(new Buttons(), BorderLayout.PAGE_END);
                frame.add(new Slider(TrafficManagement.this), BorderLayout.EAST);

                //frame.add(new Buttons(), BorderLayout.PAGE_END);
                Buttons buttons=new Buttons();
                buttons.setPreferredSize(new Dimension(100,50));
                frame.add(buttons,BorderLayout.SOUTH);
                Slider slider=new Slider(TrafficManagement.this);
                slider.setPreferredSize(new Dimension(250,0));
                frame.add(slider,BorderLayout.EAST);
                //frame.pack();
                frame.add(aDraw, BorderLayout.CENTER);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                /*frame.setSize(1500, 1000);*/
                frame.setSize(1400, 700);
                frame.setResizable ( false );
            }
        });
    }

    public Terrain yourTerrain(Car car){


        return null;
    }

    //this for how fast or how slow the system goes
    public int getTimeGranularity() {

        return timeGranularity;
    }


    public void setTimeGranularity(int timeGranularity) {

        this.timeGranularity = timeGranularity;
    }

    public int getTlDelay() {
        return tlDelay;
    }

    public void setTlDelay(int tlDelay) {
        this.tlDelay = tlDelay;
    }
    public int getSliderPersons() {
        return sliderPersons;
    }

    public void setSliderPersons(int sliderPersons) {
        this.sliderPersons = sliderPersons;
    }
}