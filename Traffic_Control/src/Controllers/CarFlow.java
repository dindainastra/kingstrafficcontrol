package Controllers;

import Objects.Draw;
import Objects.Terrain;
import Objects.Vehicle;
import Objects.TrafficLights;
import Objects.Car;
import Objects.StraightRoad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class CarFlow implements Runnable {

    private Draw map;
    private Terrain aTerrain;
    private int flowDirection;   // 0 <---   and 1 ---->
    private Timer timer = null;
    private final int delay = 2000;

    public CarFlow(Terrain t, Draw map, int direction){
        this.aTerrain = t;
        this.map = map;
        this.flowDirection = direction;
        timer = new Timer(delay, null);
    }

    /**
     * Resume the cars flow through the system
     * Repaint the map whilst moving
     */
    private void startFlow(){
        try {
            //while (this.isThisTerrainBusy()){}

            if (this.flowDirection == 1) {
                // here we have 2 options.
                // Option 1: the Thread.Carflow will create a new thread-worker for each vehicle and the Vehicle will
                // print itself.
                // Option 2: the Thread.Carflow will print each vehicle one by one and the Thread.Carflow will paint
                // the car.
                // I will add a pseudocode the the 2nd option

//                if (isThereATrafficLight(this.aTerrain.getForwardListFlow())) {
//
////                    while (checkIfTrafficLightIsGreen(((TrafficLights) aTerrain.getForwardListFlow().get(0)))) {
////
////                    }
//                }

                ArrayList<Object> list = this.aTerrain.getForwardListFlow();
                for (Object o : list) {
                    if (o instanceof Vehicle) {
                        //move should probably have arguments.
                        //moves also should check if there is a stopped vehicle in front of the current moving vehicle. If it is, just stop
                        // break the moving.
                        System.out.println("Runner");
                        final Car c = (Car)o;
                        System.out.println("Move car. - "+c.getPerson().getName());

                        //int len = this.aTerrain.getLenght();
                        final StraightRoad sRoad = (StraightRoad)this.aTerrain;
                        final int roadSteps = this.aTerrain.getLenght() / c.getLength();

                        //c.move(sRoad.getxStart(), sRoad.getYStart(), roadSteps);

//                        timer.addActionListener(new ActionListener() {
//                            @Override
//                            public void actionPerformed(ActionEvent e) {
//                                c.move();
//                                //SET DELAY BEFORE NEXT CAR STARTS
//                                map.repaint();
//
//                                //Stop timer when the car is JUST past the end of the road
//                                if(sRoad.getxStart()+sRoad.getLenght() == c.get_pos_x() ) {
//                                    System.out.println("Timer stopped.");
//                                    timer.stop();
//                                }
//                            }
//                        });
//                        timer.start();
                    } else {
                        System.out.println("Traffic Light!!!!!!!!!!!");
                        //Start traffic
                        final TrafficLights tLOne = (TrafficLights)o;
//                        Thread t = new Thread(tLOne);
//                        t.start();

                        timer.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                tLOne.change();
                                //SET DELAY BEFORE NEXT CAR STARTS
                                map.repaint();

                            }
                        });
                        timer.start();
                    }
                }
            } else {
                // same as above
//                    if (isThereATrafficLight(this.aTerrain.getBackwardListFlow())) {
//                        while (checkIfTrafficLightIsGreen(((TrafficLights) aTerrain.getBackwardListFlow().get(0)))) {
//                        }
//                    }

                //for (Object o : this.aTerrain.getBackwardListFlow()) {
//                        if (o instanceof Vehicle) {
//                            ((Vehicle) o).move();
//                        }
                //}
            }
//            map.revalidate();
//            map.repaint();
//            Thread.sleep(1000);

        } catch(Exception e) {
            System.out.println("Error: "+e.getLocalizedMessage());
        }
    }

    public boolean isThereATrafficLight(ArrayList<Object> objectArrayList){

        for (Object o : objectArrayList)
            if (o instanceof TrafficLights)
                return true;
        return false;

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

//        if (trafficLight.checkGreen())
//            return true;
        return false;

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

    public void startTraffic(){
        final TrafficLights tLOne = (TrafficLights)this.aTerrain.getForwardListFlow().get(0);
        Thread t = new Thread(tLOne);
        t.start();

    }

    public void run(){
        startFlow();

    }
}