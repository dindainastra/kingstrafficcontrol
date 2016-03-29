package Objects;

import Controllers.CarFlow;
import Controllers.TrafficManagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Car extends JPanel implements Vehicle{

	private Person driver;
	private int priorityLevel;
    private int pos_x, pos_y;
    private int R = 173, G = 216, B=230; //pastel blue
    private final int length = 20 ,width = 15;
    private JPanel myPanel;
    private int tmp = 0;
    private double distanceFromNodeToNode;
    private int offset = 0;
	private static final int INCREMENT_BY = 5;
	public static Boolean draw = false;
	public double rotate=0.0;
//	public int rotateInt = 0;
	volatile Boolean destination = false;
	//private final int radius=100;

	public CarFlow.Direction currentDirection = CarFlow.Direction.RIGHT;

	public Car(Person p, int x_coordinate, int y_coordinate){
		driver = p;
		priorityLevel = 0; //default no priority
		this.pos_x = x_coordinate;
		this.pos_y = y_coordinate;
	}

	public void move(){
		this.pos_x += 2;
	}

	public void move(int flow, int x, int y){
//		if() {
//			this.pos_x += 2;
//		}else{}
	}

	public void setDestination(Boolean des){
		this.destination = des;
	}

	public Boolean getDestination(){
		return destination;
	}

	public void setRotate(double r){
		rotate = Math.toRadians(r);
	}

	public double getRotate(){
		return rotate;
	}

	public void setCurrentDirection(CarFlow.Direction dir){
		currentDirection = dir;
	}

	public CarFlow.Direction getCurrentDirection(){
		return currentDirection;
	}

	public void move(CarFlow.Direction d){
		if(d == CarFlow.Direction.LEFT){
			this.pos_x -= 1;
		}else if(d == CarFlow.Direction.RIGHT){
			this.pos_x += 1;
		}else if(d == CarFlow.Direction.DOWN){
			this.pos_y += 1;
		}else if(d == CarFlow.Direction.UP){
			this.pos_y -= 1;
		}
	}

	public void turnCorner(double angle, int centerX, int centerY, int radius){
		if(radius == 100) {
			this.pos_x = (int) ((Math.cos(angle) * radius) + centerX);
			this.pos_y = (int) ((Math.sin(angle) * radius) + centerY);
		}else{
			this.pos_y = (int) ((Math.cos(angle) * radius) + centerY);
			this.pos_x = (int) ((Math.sin(angle) * radius) + centerX);
		}
//		this.pos_x = (int) ((Math.cos(angle) * radius/2) + centerX);
//		this.pos_y = (int) ((Math.sin(angle) * radius/2) + centerY);
		repaint();
	}

	public void turn(CarFlow.Direction d, CarFlow.Direction carDirection, double degree) {
		double turnDir = degree;

		if(degree == 0){
			turnDir = Math.toRadians(90);
		}

//		if(carDirection == CarFlow.Direction.RIGHT || carDirection == CarFlow.Direction.UP) {
			if (d == CarFlow.Direction.UP || d == CarFlow.Direction.LEFT) {
				rotate = -turnDir;
			} else if (d == CarFlow.Direction.DOWN || d == CarFlow.Direction.RIGHT) { //works
				rotate = turnDir;
			}
//		}else if(carDirection == CarFlow.Direction.LEFT || carDirection == CarFlow.Direction.DOWN){
//			if (d == CarFlow.Direction.UP || d == CarFlow.Direction.LEFT) {
//				rotate = turnDir;
//			} else if (d == CarFlow.Direction.DOWN || d == CarFlow.Direction.RIGHT) { //works
//				rotate = -turnDir;
//			}
//		}

		this.repaint();
	}

	public void turn(CarFlow.Direction d, CarFlow.Direction carD) {
		turn(d, carD, 0);
	}

	public void bend(CarFlow.Direction d, double degree){
		//Bend based on direction

		if(d == CarFlow.Direction.RIGHT) {
			rotate += Math.toRadians(degree);
		}else if(d == CarFlow.Direction.LEFT){
			rotate -= Math.toRadians(degree);
		}

//		this.pos_x += degree;
//		this.pos_y += degree;

		this.repaint();
	}

	public void turn(int x, int y){
		//turning left
		if(x < pos_x){
			//first move the car onto the new Y
			if(y != pos_y){ pos_y++; }
			//change the way the car is paint and move on the X coords
			//this.redraw("Left")
		}else if(x > pos_x){ //turning right

		}
	}

	public void turnJunction(Car c){
//		System.out.println("Turn object");
		pos_x += 3;
		rotate += 5;
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

	public int getWidth(){
		return this.width;
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
            //revalidate();
            //repaint();
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
//		g.setColor(new Color(R, G, B));
//		g.fillRect(pos_x, pos_y, length, width);
//
//
//		Color textColor = Color.BLACK;
//		g.setColor(textColor);  //greg
//		g.drawString(this.getPerson().getName().substring("Person".length()), pos_x, pos_y+width); //greg
	}


	//WHAT IS THIS ???? THIS IS NOT PAINT!!!
	//YOU PAINT WITH doDrawing Method!
	@Override
	public void paintComponent(Graphics g){
		//System.out.println("Repainting car object - turn is "+rotate);
		super.paintComponent(g);

		Graphics2D gd = (Graphics2D) g;
		AffineTransform at = gd.getTransform();

		//gd.translate(this.getWidth()/2, this.getHeight()/2);
		if(rotate != 0) {
			gd.rotate(rotate, pos_x, pos_y);
		}
//		gd.translate(getWidth()/2, getHeight()/2);

		gd.setColor(new Color (R,G,B));
		gd.fillRect(pos_x, pos_y, length, width);

		gd.setTransform(at);

        Color textColor = Color.BLACK;
        g.setColor(textColor);  //greg
        g.drawString(this.getPerson().getName().substring("Person".length()), pos_x, pos_y); //greg
    }

//	public void simulate(){
//		TrafficManagement tm = new TrafficManagement();
//		Terrain t = tm.getTerrainList().get(0);
//
//
//		CarFlow.Direction dir = cf.getDirection(this, t);
//
//		cf.moveToDestinationTimer(this, dir);
//
//
//	}

//	@Override
//	public void run() {
//		simulate();
//	}
}
