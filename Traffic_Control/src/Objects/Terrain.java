package Objects;

import java.awt.Graphics2D;

public interface Terrain {

	public void setInWhichNodeLocated();
	public int getInWhichNodeLocated();
	public int getPerson();
	public void setPerson();
	public void doDrawing(Graphics2D g);
}
