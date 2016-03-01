package Objects;

//test 

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.Timer;


public class Car implements Vehicle {
        
        // Variables declaration
	private Person driver;
	private int priorityLevel;
	private int nodeID;
        private int pos_x,pos_y;
        private final int R = 173, G = 216, B=230; //pastel blue
        private final int length = 20 ,width = 15;
    
        //draw Vehicle
        public void doDrawing(Graphics g){
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
        
        
	
        public int getpos_x(){
            return this.pos_x;      
            
        }
        
        public int getpos_y(){
            return this.pos_y;      
            
        }
        
        public void setpos_x(int x){
            this.pos_x=x;      
            
        }
        
        public void setpos_y(int y){
            this.pos_y=y;      
            
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
	
	public String toString(){
		return this.driver.getName();
	}
}
