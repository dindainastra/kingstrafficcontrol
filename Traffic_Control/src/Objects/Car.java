package Objects;

import java.awt.*;
public class Car implements Vehicle {
        
        // Variables declaration
	private Person driver;
	private int priorityLevel;
	private int nodeID;
        private final int pos_x,pos_y;
        private final int R = 173, G = 216, B=230; //pastel blue
        private final int length = 20 ,width = 15;
    
        //draw Vehicle
        protected void doDrawing(Graphics g){
            g.setColor(new Color (R,G,B));
            g.fillRect(pos_x, pos_y, length, width);   
        }
    
        //set car position
	public Car(Person p,int x_coordinate, int y_coordinate){
		driver = p;
		priorityLevel = 0; //default no priority
                this.pos_x = x_coordinate;
                this.pos_y = y_coordinate;
        }
	
	public int getPriority() {
		return this.priorityLevel;
	}

	public void setPriority(int priority) {
		this.priorityLevel = priority;
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
}

