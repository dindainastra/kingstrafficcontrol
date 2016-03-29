package Objects;

import java.awt.Graphics2D;

public interface Vehicle {

	int getPriority();
	void setPriority(int priority);
	Person getPerson();
	void setPerson(Person p);
	void doDrawing(Graphics2D g);
	void set_pos_x(int x);
	int get_pos_x();
	void move();
	int getLength();
	boolean getLock();
	void setLock(boolean lock);
	boolean amIMoving();
	void setThatIAmMoving(boolean lock);
}
