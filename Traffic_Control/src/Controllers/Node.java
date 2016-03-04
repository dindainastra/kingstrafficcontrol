package Controllers;

import Objects.Car;
import java.util.ArrayList;
import java.util.logging.Level;

public class Node {
	private ArrayList<Object> myStack = new ArrayList<Object>();
	private ArrayList<Node> nextNodeList = new ArrayList<Node>(); //I will need probably to add an option to control the junctions... we'll see
	private String nameOfNode;
	private double weightOfDifficulty;
	private double distranceOfNode;

	public Node(String nodeName, double weight, double distance) {
		this.nameOfNode = nodeName;
		this.weightOfDifficulty = weight;
		this.distranceOfNode = distance;
	}

	public Node(String nodeName, double weight, double distance, ArrayList<Node> nextNodeList) {
		this.nameOfNode = nodeName;
		this.weightOfDifficulty = weight;
		this.distranceOfNode = distance;
		this.nextNodeList = nextNodeList;
	}

	public boolean hasNext(){
		if (this.nextNodeList.get(0) instanceof Node){
			return true;
		}
		return false;
	}
	
	public boolean hasNodes(){
		if (!this.nextNodeList.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public String toString () {
        return nameOfNode + "";
    } 
	
	public void setNextNodeToTheNodeList(Node n){
		this.nextNodeList.add(n);
	}
	
	public ArrayList<Node> getNextNodeList(){
		return this.nextNodeList;
	}
	
	public ArrayList<Object> returnStack(){
		return this.myStack;
	}
	
	public void addToStack(Object obj){
		this.myStack.add(obj);
	}

	public void removeFromStack(Object obj){
		this.myStack.remove(obj);
	}

	public void printStack(){
		//degub method
		for (Object item : myStack) {   
		    System.out.println	(item);
		}
	}

	public String getNameOfNode() {
		return nameOfNode;
	}

	public void setNameOfNode(String nameOfNode) {
		this.nameOfNode = nameOfNode;
	}

	public double getWeightOfDifficulty() {
		return weightOfDifficulty;
	}

	public void setWeightOfDifficulty(double weightOfDifficulty) {
		this.weightOfDifficulty = weightOfDifficulty;
	}

	public double getTime() {
		return distranceOfNode;
	}

	public void setTime(double time) {
		this.distranceOfNode = time;
	}

}
