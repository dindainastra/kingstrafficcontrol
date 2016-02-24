package Controllers;

import java.util.ArrayList;

import Objects.Car;
import Objects.Person;

public class Main_Node2 {
	
	private static ArrayList<Node> nodeList = new ArrayList<Node>();
	private static ArrayList<Car> carList = new ArrayList<Car>();

	public static void main(String[] args) {
		
		//Declarations
		//Create the main Objects on the Node Network
		
		Person p1 = new Person("Person1", 10, false);
		Person p2 = new Person("Person2", 9, false);
		//This Person is a pedestrian, So dont add it in a Car
		Person p3 = new Person("Person3", 8, true); 
		Person p4 = new Person("Person4", 10, false);
		
		
		//Create my Cars and put the drivers in it
		carList.add(new Car(p1));
		carList.add(new Car(p1));
		carList.add(new Car(p1));
		carList.add(new Car(p1));
		
		//Create the Note Manager or Node Network relations
		NodeManager  nm = new NodeManager();
		
		//Create a basic node network structure.
		//eg
		// (START) -->  (1ST NODE)  --> (2ND NODE)  --> (END)

		nodeList.add(new Node("START",0,0));
		nodeList.add(new Node("First Node",0.8,10.0));
		nodeList.add(new Node("Second Node",0.5,20.0));
		nodeList.add(new Node("Third Node",0.5,20.0));
		nodeList.add(new Node("END",0,0));
		
		//Add the nodes into my manager
		
		for (Node item : nodeList) {  
			nm.addNode(item);
		}
		
		//Add my cars in each node stack
//		for (Car tmpC : carList){
//				nodeList.get(0).addToStack(tmpC);
//				tmpC.setInWhichNodeIsIt(0);
//		}
		
		nodeList.get(0).addToStack(carList.get(0));  //node 0  with car1
		nodeList.get(0).addToStack(carList.get(1));  //node 0  with car2
		nodeList.get(1).addToStack(carList.get(2));  //node 1  with car3
		nodeList.get(2).addToStack(carList.get(3));  //node 2  with car4
		
		for (Node item : nm.getArrayList()) {   
		    System.out.println(
		    		item.getNameOfNode() 
		    		+ " " 
		    		+ item.getTime() 
		    		+ " " 
		    		+ item.getWeightOfDifficulty()
		    					);
		    item.printStack();
		}
	    System.out.println("");

		for (;;) {
		
			for (int ii=0;ii<nodeList.size();ii++){
				
				ArrayList<Object> nodeTmpStack = (ArrayList<Object>) nodeList.get(ii).returnStack();
			
				int static_counter = nodeTmpStack.size();
				for (int iii = 0;iii<static_counter;iii++){
					
					if (ii+1 < nodeList.size() ){
					System.out.println("" + nodeList.get(ii+1).getNameOfNode() + " add to stack "+nodeTmpStack.get(0));
					nodeList.get(ii+1).addToStack(nodeTmpStack.get(0));
					}
					System.out.println("" + nodeList.get(ii).getNameOfNode() + " remove from stack "+nodeTmpStack.get(0));
					nodeList.get(ii).removeFromStack(nodeTmpStack.get(0));
					
					System.out.println("->I2 " + ii);
					System.out.println("->->I3 " + iii);
					System.out.println("nodeTmpStack.size() -->>"+nodeTmpStack.size());
	
					for (Node item : nm.getArrayList()) {   
						System.out.println	(
					    		item.getNameOfNode() 
					    		+ " " 
					    		+ item.getTime() 
					    		+ " " 
					    		+ item.getWeightOfDifficulty()
					    					);
					    item.printStack();
					}
				    System.out.println("");

				}
			
			}
		}
		
	}

}
