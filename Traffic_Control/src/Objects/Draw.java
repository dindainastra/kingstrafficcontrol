
package Objects;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;


public class Draw extends JPanel {

    private ArrayList<Terrain> terrainList = new ArrayList<Terrain>();

    //GridLayout gd=new GridLayout(0,2);
    //Draw cars, roads, traffic lights
    public Draw(ArrayList<Terrain> aTerrainList) {
        this.terrainList = aTerrainList;

       // this.setLayout(gd);

        new Buttons();
    }

    //Draw Buttons


    @Override
    protected void paintComponent(Graphics g) {

        //buttons
        /*replayButton = new JButton();
        URL rewindURL = getClass().getResource("../Resources/rewind.PNG");
        replayButton.setIcon(new ImageIcon(rewindURL));
        add(replayButton);
        replayButton.setBounds(200,200,50,40);
        */

        Graphics2D f = (Graphics2D) g;
        super.paintComponents(g);

        //draw background 
        g.setColor(new Color(50, 150, 50));
        g.fillRect(0, 0, 1500, 1000);
        ((Graphics2D) g).scale(0.8, 0.8);

        for (Terrain aTerrain : this.terrainList) {
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

        f.dispose();

    }

}

