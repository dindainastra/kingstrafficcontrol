
package Objects;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Draw extends JPanel {

    private ArrayList<Vehicle> vehicleList; 
    private ArrayList<Terrain> terrainList; 

    //Draw cars, roads, traffic lights
    public Draw(ArrayList<Vehicle> aVehicleList, ArrayList<Terrain> aTerrainList) {
        this.vehicleList = aVehicleList;
        this.terrainList = aTerrainList;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D f = (Graphics2D) g;
        super.paintComponent(g);
        //draw background 
        g.setColor(new Color (50,150,50));
        g.fillRect(0, 0, 1500, 1000);
        
        for(Terrain aTerrain : this.terrainList){
        	aTerrain.doDrawing(f);
        } 

        for (Vehicle aVehicle : vehicleList) {
        	aVehicle.doDrawing(f);
        }
    }
}

