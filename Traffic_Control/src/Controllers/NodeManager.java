package Controllers;

import java.util.ArrayList;

import Objects.Car;
import Objects.Draw;
import Objects.Person;
import Objects.Terrain;
import Objects.TrafficLights;
import Objects.Vehicle;

public class NodeManager {

	private ArrayList<Node> nodeList = new ArrayList<Node>();	
	private ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();	
	private ArrayList<Terrain> terrainList = new ArrayList<Terrain>();	
	private String teamName;
	private String slogan;
	private Draw map;
	
	//Constructor
	//For now, it is just for fun :D 
	//Possible uses: Create here each thread
	public NodeManager (Draw d){
				
		map = d;
		this.teamName = "KingsTrafficControl";
		this.slogan = "Only you can control your future, and in our case only we can control the Traffic :D";
	}
	
	public void start(){
		for (Vehicle c : vehicleList){
			for (int steps=5; steps>0; steps--)
			try {
				Thread.sleep(1000);
				if (c instanceof Car){
					c.move();
					map.revalidate();
					map.repaint();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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

	
	//Add a Vehicle to the Starting Node!
	//It declares every vehicle to the starting point because obviously  every car/bus/etc should enter first of all into the network and them to go to their direction.
	//should exist because if we need to add manually Vehicles call it for how many Vehicles are needed
	public void addANewVehicleToTheNetwork(Vehicle aVehicle){
		getNodeList().get(0).addToStack(aVehicle);
	}
	
	//Add ANY Terrain object to  a specific NODE.
	//It needs to be to a specific node, because the map is fixed.
	public void addANewTerrainToTheNetwork(Terrain t, int whichNode){
		getNodeList().get(whichNode).addToStack(t);
	}
	
	//Just reset the Node Network
	public void resetNodeNetwork(){
		nodeList.clear();
		vehicleList.clear();
	}
	
	//create the network
	public void createTheNetwork(ArrayList<Node> nl, ArrayList<Vehicle> vl, ArrayList<Terrain> tl){
		
		this.setNodeList(nl);
		this.setVehicleList(vl);
		this.setTerrainList(tl);
		
		for (Vehicle v : vehicleList)
			addANewVehicleToTheNetwork(v);
		
		for (Terrain t : terrainList)
			addANewTerrainToTheNetwork(t,1);
		
	}
	
	public void setTerrainList(ArrayList<Terrain> tl){
		this.terrainList = tl;
	}
	
	public void setNodeList(ArrayList<Node> nl){
		this.nodeList = nl;
	}
	
	public void setVehicleList(ArrayList<Vehicle> vl){
		this.vehicleList = vl;
	}
	
}