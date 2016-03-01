package Controllers;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Objects.Car;
import Objects.Display;
import Objects.Draw;
import Objects.Person;
import Objects.SRoad;
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
		
		//Declarations
		aPersonList.add(new Person("Person1", 10, false));
		aPersonList.add(new Person("Person2", 10, false));
		aPersonList.add(new Person("Person3", 10, false));
		aPersonList.add(new Person("Person4", 10, false));
		aPersonList.add(new Person("Person5", 10, false));
		aPersonList.add(new Person("Person6", 10, false));
		
		for (Person p : aPersonList)
			if (!p.isPedestrian())
				aVehicleList.add(new Car(p,0,0));
		
		aTerrainList.add(new SRoad(100,225,00,2,0));
//		aTerrainList.add(new TrafficLights());
//		aTerrainList.add(new TrafficLights());
//		
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

		nodeManager.start();
		
		nodeManager.vehicleFlow_version1();
		
//		nodeManager.resetNodeNetwork(); there is a bug now, but whatever

		nodeManager.createTheNetwork(aNodeList,aVehicleList,aTerrainList);
		
		
	}

	
}