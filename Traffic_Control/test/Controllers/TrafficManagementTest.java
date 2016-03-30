
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

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Test:
     * 1. this thread is not equals to other thread
     * 2. aTerrainList is not empty
     * 3. map is initialized(?)
     * 4.
     * @throws Exception
     */
    @Test
    public void testStart() throws Exception {
        /*for (Terrain t : aTerrainList){
               new Thread(new CarFlow(t,map,1,this)).start();
               new Thread(new CarFlow(t,map,0,this)).start(); */

    }


    /**
     *
     * Test:
     * 1. Vehicle is never zero (minimum)
     * 2. Vehicles in simulator is less than or equals to persons
     * 3. The location of the car is either 0, 330 or 1350, 405
     * 4. The person in the car is not pedestrian
     * */

    @Test
    public void testCreateVehicles() throws Exception {
        //call the constructor
        //trafficManagement
        trafficManagement = new TrafficManagement();
        trafficManagement.createPersons(20);
        trafficManagement.createVehicles();
        int checkHowManyCars = trafficManagement.getVehicleListSize();

        assertEquals(20, checkHowManyCars);
    }

    /**
     * Testing:
     * 1. The array size
     * 2. Minimum sizes of persons
     * 3. Persons in simulator is more than or equals to vehicles
     * @throws Exception
     */
    @Test
    public void testCreatePersons() throws Exception {

        //call the constructor
        trafficManagement = new TrafficManagement();
        trafficManagement.createPersons(20);
        int checkHowManyPersons = trafficManagement.getPersonListSize();

        assertEquals(20, checkHowManyPersons);
    }

    @Test
    public void testDeleteVehicle() throws Exception {

        //call the constructor
        //trafficManagement
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
        //aTerrainList = trafficManagement.getTerrainList();
        trafficManagement.factoryVehicle(20);
        int checkHowManyCars = trafficManagement.getVehicleListSize();
        assertEquals(20, checkHowManyCars);
    }

    @Test
    public void testFactoryVehicle1() throws Exception {

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
//        trafficManagement.initializeNeighboursTerrainLists();
        for(int i = 0;i<aTerrainList.size();i++) {
            aTerrain = aTerrainList.get(i);
            int checkSize = aTerrain.getNeighboursTerrainList().size();
            assertNotEquals(0, checkSize);
        }


    }
}