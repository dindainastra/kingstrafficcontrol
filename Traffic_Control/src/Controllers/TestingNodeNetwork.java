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
				aVehicleList.add(new Car(p, 200, 330));
			}
		}


		//add roundabout
		aTerrainList.add(new CornerRoad(640,250,90,1));
		aTerrainList.add(new CornerRoad(640,250,180,1));
		aTerrainList.add(new CornerRoad(640,250,270,1));
		aTerrainList.add(new CornerRoad(640,250,360,1));

		//add horizontal road
		//Added another traffic light color parameter to SRoad
		//aTerrainList.add(new SRoad(getposx(),getposy(),gettraffic(),gettrafficcolor1(),gettrafficcolor2(),getrotation());

		aTerrainList.add(new StraightRoad(150,325,11,2,2,0,500));
		aTerrainList.add(new StraightRoad(880,325,11,1,3,0,200));
		aTerrainList.add(new StraightRoad(1180,325,10,1,3,0,170));//exit road
		aTerrainList.add(new StraightRoad(150,10,01,2,2,0,565));
		aTerrainList.add(new StraightRoad(150,590,01,2,2,0,565));
		aTerrainList.add(new StraightRoad(10,325,01,2,2,0,40));//entry road
		aTerrainList.add(new StraightRoad(815,590,10,2,2,0,265));
		aTerrainList.add(new StraightRoad(815,10,10,2,2,0,265));

		//add vertical roads
		aTerrainList.add(new StraightRoad(815,110,11,1,1,90));
		aTerrainList.add(new StraightRoad(815,490,11,2,2,90,101));
		aTerrainList.add(new StraightRoad(150,110,01,2,2,90,215));
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

		int i=0;
		for (Terrain t : aTerrainList){
			Node n = new Node("Node "+ ++i);
			aNodeList.add(n);
			t.setCurrentNode(n);
		}
			
		//Create my Network's Node Graph. So, this will add the nextNode for every node.
		//Optionally, create a graph class that parses the "core" list, and the graph with AI select the 
		//relations between each node.
		
		
		//this is a graph for the only map.. shit.
		
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
//				StopWatch stopWatch = new StopWatch();
//				stopWatch.start();
//
				//System.out.println("Stopwatch time: " + stopWatch);
                frame.add(aDraw);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.setSize(1500, 1000);
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