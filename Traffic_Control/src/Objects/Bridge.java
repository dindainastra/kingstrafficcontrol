package Objects;

import java.awt.Graphics2D;

import Controllers.Node;

//bridge dummy comment

public class Bridge implements Terrain{

	private Node nextNode;
	private Node currentNode;
	private int lenght;
	
	@Override
	public void doDrawing(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLenght() {
		return lenght;  // probably this is the lenght of this. dunno
	} 
	
	@Override
	public Node getNextNode() {
		return this.nextNode;
	}

	@Override
	public void setNextNode(Node n) {
		this.nextNode = n;
	}

	@Override
	public Node getCurrentNode() {
		return this.currentNode;
	}

	@Override
	public void setCurrentNode(Node n) {
		this.currentNode = n;
	}

}
