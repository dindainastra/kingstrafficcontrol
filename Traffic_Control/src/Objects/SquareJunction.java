package Objects;
import java.awt.*;
import javax.swing.*;

public class SquareJunction implements Terrain{

    // Variables declaration
    //Set size of road
    private int xStart,yStart;
    private final int road_width = 50;

    public SquareJunction(int x_Start, int y_Start){
        this.xStart = x_Start;
        this.yStart = y_Start;
    }   
    
    public void doDrawing(Graphics2D g){
        Graphics2D road=(Graphics2D) g;
        Graphics2D lane_divider=(Graphics2D) g;
        Graphics2D road_border=(Graphics2D) g;
        
        //draw junction
        road.setColor(Color.gray);
        road.fillRect(xStart, yStart, road_width , road_width);
        
        //draw road divider
        float[] dash = {4f, 0f, 2f};
        BasicStroke bs3 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f, dash, 2f);
       
        //draw road border
        road_border.setStroke(bs3);
        road_border.setColor(Color.white);
        road_border.drawLine(xStart,yStart,xStart,yStart+road_width-1);//left
        road_border.drawLine(xStart+road_width,yStart,xStart+road_width,yStart+road_width-1);//right
        road_border.drawLine(xStart,yStart,xStart+road_width-1,yStart);//top
        road_border.drawLine(xStart,yStart+road_width,xStart+road_width-1,yStart+road_width);//bottom
        
        //draw road lines
        lane_divider.setStroke(bs3);
        lane_divider.setColor(Color.white);
        lane_divider.drawLine(xStart+road_width/2,yStart,xStart+road_width/2,yStart+road_width-1);//horizontal
        lane_divider.drawLine(xStart,yStart+road_width/2,xStart+road_width,yStart+road_width/2);//vertical
    }

	@Override
	public void setInWhichNodeLocated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getInWhichNodeLocated() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPerson() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPerson() {
		// TODO Auto-generated method stub
		
	}      
}