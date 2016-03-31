
package Controllers;

import static org.junit.Assert.*;

import Interfaces.Terrain;
import Objects.Person;
import Controllers.VehicleFlow;
import Objects.TrafficLights;
import Interfaces.Vehicle;
import org.junit.*;

import java.util.ArrayList;

public class TrafficManagementTest {
    ArrayList<Person>  aPersonList;
    ArrayList<Terrain> aTerrainList;
    ArrayList<Vehicle> aVehicleList;
    Terrain aTerrain;
    TrafficManagement trafficManagement;
    ArrayList<Object> forwardListObjects;
    ArrayList<Object> backwardListObjects;

    @Test
    public void testCreateVehicles() throws Exception {
        trafficManagement = new TrafficManagement();
        trafficManagement.createPersons(20);
        trafficManagement.createVehicles();
        int checkHowManyCars = trafficManagement.getVehicleListSize();

        assertEquals(20, checkHowManyCars);
    }

    @Test
    public void testCreatePersons() throws Exception {
        trafficManagement = new TrafficManagement();
        trafficManagement.createPersons(20);
        int checkHowManyPersons = trafficManagement.getPersonListSize();

        assertEquals(20, checkHowManyPersons);
    }

    @Test
    public void testDeleteVehicle() throws Exception {
        trafficManagement = new TrafficManagement();
        trafficManagement.createPersons(20);
        trafficManagement.createVehicles();
        trafficManagement.deleteVehicle(5);
        int checkHowManyCars = trafficManagement.getVehicleListSize();

        assertEquals(15, checkHowManyCars);

    }

    @Test
    public void testFactoryVehicle() throws Exception {
        trafficManagement = new TrafficManagement();
        trafficManagement.run();
        trafficManagement.factoryVehicle(20);
        int checkHowManyCars = trafficManagement.getVehicleListSize();
        assertEquals(20, checkHowManyCars);
    }


    @Test
    public void testInitializeStaticTrafficLights() throws Exception {
        int trafficLightsSize = 0;
        trafficManagement = new TrafficManagement();
        trafficManagement.run();
        aTerrainList = trafficManagement.getTerrainList();
        for(int i = 0;i<aTerrainList.size();i++) {
            backwardListObjects = aTerrainList.get(i).getBackwardListFlow();
            forwardListObjects = aTerrainList.get(i).getForwardListFlow();
            for (Object o : forwardListObjects)
                if (o instanceof TrafficLights)
                    trafficLightsSize++;
            for (Object o : backwardListObjects)
                if (o instanceof TrafficLights)
                    trafficLightsSize++;
        }
        assertNotEquals(0,trafficLightsSize);
    }

    @Test
    public void testInitializeNeighboursTerrainLists() throws Exception {
        trafficManagement = new TrafficManagement();
        trafficManagement.run();
        aTerrainList = trafficManagement.getTerrainList();
        for(int i = 0;i<aTerrainList.size();i++) {
            aTerrain = aTerrainList.get(i);
            int checkSize = aTerrain.getNeighboursTerrainList().size();
            assertNotEquals(0, checkSize);
        }


    }
}