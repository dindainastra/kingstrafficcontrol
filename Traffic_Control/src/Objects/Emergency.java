package Objects;
import java.awt.*;

import javax.swing.JPanel;

public class Emergency extends JPanel implements Vehicle {

	// Variables declaration
    private final int pos_x,pos_y;
    private final int R = 255, G = 0, B=0; //Red
    private final int length = 20 ,width = 15;
    
    //set car position
       Emergency(int x_coordinate, int y_coordinate){
            this.pos_x = x_coordinate;
            this.pos_y = y_coordinate;
       }
       
    //draw Vehicle
    protected void doDrawing(Graphics g){
        g.setColor(new Color (R,G,B));
        g.fillRect(pos_x, pos_y, length, width);   
    }

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
		return null;
	}

	@Override
	public void setPerson(Person p) {
		// TODO Auto-generated method stub
		
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