package Controllers;

import Objects.Car;
import Objects.Person;

public class Main_Node {

	public static void main(String[] args) {
		
		
		//Declarations
		//Create the main Objects on the Node Network
		
		Person p1 = new Person("Person1", 10, false);
		Person p2 = new Person("Person2", 9, false);
		Person p3 = new Person("Person3", 8, true); //This Person is a pedestrian, So dont add it on a Car
		Person p4 = new Person("Person4", 10, false);
		
		
		//Create my Cars and put the drivers in it
		Car c1 = new Car(p1);
		Car c2 = new Car(p2);
		Car c3 = new Car(p4);
		
		//Create the Note Manager or Node Network relations
		NodeManager  nm = new NodeManager();
		
		
		//Create a basic node network structure.
		//eg
		// (START) -->  (1ST NODE)  --> (2ND NODE)  --> (END)
		Node n1 = new Node("START",0,0);
		Node n2 = new Node("First Node",0.8,10.0);
		Node n3 = new Node("Second Node",0.5,20.0);
		Node n4 = new Node("END",0,0);
		
		//Add the nodes into my manager
		nm.addNode(n1);
		nm.addNode(n2);
		nm.addNode(n3);
		nm.addNode(n4);
		
		//Add my cars in each node stack
		n2.addToStack(c1);
		n2.addToStack(c2);
		n3.addToStack(c3);
		
		//Debug start   -- Just print my objects and check if node has the correct objects in it.
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
		
		//System.out.println(nm.getArrayList().get(1).getNameOfNode());
		System.out.println(nm.getArrayList());
		System.out.print(nm);
		//debug end
	}

}
