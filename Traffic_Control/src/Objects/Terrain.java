package Objects;

import java.awt.Graphics2D;

import Controllers.Node;

public interface Terrain {


	public void doDrawing(Graphics2D g);
	
	public Node getCurrentNode();
	public void setCurrentNode(Node n);
	public int getLenght();

	public void setNextNode(Node n);
	public Node getNextNode();
}
