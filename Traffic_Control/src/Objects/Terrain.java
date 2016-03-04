package Objects;

import java.awt.Graphics2D;

import Controllers.Node;

public interface Terrain {

	public void setInWhichNodeLocated();
	public int getInWhichNodeLocated();
	public int getPerson();
	public void setPerson();
	public void doDrawing(Graphics2D g);
	public int getLenght();
	public Node getNextNode();
	public void setNextNode(Node n);
	public Node getCurrentNode();
	public void setCurrentNode(Node n);
	
}
