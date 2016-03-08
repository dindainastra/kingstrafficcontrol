
package Objects;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Draw extends JPanel {

    private ArrayList<Terrain> terrainList = new ArrayList<Terrain>();

    //Draw cars, roads, traffic lights
    public Draw(ArrayList<Terrain> aTerrainList) {
        this.terrainList = aTerrainList;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D f = (Graphics2D) g;
        super.paintComponent(g);
        //draw background 
        g.setColor(new Color (50,150,50));
        g.fillRect(0, 0, 1500, 1000);
        
        for (Terrain aTerrain : this.terrainList){
        	aTerrain.doDrawing(f);
            for (Object v : aTerrain.getForwardListFlow())
                if (v instanceof Vehicle)
                    ((Vehicle) v).doDrawing(f);
                else
                    ((TrafficLights) v).doDrawing(f);
            for (Object v : aTerrain.getBackwardListFlow())
                if (v instanceof Vehicle)
                    ((Vehicle) v).doDrawing(f);
                else
                    ((TrafficLights) v).doDrawing(f);
        } 

    }
}

