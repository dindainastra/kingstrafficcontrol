/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package new_project;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Draw extends JPanel {
   
    // Variables declaration
    private int numCars=2;
    private int numSRoads=1;
    private int numVRoads=1;
    
    //Create arrays for each objects
    ArrayList<SquareJunction> squarejuncions = new ArrayList<>();
    ArrayList<SRoad> sroads = new ArrayList<>();
    ArrayList<Vehicle> vehicles=new ArrayList<>();
    
    //Make cars, roads, traffic lights
    public Draw() {
        //Draw map network
        //sroads.add(new SRoad(getposx(),getposy(),gettraffic(),gettrafficcolor(),getrotation());
        sroads.add(new SRoad(200,25,01,3,0));
        sroads.add(new SRoad(100,25,00,2,0));
        squarejuncions.add(new SquareJunction(300,25));
        sroads.add(new SRoad(300,25,00,2,0));
        
        //Draw traffic
        vehicles.add(new Vehicle(210,30,2));
        vehicles.add(new Vehicle(250,55,1));  
        vehicles.add(new Vehicle(270,30,3));
    }
    @Override
    public void paintComponent(Graphics g){
        Graphics2D f =(Graphics2D) g;
        super.paintComponent(g);
        for(SRoad SRoad : sroads){
            SRoad.doDrawing(f);
        } 
        for(SquareJunction SquareJunction : squarejuncions){
            SquareJunction.doDrawing(f);
        }
        for (Vehicle vehicle : vehicles) {
            vehicle.doDrawing(f); 
        }
    }    
}
