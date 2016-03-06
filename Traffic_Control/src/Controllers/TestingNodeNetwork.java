package Controllers;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
//added library for Stopwatch
//import org.apache.commons.lang.time.StopWatch;

import Objects.CornerRoad;
import Objects.Car;
import Objects.Draw;
import Objects.Person;
import Objects.StraightRoad;
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
	private static Random rand;

	public static void main(String[] args) {
		rand = new Random();
		aPersonList = new ArrayList<Person>();
		aVehicleList = new ArrayList<Vehicle>();
		aNodeList = new ArrayList<Node>();
		aTerrainList = new ArrayList<Terrain>();

		//Declarations of the Network
		//add Persons  new Person("Name", politenesslevel, isPedestrian)
		for(int i=0; i<=2; i++){
			aPersonList.add(new Person("Person"+i, rand.nextInt(10), false));
		}

		//Set Person to every Car and add the car in the road
		for (Person p : aPersonList) {
			if (!p.isPedestrian()) {
				aVehicleList.add(new Car(p, 80, 225));
			}
		}

		//add horizontal road
		//Added another traffic light color parameter to SRoad
		//aTerrainList.add(new SRoad(getposx(),getposy(),gettraffic(),gettrafficcolor1(),gettrafficcolor2(),getrotation());
		aTerrainList.add(new StraightRoad(100,225,00,2,2,0)); //1

		aTerrainList.add(new StraightRoad(200,225,01,1,3,0)); //2
		aTerrainList.add(new StraightRoad(350,225,10,1,3,0)); //3
		aTerrainList.add(new StraightRoad(450,225,01,2,2,0)); //4
		aTerrainList.add(new StraightRoad(350,75,00,2,2,0));  //5
		aTerrainList.add(new StraightRoad(450,75,00,2,2,0));  //6
        
		//add vertical roads
		aTerrainList.add(new StraightRoad(600,125,11,1,1,90)); //7
		aTerrainList.add(new StraightRoad(350,125,01,2,2,90)); //8
		aTerrainList.add(new StraightRoad(300,325,01,2,2,90)); //9
        
        //add curved roads
		aTerrainList.add(new CornerRoad(500,75,360)); //10
		aTerrainList.add(new CornerRoad(500,175,270)); //11
		aTerrainList.add(new CornerRoad(250,225,270)); //12
		aTerrainList.add(new CornerRoad(250,275,90)); //13
        
		aTerrainList.add(new CornerRoad(300,75,90)); //14
        
        //add roundabout
		aTerrainList.add(new CornerRoad(225,400,90)); //15
		aTerrainList.add(new CornerRoad(225,400,180)); //16
		aTerrainList.add(new CornerRoad(225,400,270)); //17
		aTerrainList.add(new CornerRoad(225,400,360)); //18
        
//		//add junctions
		aTerrainList.add(new SquareJunction(300,225)); //19
//        

//        //Draw traffic
//        cars.add(new Car(p,210,230));
//        motorbikes.add(new Motorbike(250,255));  
//        lorries.add(new Lorry(240,230));
//        bikes.add(new Bike(170,230));
//        emergencies.add(new Emergency(120,230));

		//Run traffic simulation forever

		//add nodes

		int i=0;
		for (Terrain t : aTerrainList){
			Node n = new Node("Node "+ ++i);
			aNodeList.add(n);
			t.setCurrentNode(n);
		}
			
		//Create my Network's Node Graph. So, this will add the nextNode for every node.
		//Optionally, create a graph class that parses the "core" list, and the graph with AI select the 
		//relations between each node.
		
		aNodeList.get(0).setNextNodeToTheNodeList(aNodeList.get(1));
		aNodeList.get(1).setNextNodeToTheNodeList(aNodeList.get(18));
		aNodeList.get(18).setNextNodeToTheNodeList(aNodeList.get(7));
		aNodeList.get(18).setNextNodeToTheNodeList(aNodeList.get(2));
		aNodeList.get(18).setNextNodeToTheNodeList(aNodeList.get(11));
		aNodeList.get(2).setNextNodeToTheNodeList(aNodeList.get(3));
		aNodeList.get(3).setNextNodeToTheNodeList(aNodeList.get(10));
		aNodeList.get(10).setNextNodeToTheNodeList(aNodeList.get(6));
		aNodeList.get(6).setNextNodeToTheNodeList(aNodeList.get(9));
		aNodeList.get(9).setNextNodeToTheNodeList(aNodeList.get(5));
		aNodeList.get(5).setNextNodeToTheNodeList(aNodeList.get(4));
		aNodeList.get(4).setNextNodeToTheNodeList(aNodeList.get(13));
		aNodeList.get(13).setNextNodeToTheNodeList(aNodeList.get(7));
		aNodeList.get(11).setNextNodeToTheNodeList(aNodeList.get(12));
		aNodeList.get(12).setNextNodeToTheNodeList(aNodeList.get(8));
		aNodeList.get(8).setNextNodeToTheNodeList(aNodeList.get(14));
		aNodeList.get(14).setNextNodeToTheNodeList(aNodeList.get(15));
		aNodeList.get(15).setNextNodeToTheNodeList(aNodeList.get(16));
		aNodeList.get(16).setNextNodeToTheNodeList(aNodeList.get(17));
		
		//System.out.println(aNodeList.get(18).getNextNodeList().toString()); correct result
		
		/*
		//integrate TrafficLight with the nodes
		if (aNodeList.get(0).getNextNodeList().get(0).returnStack().get(1) instanceof TrafficLights)
			System.out.println("TR"); // write here the code
		*/

		aDraw = new Draw(aVehicleList,aTerrainList);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame = new JFrame();
				frame.add(aDraw);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.setSize(700, 700);
			}
		});

		//create the node managers who has all the informations about the nodes, and he will change the vehicles from node to node upon each car request
		System.out.println("Debug Draw: "+aDraw);
		nodeManager = new NodeManager(aDraw);
		//nodeManager.setMap(aDraw);
		
		for (Person p : aPersonList){
			p.setTheManager(nodeManager);
		}

		nodeManager.createTheNetwork(aNodeList,aVehicleList,aTerrainList);

		//System.out.println("My network is like that:");
		nodeManager.printMyNetwork();
		//nodeManager.vehicleFlow_version1();

		//Create and move cars with different threads

		nodeManager.start();
		
		//nodeManager.vehicleFlow_version1();

//		nodeManager.resetNodeNetwork(); there is a bug now, but whatever
		//nodeManager.createTheNetwork(aNodeList,aVehicleList,aTerrainList);
		
		/*for (int i=0;i<10;i++) {
			nodeManager.addANewVehicleToTheNetwork(new Car(new Person("1", 10, false), 100, i++));
		}*/
		//random vehicle creation by time (RANDOM)
	}
}