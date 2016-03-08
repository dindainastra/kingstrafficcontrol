package Objects;
import java.awt.*;

import javax.swing.JPanel;

public class Bike extends JPanel implements Vehicle{
    
	private Person driver;
	private int priorityLevel;
    private int pos_x,pos_y;
    private int R = 173, G = 216, B=230; //pastel blue   <---Change this color
    private final int length = 20 ,width = 15; //<---Change these variables
    
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
	public void doDrawing(Graphics2D g) {
		// TODO Auto-generated method stub
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
	public void move(){
		this.set_pos_x(length + this.get_pos_x( ) + 5);
		revalidate();
		repaint();
	}
}
