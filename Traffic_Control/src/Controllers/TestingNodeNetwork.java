package Controllers;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Objects.CRoad;
import Objects.Car;
import Objects.Display;
import Objects.Draw;
import Objects.Person;
import Objects.SRoad;
import Objects.SquareJunction;
import Objects.Terrain;
import Objects.TrafficLights;
import Objects.Vehicle;

public class TestingNodeNetwork {

	private static NodeManager  nodeManager;
	private static Draw aDraw;
	private static ArrayList<Vehicle> aVehicleList;
	private static ArrayList<Person> aPersonList;
	private static ArrayList<Node> aNodeList;
	private static ArrayList<Terrain> aTerrainList;
	private static JFrame frame;
	
	public static void main(String[] args) {
		
		aPersonList = new ArrayList<Person>();
		aVehicleList = new ArrayList<Vehicle>();
		aNodeList = new ArrayList<Node>();
		aTerrainList = new ArrayList<Terrain>();
		
		//Declarations of the Network
		
		//add Persons
		aPersonList.add(new Person("Person1", 10, false));
		/*aPersonList.add(new Person("Person2", 10, false));
		aPersonList.add(new Person("Person3", 10, false));
		aPersonList.add(new Person("Person4", 10, false));
		aPersonList.add(new Person("Person5", 10, false));
		aPersonList.add(new Person("Person6", 10, false));
		*/
		//Set Person to every Car and add the car in the road
		for (Person p : aPersonList)
			if (!p.isPedestrian()){
                                aVehicleList.add(new Car(p,100,230));
                        }
		//add horizontal roads
/*=======
			if (!p.isPedestrian())
				aVehicleList.add(new Car(p,100,225));
		
				aVehicleList.add(new Car(p,0,0));
		
>>>>>>> 5ca9dc67e409273a37fe153a7da3f242c041c645*/
		aTerrainList.add(new SRoad(100,225,00,2,0));
		aTerrainList.add(new SRoad(200,225,01,3,0));
		aTerrainList.add(new SRoad(350,225,10,3,0));
		aTerrainList.add(new SRoad(450,225,01,2,0));
		aTerrainList.add(new SRoad(350,75,00,2,0));
		aTerrainList.add(new SRoad(450,75,00,2,0));
        
		//add vertical roads
		aTerrainList.add(new SRoad(600,125,11,1,90)); 
		aTerrainList.add(new SRoad(350,125,01,2,90));
		aTerrainList.add(new SRoad(300,325,01,2,90));
        
        //add corner roads
		aTerrainList.add(new CRoad(500,75,360));
		aTerrainList.add(new CRoad(500,175,270));
		aTerrainList.add(new CRoad(250,225,270));
		aTerrainList.add(new CRoad(250,275,90));
        
		aTerrainList.add(new CRoad(300,75,90));
        
        //add roundabout
		aTerrainList.add(new CRoad(225,400,90));
		aTerrainList.add(new CRoad(225,400,180));
		aTerrainList.add(new CRoad(225,400,270));
		aTerrainList.add(new CRoad(225,400,360));
        
		//add junctions
		aTerrainList.add(new SquareJunction(300,225));
                
//        //Draw traffic
//        cars.add(new Car(p,210,230));
//        motorbikes.add(new Motorbike(250,255));  
//        lorries.add(new Lorry(240,230));
//        bikes.add(new Bike(170,230));
//        emergencies.add(new Emergency(120,230));
		
		
		//add nodes
		aNodeList.add(new Node("START",0,0));
		aNodeList.add(new Node("First Node",0.8,10.0));
		aNodeList.add(new Node("Second Node",0.5,20.0));
		aNodeList.add(new Node("Third Node",0.5,20.0));
		aNodeList.add(new Node("END",0,20.0));
		
		aDraw = new Draw(aVehicleList,aTerrainList);
		
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame = new JFrame();
                frame.add(aDraw);
                //frame.add(R)
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.setSize(700, 700);
            }
        });		
		
		//create the node managers who has all the informations about the nodes, and he will change the vehicles from node to node upon each car request
		System.out.println("Debug Draw: "+aDraw);
		nodeManager = new NodeManager(aDraw);
		//nodeManager.setMap(aDraw);
		
		nodeManager.createTheNetwork(aNodeList,aVehicleList,aTerrainList);
		
		System.out.println("My network is like that:");
		nodeManager.printMyNetwork();
		nodeManager.vehicleFlow_version1();

		nodeManager.start();
		
		nodeManager.vehicleFlow_version1();
		
//		nodeManager.resetNodeNetwork(); there is a bug now, but whatever

		nodeManager.createTheNetwork(aNodeList,aVehicleList,aTerrainList);
		
		for (int i=0;i<10;i++)
			nodeManager.addANewVehicleToTheNetwork(new Car(new Person("1",10,false),100,i++));
		//random vehicle creation by time (RANDOM)
	}

	
}