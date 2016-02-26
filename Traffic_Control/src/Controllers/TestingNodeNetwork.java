package Controllers;

import Objects.Person;

public class TestingNodeNetwork {

	private static NodeManager  nodeManager;
	
	public static void main(String[] args) {
		
		//Declarations
		
		//create the node managers who has all the informations about the nodes, and he will change the vehicles from node to node upon each car request
		nodeManager = new NodeManager();
		
		//node manager creates and add the nodes to the node network
		nodeManager.addNodeToTheNodeNetwork(new Node("START",0,0));
		nodeManager.addNodeToTheNodeNetwork(new Node("First Node",0.8,10.0));
		nodeManager.addNodeToTheNodeNetwork(new Node("Second Node",0.5,20.0));
		nodeManager.addNodeToTheNodeNetwork(new Node("END",0,0));
	
		//create Persons, set them as drivers to a Vehicle, and add them into the Node Network
		nodeManager.addANewVehicleToTheNetwork(new Person("Person1", 10, false),"Car");			
		nodeManager.addANewVehicleToTheNetwork(new Person("Person2", 9, false),"Car");		
		nodeManager.addANewVehicleToTheNetwork(new Person("Person3", 10, false),"Car");		
		nodeManager.addANewVehicleToTheNetwork(new Person("Person4", 10, false),"Car");
		nodeManager.addANewVehicleToTheNetwork(new Person("Person5", 9, false),"Car");		
		nodeManager.addANewVehicleToTheNetwork(new Person("Person6", 10, false),"Car");		
		nodeManager.addANewVehicleToTheNetwork(new Person("Person7", 10, false),"Car");
		
		//print the whole network to see what is going on every time
		//nodeManager.printMyNetwork();

		//start this thing to work, baby.  
		//VERSION 1 - Move things to the next Node Stack ONLY if the previous Stack if empty.
		/*
		 *  Stack1		Stack2		Stack3
		 * 	4 Veh		0 Veh		0 Veh
		 * 
		 * So, Vehicles will move to stack3 ONLY when Stack1 is empty!
		 * Version_1 sends the Vehicles from stack to stack periodically.
		 * 
		 */
		//nodeManager.vehicleFlow_version1();  //  <--- maybe if it is on thread it should work. Dunno now
		
		//version2 uses iterators. Maybe it is more easy to parallelized but many conflicts with the iterators. Thats why I leave it as an example and reminder if version1 is not gonna work well.
//		nodeManager.vehicleFlow_version2();
		
//		nodeManager.vehicleFlow_version1();
		
		nodeManager.resetNodeNetwork();
		
		//if I haven't already remove from the vehicleFlow_version all the Objects it returns nothing because I have reset the network (nodeList declared as null)
		//The nodes that are in the memory are going to be deleted automatically due to the default garbage collector of JAVA
		nodeManager.vehicleFlow_version1();
		
		//node manager creates and add the nodes to the node network
		nodeManager.createMyNodeNetwork();
	
		//create Persons, set them as drivers to a Vehicle, and add them into the Node Network
		nodeManager.addObjectsToTheNetwork();
		
		nodeManager.vehicleFlow_version1();

		
	}

	
}