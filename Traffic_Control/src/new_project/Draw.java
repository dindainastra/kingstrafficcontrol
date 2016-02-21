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
    private int numHRoads=1;
    private int numVRoads=1;
    
    //Create arrays for each objects
    ArrayList<SquareJunction> squarejuncions = new ArrayList<>();
    ArrayList<TrafficLight> trafficlights = new ArrayList<>();
    ArrayList<HRoad> hroads = new ArrayList<>(numHRoads);
    ArrayList<VRoad> vroads = new ArrayList<>(numVRoads);
    ArrayList<Vehicle> vehicles=new ArrayList<>(numCars);
    
    //Make cars, roads, traffic lights
    public Draw() {
        
        hroads.add(new HRoad(200,25));
        hroads.add(new HRoad(100,25));
        vroads.add(new VRoad(300,75));
        squarejuncions.add(new SquareJunction(300,25));
        trafficlights.add(new TrafficLight(295,25,0,255,0));
        trafficlights.add(new TrafficLight(295,50,255,0,0));//R,G,B
        
        vehicles.add(new Vehicle(210,30,2));
        vehicles.add(new Vehicle(250,55,1));  
        vehicles.add(new Vehicle(270,30,3));
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(VRoad VRoad : vroads){
            VRoad.doDrawing(g);
        }
        for(HRoad HRoad : hroads){
            HRoad.doDrawing(g);
        } 
        for(SquareJunction SquareJunction : squarejuncions){
            SquareJunction.doDrawing(g);
        }
        for(TrafficLight TrafficLight : trafficlights){
            TrafficLight.doDrawing(g);
        }
        for (Vehicle vehicle : vehicles) {
            vehicle.doDrawing(g); 
        }
    }    
}
