package Objects;

import java.awt.Graphics2D;

import Controllers.Node;

public interface Vehicle {

	public int getPriority();
	public void setPriority(int priority);
	public Person getPerson();
	public void setPerson(Person p);
	public void doDrawing(Graphics2D g);
	public void set_pos_x(int x);
	public int get_pos_x();
	public void move();
	public int getLength();
	public Node getNextNode();
	public void setNextNode(Node n);
	public Node getCurrentNode();
	public void setCurrentNode(Node n);
}
