package Objects;

import java.awt.*;

import javax.swing.JPanel;

import Controllers.Node;
import Controllers.NodeManager;
public class Car extends JPanel implements Vehicle {

	private Person driver;
	private int priorityLevel;
	private Node currentNode;
	private Node nextNode;
    private int pos_x,pos_y;
    private int R = 173, G = 216, B=230; //pastel blue
    private final int length = 20 ,width = 15;
    private JPanel myPanel;
    private int tmp = 0;
    private double distanceFromNodeToNode;
    private int offset = 5;
        
	public void move(){
		System.out.println("Next StackSize "+nextNode.returnStack().size()+" Driver: "+ driver.getName() + " X: " + (length + this.get_pos_x( ) + offset - ((nextNode.returnStack().size()-1) * this.length)));
		this.set_pos_x(length + this.get_pos_x( ) + offset - ((nextNode.returnStack().size()-1) * this.length));
		revalidate();
		repaint();
	}

	/**
	 * Smoother movement of the car from one coordinate to another
	 */
	public void transition(int startX, int startY, int endX, int endY){

	}
        
	//set car position
	public Car(Person p, int x_coordinate, int y_coordinate){
		driver = p;
		priorityLevel = 0; //default no priority
		this.pos_x = x_coordinate;
		this.pos_y = y_coordinate;
	}
	
	public void set_pos_x(int x){
		this.pos_x = x;
	}
	
	public int get_pos_x(){
		return this.pos_x;
	}
	
	public void set_pos_y(int y){
		this.pos_y = y;
	}
	
	public int get_pos_y(){
		return this.pos_y;
	}
	
	public int getLength(){
		return this.length;
	}
	
	public int getPriority() {
		return this.priorityLevel;
	}

	public void setPriority(int priority) {
		this.priorityLevel = priority;
		checkEmergency();
	}
	
	public void checkEmergency(){
		if (this.getPriority()==1){
			//RGB=RED
			this.setR(255);
			this.setG(0);
			this.setB(0);
            revalidate();
            repaint();
		}
	}
	
	public void setR(int r){
		this.R = r;
	}
	
	public void setG(int g){
		this.G = g;
	}
	
	public void setB(int b){
		this.B = b;
	}

	public String getDriversName() {
		return driver.getName();
	}
	
	public int getDriversPolitenssLevel() {
		return driver.getPolitenessLevel();
	}
	
	public void setPerson(Person p) {
		this.driver = p;
	}

	public Person getPerson() {
		return this.driver;
	}	
	
	public String toString(){
		return this.driver.getName();
	}

	@Override
	public void doDrawing(Graphics2D g) {
		g.setColor(new Color (R,G,B));
        g.fillRect(pos_x, pos_y, length, width);
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
