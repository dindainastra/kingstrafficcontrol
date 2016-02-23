package Controllers;

import java.util.ArrayList;

public class Node {

	private ArrayList<Object> myStack = new ArrayList<Object>();
	private String nameOfNode;
	private double weightOfDifficulty;
	private double time;

	public Node(String text, double text2, double text3) {
		
		this.setNameOfNode(text);
		this.setWeightOfDifficulty(text2);
		this.setTime(text3);

	}

	public Object returnStack(){
		return this.myStack;
	}
	
	public void addToStack(Object obj){
		this.myStack.add(obj);
	}

	public void removeFromStack(Object obj){
		this.myStack.remove(obj);
	}

	public void printStack(){
		
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
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

}
