package Controllers;

import Objects.Draw;
import Objects.Terrain;
import Objects.Vehicle;

public class CarFlow implements Runnable {
    private Draw map;
    private Vehicle vehicle;
    
    public CarFlow(Vehicle v, Draw map){
        this.vehicle = v;
        this.map = map;
    }
    
    /**
     * Resume the cars flow through the system
     * Repaint the map whilst moving
     */
    private void startFlow(){
        //Move the car for for a set period of time?
    	Terrain t = (Terrain) vehicle.getCurrentNode().getNextNodeList().get(0).returnStack().get(0);  //fix that shit
    	
    	// t.getLenght()/vehicle.getLength()-1
    	// (lenght of the terrain    /   vehicle lenght      )   - 1 step.
    	for (int steps= ((t.getLenght()/vehicle.getLength())-1); steps>0; steps--) {
            try {
                //Check what the next node is
               
                //Could pass in the next node to the move method
                //Move method carries the car all the way to the node
                //Then on the next iteration we decide the next node (randomly) and pass it again to the move method
                this.vehicle.move();
                map.revalidate();
                map.repaint();
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                System.out.println("Error: "+e.getLocalizedMessage());
            }
        }
    	
    	//remove this vehicle from the current node
    	vehicle.getPerson().getTheManager().getNodeList().get(
    			vehicle.getPerson().getTheManager().getNodeList().indexOf(vehicle.getCurrentNode())
    			).removeFromStack(vehicle);
    	
    	//add this vehicle to the next node    	
    	vehicle.getPerson().getTheManager().getNodeList().get(
    			vehicle.getPerson().getTheManager().getNodeList().indexOf(vehicle.getNextNode())
    			).addToStack(vehicle);
    	
    	vehicle.getPerson().getTheManager().printMyNetwork2();

    }

    public void run(){
        startFlow();
    }
}