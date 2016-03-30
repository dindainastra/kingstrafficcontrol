
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



    /**
     * Testing:
     * 1. if this is 0, then it is not equal 1
     * 2. if this is 1, then it is not equal 0
     */
//    @Test
//    public void testInitializeForwardAndBackwardLists() throws Exception {
//        trafficManagement = new TrafficManagement();
//        aVehicleList = trafficManagement.getVehicleList();
//        for (Vehicle v : this.aVehicleList){
//            if (v.get_pos_x()==0){
//                //this v is in this.aTerrainList.get(0).setForwardListFlow(v);
//            } else {
//                //this.aTerrainList.get(1).setBackwardListFlow(v);
//            }
//        }
//    }

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

        trafficManagement.factoryVehicle(20);
        int checkHowManyCars = trafficManagement.getVehicleListSize();

        assertEquals(20, checkHowManyCars);

    }

    @Test
    public void testFactoryVehicle1() throws Exception {

    }

    @Test
    public void testInitializeStaticTrafficLights() throws Exception {
        trafficManagement = new TrafficManagement();
        aTerrainList = trafficManagement.getTerrainList();

        ArrayList<Object> forwardListObjects = aTerrain.getForwardListFlow();
        ArrayList<Object> backwardListObjects = aTerrain.getBackwardListFlow();
        int trafficLightsSize = 0;
        trafficManagement.initializeStaticTrafficLights();
        for (Terrain aTerrain : aTerrainList) {
            for (Object o : forwardListObjects)
                if (o instanceof TrafficLights)
                    trafficLightsSize++;
            for (Object o : backwardListObjects)
                if (o instanceof TrafficLights)
                    trafficLightsSize++;
        }
        assertNotEquals(0,trafficLightsSize);
    }
}