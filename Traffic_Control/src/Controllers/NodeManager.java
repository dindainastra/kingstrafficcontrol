package Controllers;

import java.util.ArrayList;
import java.util.ListIterator;

public class NodeManager {

	private ArrayList<Node> Nodes = new ArrayList<Node>();
	private ListIterator listIterator;
	
	public NodeManager (){
		
		this.listIterator = Nodes.listIterator();
	
	}
	
	
	public void addNode(Node n){
		this.Nodes.add(n);
	}
	
	public int getNextNode(){
		return listIterator.previousIndex();
	}
	
	
	public int getPreviousNode(){
		return listIterator.nextIndex();
	}
	 
	public void moveToAnotherNode(){
		listIterator.next();
	}
}
