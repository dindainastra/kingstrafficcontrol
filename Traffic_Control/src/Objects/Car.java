package Objects;

import Controllers.VehicleFlowHelper;
import Interfaces.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Car extends JPanel implements Vehicle {

    private static final int INCREMENT_BY = 5;
    public static Boolean draw = false;
    private final int length = 20, width = 15;
    private double rotate = 0.0;
    private VehicleFlowHelper.Direction currentDirection = VehicleFlowHelper.Direction.RIGHT;
    private volatile Boolean destination = false;
    private Person driver;
    private int priorityLevel;
    private int pos_x, pos_y;
    private int R = 173, G = 216, B = 230; //pastel blue
    private volatile boolean lock;
    private volatile boolean amIMoving;

    public Car(Person p, int x_coordinate, int y_coordinate) {

        driver = p;
        priorityLevel = 0; //default no priority
        this.pos_x = x_coordinate;
        this.pos_y = y_coordinate;
        lock = false;
        amIMoving = false;
    }

    public void move() {
        this.pos_x += 2;
    }

    public void setRotate(double r) {
        rotate = Math.toRadians(r);
    }

    public VehicleFlowHelper.Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(VehicleFlowHelper.Direction dir) {
        currentDirection = dir;
    }

    /**
     * Drive the vehicle to the given direction by either adding or subtracting one on
     * either the X or Y axis
     * @param d
     */
    public void move(VehicleFlowHelper.Direction d) {
        if (d == VehicleFlowHelper.Direction.LEFT) {
            this.pos_x -= 1;
        } else if (d == VehicleFlowHelper.Direction.RIGHT) {
            this.pos_x += 1;
        } else if (d == VehicleFlowHelper.Direction.DOWN) {
            this.pos_y += 1;
        } else if (d == VehicleFlowHelper.Direction.UP) {
            this.pos_y -= 1;
        }
    }

    public void turnCorner(double angle, int centerX, int centerY, int radius) {
        if (radius == 100) {
            this.pos_x = (int) ((Math.cos(angle) * radius) + centerX);
            this.pos_y = (int) ((Math.sin(angle) * radius) + centerY);
        } else {
            this.pos_y = (int) ((Math.cos(angle) * radius) + centerY);
            this.pos_x = (int) ((Math.sin(angle) * radius) + centerX);
        }
//		this.pos_x = (int) ((Math.cos(angle) * radius/2) + centerX);
//		this.pos_y = (int) ((Math.sin(angle) * radius/2) + centerY);
        repaint();
    }

    public void turn(VehicleFlowHelper.Direction d, double degree) {
        double turnDir = degree;

        if (degree == 0) {
            turnDir = Math.toRadians(90);
        }

        if (d == VehicleFlowHelper.Direction.UP || d == VehicleFlowHelper.Direction.LEFT) {
            rotate = -turnDir;
        } else if (d == VehicleFlowHelper.Direction.DOWN || d == VehicleFlowHelper.Direction.RIGHT) { //works
            rotate = turnDir;
        }

        this.repaint();
    }

    public void bend(VehicleFlowHelper.Direction d, double degree) {
        //Bend based on direction
        if (d == VehicleFlowHelper.Direction.RIGHT) {
            rotate += Math.toRadians(degree);
        } else if (d == VehicleFlowHelper.Direction.LEFT) {
            rotate -= Math.toRadians(degree);
        }

//		this.pos_x += degree;
//		this.pos_y += degree;

        this.repaint();
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
    public synchronized boolean getLock() {
        return lock;
    }

    @Override
    public synchronized void setLock(boolean lock) {
        this.lock = lock;
    }

    @Override
    public boolean amIMoving() {
        return amIMoving;
    }

    @Override
    public void setThatIAmMoving(boolean flag) {
        amIMoving = flag;
    }

    public int getWidth() {
        return this.width;
    }

    public int getPriority() {
        return this.priorityLevel;
    }

    public void setPriority(int priority) {
        this.priorityLevel = priority;
        checkEmergency();
    }

    private void checkEmergency() {
        if (this.getPriority() == 1) {
            this.setR(255);
            this.setG(0);
            this.setB(0);
        }
    }

    private void setR(int r) {
        this.R = r;
    }

    private void setG(int g) {
        this.G = g;
    }

    private void setB(int b) {
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
    public void doDrawing(Graphics2D g) {
//		g.setColor(new Color(R, G, B));
//		g.fillRect(pos_x, pos_y, length, width);
//
//
//		Color textColor = Color.BLACK;
//		g.setColor(textColor);  //greg
//		g.drawString(this.getPerson().getName().substring("Person".length()), pos_x, pos_y+width); //greg
    }


    @Override
    public void paintComponent(Graphics g) {
        //System.out.println("Repainting car object - turn is "+rotate);
        super.paintComponent(g);

        Graphics2D gd = (Graphics2D) g;
        AffineTransform at = gd.getTransform();

        //gd.translate(this.getWidth()/2, this.getHeight()/2);
        if (rotate != 0) {
            gd.rotate(rotate, pos_x, pos_y);
        }
//		gd.translate(getWidth()/2, getHeight()/2);

        gd.setColor(new Color(R, G, B));
        gd.fillRect(pos_x, pos_y, length, width);

        gd.setTransform(at);

        Color textColor = Color.BLACK;
        g.setColor(textColor);  //greg
        g.drawString(this.getPerson().getName().substring("Person".length()), pos_x, pos_y); //greg
    }
}
