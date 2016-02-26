package Controllers;

import java.util.ArrayList;
import java.util.Iterator;

import Objects.Car;
import Objects.Person;
import Objects.Terrain;
import Objects.TrafficLights;

public class NodeManager {

	private ArrayList<Node> nodeList = new ArrayList<Node>();
	private Iterator<Node> iterator;
	private String teamName;
	private String slogan;
	
	//Constructor
	//For now, it is just for fun :D 
	//Possible uses: Create here each thread
	public NodeManager (){
		this.teamName = "KingsTrafficControl";
		this.slogan = "Only you can control your future, and in our case only we can control the Traffic :D";
	}
	
	
	//Return the nodeNetwork.
	public ArrayList<Node> getNodeList(){
		return nodeList;
	}
	
	//Calculates the MAX Size of a Node. Simplify: Return an integer of the biggest stack. 
	public int getMAXNodeSize(){
		
		int max = 0;
		for (Node n : nodeList)
			if (max < n.returnStack().size() ) 
				max = n.returnStack().size();
		return max;
	}
	
	
	//Add a node to the network
	public void addNodeToTheNodeNetwork(Node n){
		this.nodeList.add(n);
	}
	
	//Print in that specific time how my network looks like. 
	//Where are the objects, in which node and things like that. 
	//Possible future debugging using the Threads
	public void printMyNetwork(){
		
		for (Node n : getNodeList())
			System.out.print("::: "+n.getNameOfNode()+" :::\t\t\t");
		System.out.println("");
		
		int i=0;
		for (int j=0;j<getMAXNodeSize();j++){
			for (Node n : getNodeList())
				if (n.returnStack().size() > 0 && n.returnStack().size() > i)
					System.out.print(n.returnStack().get(i).toString()+"\t\t");
				else 
				{
					System.out.print("\t\t\t\t\t");
				}
			System.out.println("");
			i++;
		}
		System.out.println("");
	}
	
	//Version 1 - Every object moves from the one node to the other if the previous is empty!
	// Future things to do:
	// 1) Check if an Object is Terrain and STICK it into its NODE. So, don't move the Terrain Objects
	// 2) Every Object should not move periodically, for example, from stack to stack if the previous stack is empty, BUT in a sequence. Possible solution for this: MThreading. 
	public void vehicleFlow_version1(){
		
		System.out.println("Flow_version1");
		printMyNetwork();
		
		if (nodeList != null)
		for (int ii=0;ii<nodeList.size();ii++){
			
			ArrayList<Object> nodeTmpStack = (ArrayList<Object>) nodeList.get(ii).returnStack();
		
			int static_counter = nodeTmpStack.size();
			for (int iii = 0;iii<static_counter;iii++){
				
				if (ii+1 < nodeList.size() )
					nodeList.get(ii+1).addToStack(nodeTmpStack.get(0));
				nodeList.get(ii).removeFromStack(nodeTmpStack.get(0));
				
				printMyNetwork(); //just to see if everything is fine

			}
		}
		
		
	}
	
	//Version 2 - IT DOES NOT WORK! JUST AS REMINDER IF THINGS GO VERY WRONG WITH THE MULTITHREADING.
	public void vehicleFlow_version2(){
		//it wouldn't work with iterators...
		//basically, it will be much more difficult.
		//I let it as an example, if the version1 is not possible to be parallelized
		
		System.out.println("Flow_version2");
		printMyNetwork();
		
//		if (NodeList != null)
		
		iterator = nodeList.iterator();
		
		while (iterator.hasNext()) {
		    System.out.println("Iterator Says: "+iterator.next().getNameOfNode());
		    printMyNetwork();
		}
		
		while (iterator.hasNext()) {
		    System.out.println("::2:: Iterator Says: "+iterator.next().getNameOfNode());
		    printMyNetwork();
		}
		
	}
	
	//Add a Vehicle to the Starting Node!
	//It declares every vehicle to the starting point because obviously  every car/bus/etc should enter first of all into the network and them to go to their direction.
	//should exist because if we need to add manually Vehicles call it for how many Vehicles are needed
	public void addANewVehicleToTheNetwork(Person p, String obj){
		if (obj.equals("CAR"))
			getNodeList().get(0).addToStack(new Car(p, 0, 0));
	}
	
	//Add ANY Terrain object to  a specific NODE.
	//It needs to be to a specific node, because the map is fixed.
	public void addANewTerrainToTheNetwork(Terrain t, int i){
		getNodeList().get(i).addToStack(t);
	}
	
	//Just reset the Node Network
	public void resetNodeNetwork(){
		nodeList.clear();
	}
	
	//Declare the Node Network
	//also link the one node with as many next node it has.
	public void createMyNodeNetwork(){

		Node previousNode;
		Node startNode = new Node("START",0,0);
		addNodeToTheNodeNetwork(startNode);

		Node tmpN = new Node("First Node",0.8,10.0);
		addNodeToTheNodeNetwork(tmpN);

		previousNode = startNode;
		previousNode.setNextNodeToTheNodeList(tmpN);

		tmpN = new Node("Second Node",0.5,20.0);
		addNodeToTheNodeNetwork(tmpN);

		previousNode = tmpN;
		previousNode.setNextNodeToTheNodeList(tmpN);
		
		tmpN = new Node("Third Node",0.5,20.0);
		addNodeToTheNodeNetwork(tmpN);

		previousNode = tmpN;
		previousNode.setNextNodeToTheNodeList(tmpN);
		
		Node endNode = new Node("END",0,0);
		addNodeToTheNodeNetwork(endNode);  
			
		//previousNode = tmpN;
		//addNodeToTheNodeNetwork(tmpN); //do a cycle from end to start again and again! :)

	}

	//This method adds ALL the objects to the Node Network!
	public void addObjectsToTheNetwork() {
		
		addANewVehicleToTheNetwork(new Person("Person1", 10, false),"CAR");			
		addANewVehicleToTheNetwork(new Person("Person2", 9, false),"CAR");
		addANewTerrainToTheNetwork(new TrafficLights(),1);	//TL in Node = 1	//I have to and the option to stick the Terrain Class and not move it from Node to Node.
		addANewTerrainToTheNetwork(new TrafficLights(),2);	//TL in Node = 2	//same as above...
		addANewVehicleToTheNetwork(new Person("Person3", 9, false),"CAR");
		addANewVehicleToTheNetwork(new Person("Person4", 9, false),"CAR");
		addANewVehicleToTheNetwork(new Person("Person5", 9, false),"CAR");
		addANewVehicleToTheNetwork(new Person("Person6", 9, false),"CAR");
		addANewVehicleToTheNetwork(new Person("Person7", 9, false),"CAR");

		
	}
	
}