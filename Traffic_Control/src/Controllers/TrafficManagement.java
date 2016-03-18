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

public class TrafficManagement extends JFrame {

    private ArrayList<Person>  aPersonList;
    private ArrayList<Vehicle> aVehicleList;
    private ArrayList<Terrain> aTerrainList;
    private Random rand;
    private JFrame frame;
    private Draw map;

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

        run();

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


    public void run(){

        createPersons(1);

        createVehicles();

        staticMapCreator();
        //initComponents();

        //first init the trafficlights to have the firsts position in the Collection
        initializeStaticTrafficLights();

        //init all the vehicles
        initializeForwardAndBackwardLists();

        map = new Draw(aTerrainList);


        drawTheMap(map);
        start();
    }

    public void run(int foo){

        createPersons(20);

        createVehicles();

        randomMapCreator();

        //first init the trafficlights to have the firsts position in the Collection
        initializeRandomTrafficLights();

        //init all the vehicles
        initializeForwardAndBackwardLists();

        map = new Draw(aTerrainList);

        drawTheMap(map);

    }

    /**
     * Start the flow of cars in the node system
     * For each car in the vehicle list, create a thread for the cars flow
     */
    public void start(){
        for(Terrain t : aTerrainList){
            try {
                if (t instanceof StraightRoad){
                    //Create thread for the ---> Direction of the Road
                    new Thread(new CarFlow((StraightRoad) t, map, 1), "Thread-"+(t.getClass().toString())).start();
                    //Create thread for the <--- Direction of the Road
                    new Thread(new CarFlow((StraightRoad) t, map, 0), "Thread-"+(t.getClass().toString())).start();
                }
                // (MAYBE) here we need 2 more threads.  one for the upper direction and one for the down direction.
                else if (t instanceof SquareJunction){
                    new Thread(new CarFlow((SquareJunction) t, map, 1), "Thread-"+(t.getClass().toString())).start();
                    new Thread(new CarFlow((SquareJunction) t, map, 0), "Thread-"+(t.getClass().toString())).start();
                }else if (t instanceof CornerRoad){
                    new Thread(new CarFlow((CornerRoad) t, map, 1), "Thread-"+(t.getClass().toString())).start();
                    new Thread(new CarFlow((CornerRoad) t, map, 0), "Thread-"+(t.getClass().toString())).start();
                }
               // Thread.sleep(1);
            } 
//            catch(InterruptedException e) {
//                System.out.println("Error with threads: "+e.getLocalizedMessage());
//            } 
            catch(NullPointerException e){
                System.out.println("Error: "+e.getLocalizedMessage());
            }
        }
    }


    public void createVehicles(){
        for (Person p : aPersonList) {
//            if (!p.isPedestrian()) {
//                if (new Random().nextBoolean())
                    aVehicleList.add(new Car(p, 150, 110));
//                else
//                    aVehicleList.add(new Car(p, 1180, 330));
//            }
        }
    }

    public void createPersons(int aNumber){
        for(int i=0; i<aNumber; i++){
            aPersonList.add(new Person("Person "+i, rand.nextInt(25), false));
        }
    }

    public void initializeForwardAndBackwardLists(){
        for (Vehicle v : this.aVehicleList) {
            this.aTerrainList.get(14).setForwardListFlow(v);  //insert vehicle in the enter node direction list -->

//            if (v.get_pos_x()==0) {
//                this.aTerrainList.get(0).setForwardListFlow(v);  //insert vehicle in the enter node direction list -->
//            }else {
//                this.aTerrainList.get(1).setBackwardListFlow(v);  //insert vehicle in the exit node direction list <--
//            }
        }
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

    public void initializeRandomTrafficLights(){

    }

    public void staticMapCreator(){

        //create FIRSTLY the entry and exit roads
        aTerrainList.add(new StraightRoad(10,325,01,2,2,0,40));//entry road    //0
        aTerrainList.add(new StraightRoad(1180,325,10,1,3,0,170));//exit road  //1

        //next, create the rest map

        //add roundabout
        aTerrainList.add(new CornerRoad(640,250,90,1));                         //2
        aTerrainList.add(new CornerRoad(640,250,180,1));                        //3
        aTerrainList.add(new CornerRoad(640,250,270,1));                        //4
        aTerrainList.add(new CornerRoad(640,250,360,1));                        //5

        //add horizontal road
        //Added another traffic light color parameter to SRoad
        aTerrainList.add(new StraightRoad(150,325,11,2,2,0,500));               //6   <-----
        aTerrainList.add(new StraightRoad(880,325,11,1,3,0,200));
        aTerrainList.add(new StraightRoad(150,10,01,2,2,0,565));
        aTerrainList.add(new StraightRoad(150,590,01,2,2,0,565));
        aTerrainList.add(new StraightRoad(815,590,10,2,2,0,265));
        aTerrainList.add(new StraightRoad(815,10,10,2,2,0,265));

        //add vertical roads
        aTerrainList.add(new StraightRoad(815,110,11,1,1,90,150));              //12
        aTerrainList.add(new StraightRoad(815,490,11,2,2,90,101));
        aTerrainList.add(new StraightRoad(150,110,01,2,2,90,215));              //14
        aTerrainList.add(new StraightRoad(150,425,10,2,2,90,165));
        aTerrainList.add(new StraightRoad(1180,425,10,2,2,90,165));
        aTerrainList.add(new StraightRoad(1180,110,01,2,2,90,215));

        //add curved roads
        aTerrainList.add(new CornerRoad(980,10,360,0));
        aTerrainList.add(new CornerRoad(980,490,270,0));
        aTerrainList.add(new CornerRoad(50,10,90,0));
        aTerrainList.add(new CornerRoad(50,490,180,0));
        //aTerrainList.add(new CornerRoad(300,75,90));

        //add junctions
        aTerrainList.add(new SquareJunction(1080,325));
        aTerrainList.add(new SquareJunction(50,325));
        aTerrainList.add(new SquareJunction(715,590));
        aTerrainList.add(new SquareJunction(715,10));

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

}
