
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
        ((Graphics2D) g).scale(0.8,0.8);

        for (Terrain aTerrain : this.terrainList) {
            aTerrain.doDrawing(f);
        }

        for (Terrain aTerrain : this.terrainList){
            for (Object v : aTerrain.getForwardListFlow())
                if (v instanceof Vehicle)
                    ((Vehicle) v).doDrawing(f);
                else {
                    //((TrafficLights) v).doDrawing(f);
                    ((TrafficLights) v).paintComponent(g);
                }
            for (Object v : aTerrain.getBackwardListFlow())
                if (v instanceof Vehicle)
                    ((Vehicle) v).doDrawing(f);
                else {
                    ((TrafficLights) v).paintComponent(g);
                    //((TrafficLights) v).doDrawing(f);
                    /*
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //
                            //                //for (;;) {
                            //                    try {
                            //                        Thread.sleep(2000);
                            //                    } catch (InterruptedException e) {
                            //                        System.out.println("Error: "+e.getLocalizedMessage());
                            //                    }
                            //                    trafficLight.change();
                            //                //}
                        }
                    });
                    thread.start();*/
                }
        } 

    }
}

