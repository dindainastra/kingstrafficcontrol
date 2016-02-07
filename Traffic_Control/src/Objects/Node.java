import java.util.ArrayList;

public class Node {

	private ArrayList<Object> myStack = new ArrayList();
	
	public Node() {
		
	}
	
	
	public void getPreviousNode(){
	
	}

	public void getNextNode(){
		
	}	
	
	public void addToStack(Object obj){
		this.myStack.add(obj);
	}

	public void removeFromStack(Object obj){
		this.myStack.remove(obj);
	}

}
