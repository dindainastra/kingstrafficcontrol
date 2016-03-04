package Objects;
import java.awt.*;

import javax.swing.JPanel;

import Controllers.Node;

public class Bike extends JPanel implements Vehicle{
    
	private Node currentNode;
	private Node nextNode;
	// Variables declaration
    private final int pos_x,pos_y;
    private final int R=128, G=0, B=128; //pastel purple
    private final int length = 10 ,width = 11;
    
    //set Bike position
    Bike(int x_coordinate, int y_coordinate){
        this.pos_x = x_coordinate;
        this.pos_y = y_coordinate;
    }
    
    //draw Bike
    protected void doDrawing(Graphics g){
        g.setColor(new Color (R,G,B));
        g.fillRect(pos_x, pos_y, length, width);   
       // repaint(); 
    }

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPriority(int priority) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Person getPerson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPerson(Person p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDrawing(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set_pos_x(int x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int get_pos_x() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return 0;
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
