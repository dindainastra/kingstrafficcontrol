package Controllers;

import Objects.Draw;
import Objects.Terrain;
import Objects.TrafficLights;
import Objects.Vehicle;

import javax.swing.*;
import java.util.ArrayList;

public class VehicleFlow implements Runnable {

    private final int delay = 30;
    private Draw map;
    private Terrain aTerrain;
    private int flowDirection;   // 0 <---   and 1 ---->
    private volatile Timer timer = null;
    private ArrayList<Object> currentFlowList;
    private TrafficManagement trafficManagement;
    private String threadName;

    public VehicleFlow(Terrain t, Draw map, int direction, TrafficManagement trafficManagement) {
        this.aTerrain = t;
        this.map = map;
        this.flowDirection = direction;
        timer = new Timer(delay, null);

        if (flowDirection == 1) {
            currentFlowList = this.aTerrain.getForwardListFlow();
        } else {
            currentFlowList = this.aTerrain.getBackwardListFlow();
        }

        this.trafficManagement = trafficManagement;

    }

    public void deleteFromCurrentList(Object item) {
        currentFlowList.remove(item);
    }


    private void startTraffic(Object o) {
        TrafficLights tLOne = (TrafficLights) o;
        //tLOne.run();
        map.repaint();
        Thread t = new Thread(tLOne);
        t.start();
    }


    public void run() {

        for (Object o : this.currentFlowList) {
            if (o instanceof TrafficLights) {
                startTraffic(o);
            }
        }

        for (; ; ) {

            map.repaint();
            for (Object o : this.currentFlowList) {
                if (o instanceof Vehicle) {
                    if (!((Vehicle) o).getLock()) {
                        ((Vehicle) o).setLock(true);
                        new Thread(new VehicleFlowHelper((Vehicle) o, map, flowDirection, trafficManagement, aTerrain, this)).start();
                    }
                }
            }

//            try {
//                startFlow();
//                Thread.sleep(trafficManagement.getTimeGranularity());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

        }

    }

}