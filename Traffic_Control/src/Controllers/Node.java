package Controllers;

import Objects.Car;
import java.util.ArrayList;
import java.util.logging.Level;

public class Node  implements Runnable{
	private ArrayList<Object> myStack = new ArrayList<Object>();
	private ArrayList<Node> nextNodeList = new ArrayList<Node>(); //I will need probably to add an option to control the junctions... we'll see
	private ArrayList<Node> previousNodeList = new ArrayList<Node>(); 
	private int directionOfNode = 0;  // 0 ->   and  1 <- 
	private String nameOfNode;

	public Node(String nodeName) {
		this.nameOfNode = nodeName;
	}

	public Node(String nodeName, ArrayList<Node> nextNodeList, ArrayList<Node> previousNodeList) {
		this.nameOfNode = nodeName;
		this.nextNodeList = nextNodeList;
		this.previousNodeList = previousNodeList;
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
	
	public void setPreviousNodeToTheNodeList(Node n){
		this.previousNodeList.add(n);
	}
	
	public ArrayList<Node> getPreviousNodeList(){
		return this.previousNodeList;
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
	
	public int getDirectionOfNode(){
		return this.directionOfNode;
	}
	
	public void getDirectionOfNode(int i){
		this.directionOfNode = i;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
