package Controllers;

import Objects.Car;
import Objects.Person;

public class Main_Node {

	public static void main(String[] args) {
		
		Person p1 = new Person("Person1", 10, false);
		Person p2 = new Person("Person2", 9, false);
		Person p3 = new Person("Person3", 8, true);
		Person p4 = new Person("Person4", 10, false);
		
		Car c1 = new Car(p1);
		Car c2 = new Car(p2);
		Car c3 = new Car(p4);
		
		NodeManager  nm = new NodeManager();
		
		
		Node n1 = new Node("START",0,0);
		Node n2 = new Node("First Node",0.8,10.0);
		Node n3 = new Node("Second Node",0.5,20.0);
		Node n4 = new Node("END",0,0);
		
		nm.addNode(n1);
		nm.addNode(n2);
		nm.addNode(n3);
		nm.addNode(n4);
		
		
		n1.addToStack(c1);
		
		//Debug start
		for (Node item : nm.getArrayList()) {   
		    System.out.println(item.getNameOfNode() + " " + item.getTime() + " " + item.getWeightOfDifficulty());
		}
		
		//System.out.println(nm.getArrayList().get(1).getNameOfNode());
		System.out.println(nm.getArrayList());
		System.out.print(nm);
		//debug end
	}

}
