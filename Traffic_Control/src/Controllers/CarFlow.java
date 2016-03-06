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
    	vehicle.getPerson().getTheManager().getNodeList().get(
    			vehicle.getPerson().getTheManager().getNodeList().indexOf(vehicle.getCurrentNode())
    			).removeFromStack(vehicle);
    	vehicle.getPerson().getTheManager().getNodeList().get(
    			vehicle.getPerson().getTheManager().getNodeList().indexOf(vehicle.getCurrentNode())+1
    			).addToStack(vehicle);

    }

    public void run(){
        startFlow();
    }
}