package Objects;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Bus extends JPanel implements Vehicle {

	private Person driver;

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
		return this.driver;
	}

	@Override
	public void setPerson(Person p) {
		this.driver = p;	
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

	
	
}
