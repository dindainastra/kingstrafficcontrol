
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
        //NOT OPTIMAL - EVERYTHING IS REPAINTED ON EVERY REPAINT CALL
        //System.out.println("Repaint called?");

        Graphics2D f = (Graphics2D) g;
        super.paintComponent(g);
        //draw background 
        g.setColor(new Color (50,150,50));
        g.fillRect(0, 0, 1500, 1000);
        ((Graphics2D) g).scale(0.8,0.8);

        for (Terrain aTerrain : this.terrainList) {
            //aTerrain.doDrawing(f);
            if (aTerrain instanceof StraightRoad) {
                ((StraightRoad) aTerrain).paintComponent(g);
            }else if (aTerrain instanceof CornerRoad){
                ((CornerRoad) aTerrain).paintComponent(g);
            }else if (aTerrain instanceof SquareJunction){
                ((SquareJunction) aTerrain).paintComponent(g);
            }
        }

        for (Terrain aTerrain : this.terrainList){
            for (Object v : aTerrain.getForwardListFlow())
                if (v instanceof Vehicle) {
                    ((Car) v).paintComponent(g);
                }else {
                	((TrafficLights) v).paintComponent(g);
                }
            for (Object v : aTerrain.getBackwardListFlow())
                if (v instanceof Vehicle)
                    ((Vehicle) v).doDrawing(f);
                else
                    ((TrafficLights) v).paintComponent(g);
        } 

    }
}

