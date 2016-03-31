package Controllers;

import Interfaces.Terrain;
import Interfaces.Vehicle;
import Nodes.StraightRoad;
import Objects.Car;
import Objects.Person;
import Objects.TrafficLights;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class VehicleFlowHelperTest {
    VehicleFlowHelper vehicleFlowHelper;
    Vehicle vehicle;
    Draw aMap;
    int flowDirection;
    TrafficManagement trafficManagement;
    Terrain aTerrain;
    VehicleFlow vfl;
    Person p;

    @Test
    public void testMoveToEnd() throws Exception {

    }

    @Test
    public void testMoveToEndChanged() throws Exception {

    }

    @Test
    public void testTurnCorner() throws Exception {

    }

    @Test
    public void testInCurrentTerrain() throws Exception {

    }

    @Test
    public void testMoveThisVehicleToTheNextCorrectStack() throws Exception {

    }

    @Test
    public void testIsFirstExit() throws Exception {
        vehicleFlowHelper = new VehicleFlowHelper(vehicle, aMap, flowDirection, trafficManagement, aTerrain, vfl);
        assertTrue(vehicleFlowHelper.isFirstExit(VehicleFlowHelper.Direction.RIGHT, VehicleFlowHelper.Direction.UP));
        assertTrue(vehicleFlowHelper.isFirstExit(VehicleFlowHelper.Direction.DOWN, VehicleFlowHelper.Direction.RIGHT));
        assertTrue(vehicleFlowHelper.isFirstExit(VehicleFlowHelper.Direction.LEFT, VehicleFlowHelper.Direction.DOWN));
        assertTrue(vehicleFlowHelper.isFirstExit(VehicleFlowHelper.Direction.UP, VehicleFlowHelper.Direction.LEFT));
        assertFalse(vehicleFlowHelper.isFirstExit(VehicleFlowHelper.Direction.LEFT, VehicleFlowHelper.Direction.LEFT));
        assertFalse(vehicleFlowHelper.isFirstExit(VehicleFlowHelper.Direction.RIGHT, VehicleFlowHelper.Direction.RIGHT));
        assertFalse(vehicleFlowHelper.isFirstExit(VehicleFlowHelper.Direction.UP, VehicleFlowHelper.Direction.UP));
        assertFalse(vehicleFlowHelper.isFirstExit(VehicleFlowHelper.Direction.DOWN, VehicleFlowHelper.Direction.DOWN));
    }

    @Test
    public void testFirstExit() throws Exception {

    }

    @Test
    public void testSecondExit() throws Exception {

    }

    @Test
    public void testSecondExitChanged() throws Exception {

    }

    @Test
    public void testIsThereATrafficLight() throws Exception {
        vehicleFlowHelper = new VehicleFlowHelper(vehicle, aMap, flowDirection, trafficManagement, aTerrain, vfl);
        TrafficLights trafficLights = new TrafficLights(0,0,0,0,0);
        ArrayList<Object> objectArrayList = new ArrayList<>();

        objectArrayList.add(trafficLights);

        //this.aTerrain.getForwardListFlow()
        assertTrue(vehicleFlowHelper.isThereATrafficLight(objectArrayList));
    }

    @Test
    public void testMoveToDestination() throws Exception {

    }

    @Test
    public void testMoveToDestination1() throws Exception {

    }

    @Test
    public void testCheckIfTrafficLightIsGreen() throws Exception {
        vehicleFlowHelper = new VehicleFlowHelper(vehicle, aMap, flowDirection, trafficManagement, aTerrain, vfl);
        TrafficLights trafficLight = new TrafficLights(1,1,3,1,1);
        TrafficLights trafficLight2 = new TrafficLights(1,1,2,1,1);
        //Boolean check = vehicleFlowHelper.checkIfTrafficLightIsGreen(trafficLight);
        Boolean check2 = vehicleFlowHelper.checkIfTrafficLightIsGreen(trafficLight2);
        for (int i = 0; i< 4;i++){
            trafficLight.doRun();
            assertTrue(vehicleFlowHelper.checkIfTrafficLightIsGreen(trafficLight));
        }
        trafficLight.doRun();
        assertFalse(vehicleFlowHelper.checkIfTrafficLightIsGreen(trafficLight));
        assertFalse(check2);
    }

    @Test
    public void testIsThisTerrainBusy() throws Exception {
        flowDirection = 1;
        Vehicle v = new Car(p, 30, 330);
        Vehicle v2 = new Car(p, 30, 330);
        Vehicle v3 = new Car(p, 30, 330);
        aTerrain = new StraightRoad(250, 325, 0, 100, trafficManagement);
        aTerrain.setForwardListFlow(v);
        aTerrain.setForwardListFlow(v2);
        aTerrain.setForwardListFlow(v3);
        vehicleFlowHelper = new VehicleFlowHelper(vehicle, aMap, flowDirection, trafficManagement, aTerrain, vfl);
        assertTrue(vehicleFlowHelper.isThisTerrainBusy());
    }
}