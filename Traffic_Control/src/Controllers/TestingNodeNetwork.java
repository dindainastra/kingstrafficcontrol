package Controllers;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Objects.*;

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
				aVehicleList.add(new Car(p,100,225));
		
				aVehicleList.add(new Car(p,0,0));
		
		aTerrainList.add(new SRoad(100,225,00,2,0));
		aTerrainList.add(new SRoad(200,225,01,3,0));
		aTerrainList.add(new SRoad(350,255,11,1,0));
		aTerrainList.add(new SRoad(100,225,00,2,0));
		aTerrainList.add(new TrafficLights());
		//Draw vertical roads
		aTerrainList.add(new SRoad(600,125,11,1,90));
		aTerrainList.add(new SRoad(350,125,01,2,90));
		aTerrainList.add(new SRoad(300,325,01,2,90));

          //Draw curved roads
		aTerrainList.add(new CRoad(500,75,360));
		aTerrainList.add(new CRoad(500,175,270));
		aTerrainList.add(new CRoad(250,225,270));
		aTerrainList.add(new CRoad(250,275,90));

		aTerrainList.add(new CRoad(300,75,90));

          //draw roundabout
		aTerrainList.add(new CRoad(225,400,90));
		aTerrainList.add(new CRoad(225,400,180));
		aTerrainList.add(new CRoad(225,400,270));
		aTerrainList.add(new CRoad(225,400,360));
          //Draw junctions
		aTerrainList.add(new SquareJunction(300,225));

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
		
		for (int i=0;i<10;i++)
			nodeManager.addANewVehicleToTheNetwork(new Car(new Person("1",10,false),100,i++));
		//random vehicle creation by time (RANDOM)
	}

	
}