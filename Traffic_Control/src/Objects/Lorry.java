package Objects;

import Controllers.VehicleFlowHelper;
import Controllers.VehicleFlowHelper.Direction;
import Interfaces.Vehicle;

import javax.swing.*;
import java.awt.*;

public class Lorry extends JPanel implements Vehicle {

    private final int length = 40, width = 17;
    private Person driver;
    private int priorityLevel;
    private int pos_x, pos_y;
    private int R = 222, G = 184, B = 135;//pastel brown

    //set lorry position
    public Lorry(int x_coordinate, int y_coordinate) {
        this.pos_x = x_coordinate;
        this.pos_y = y_coordinate;
    }

    //draw lorry
    protected void doDrawing(Graphics g) {
        g.setColor(new Color(R, G, B));
        g.fillRect(pos_x, pos_y, length, width);
        // repaint();
    }

    @Override
    public void doDrawing(Graphics2D g) {
        // TODO Auto-generated method stub

    }

    public int get_pos_x() {
        return this.pos_x;
    }

    public void set_pos_x(int x) {
        this.pos_x = x;
    }

    public int get_pos_y() {
        return this.pos_y;
    }

    public void set_pos_y(int y) {
        this.pos_y = y;
    }

    public int getLength() {
        return this.length;
    }

    @Override
    public boolean getLock() {
        return false;
    }

    @Override
    public void setLock(boolean lock) {

    }

    @Override
    public boolean amIMoving() {
        return false;
    }

    @Override
    public void setThatIAmMoving(boolean lock) {

    }

    @Override
    public void turnCorner(double angle, int centerX, int centerY, int radius) {

    }

    @Override
    public void bend(VehicleFlowHelper.Direction d, double degree) {

    }

    public int getPriority() {
        return this.priorityLevel;
    }

    public void setPriority(int priority) {
        this.priorityLevel = priority;
        checkEmergency();
    }

    public void checkEmergency() {
        if (this.getPriority() == 1) {
            //RGB=RED
            this.setR(255);
            this.setG(0);
            this.setB(0);
            revalidate();
            repaint();
        }
    }

    public void setR(int r) {
        this.R = r;
    }

    public void setG(int g) {
        this.G = g;
    }

    public void setB(int b) {
        this.B = b;
    }

    public String getDriversName() {
        return driver.getName();
    }

    public int getDriversPolitenssLevel() {
        return driver.getPolitenessLevel();
    }

    public Person getPerson() {
        return this.driver;
    }

    public void setPerson(Person p) {
        this.driver = p;
    }

    public String toString() {
        return this.driver.getName();
    }

    @Override
    public void move() {
        this.set_pos_x(length + this.get_pos_x() + 5);
        revalidate();
        repaint();
    }

    @Override
    public void move(VehicleFlowHelper.Direction direction) {

    }

	@Override
	public Direction getCurrentDirection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCurrentDirection(Direction dir) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turn(Direction d, double degree) {
		// TODO Auto-generated method stub
		
	}
}
