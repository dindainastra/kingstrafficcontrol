package Controllers;

/**
 * Created by Dindainastra on 3/20/2016.
 */
import static org.junit.Assert.*;

import Objects.Person;
import Objects.Terrain;
import Objects.Vehicle;
import org.junit.*;

import java.util.ArrayList;

public class TrafficManagementTest {

    ArrayList<Person>  aPersonList;
    ArrayList<Terrain> aTerrainList;
    ArrayList<Vehicle> aVehicleList;

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
        //assertTrue(TrafficManagement.createPerson() >0);
        /*
            public void createVehicles(){
                for (Person p : aPersonList) {
                    if (!p.isPedestrian()) {
                        if (new Random().nextBoolean())
                            aVehicleList.add(new Car(p, 0,330));
                        else
                            aVehicleList.add(new Car(p, 1350, 405));
                    }
                }
            }*/
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
        //assertArrayEquals(TrafficManagement.createPersons(10),aPersonList.size(10)); //1
        //assertTrue(aNumber<50 && aNumber >0);//2
        //assertFalse(TrafficManagement.createPersons(10)=> TrafficManagement.createVehicles());//3

        /*for(int i=0; i<aNumber; i++){
            aPersonList.add(new Person("Person "+i, rand.nextInt(10), false, null));
        }*/
    }



    /**
     * Testing:
     * 1. if this is 0, then it is not equal 1
     * 2. if this is 1, then it is not equal 0
     */
    @Test
    public void testInitializeForwardAndBackwardLists() throws Exception {
        /*
            public void initializeForwardAndBackwardLists(){
                for (Vehicle v : this.aVehicleList)
                    if (v.get_pos_x()==0)
                        this.aTerrainList.get(0).setForwardListFlow(v);  //insert vehicle in the enter node direction list -->
                    else
                        this.aTerrainList.get(1).setBackwardListFlow(v);  //insert vehicle in the exit node direction list <--

            }*/
    }
}
