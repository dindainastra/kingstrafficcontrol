package Interfaces;

import Controllers.VehicleFlowHelper;
import Objects.Person;

import java.awt.*;

public interface Vehicle {

    int getPriority();

    void setPriority(int priority);

    Person getPerson();

    void setPerson(Person p);

    void doDrawing(Graphics2D g);

    int get_pos_x();

    void set_pos_x(int x);

    int get_pos_y();

    void set_pos_y(int y);

    void move();

    void move(VehicleFlowHelper.Direction direction);

    int getLength();

    boolean getLock();

    void setLock(boolean lock);

    boolean amIMoving();

    void setThatIAmMoving(boolean lock);

    void turnCorner(double angle, int centerX, int centerY, int radius);

    void bend(VehicleFlowHelper.Direction d, double degree);

    VehicleFlowHelper.Direction getCurrentDirection();
    void setCurrentDirection(VehicleFlowHelper.Direction dir);

    void turn(VehicleFlowHelper.Direction d, double degree);
}
