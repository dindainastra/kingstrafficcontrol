package Objects;

import java.awt.Graphics2D;

import Controllers.Node;

//bridge dummy comment

public class Bridge implements Terrain{

	private Node currentNode;
	private Node nextNode;
	private int lenght;
	
	@Override
	public void setInWhichNodeLocated() {
		// TODO Auto-generated method stub
		String tets = "testing";
		
	}

	@Override
	public int getInWhichNodeLocated() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPerson() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPerson() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDrawing(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLenght() {
		return this.lenght;
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
