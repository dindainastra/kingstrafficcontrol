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
    	
    	for (int steps= (t.getLenght()/vehicle.getLength()); steps>0; steps--) {
            try {
                //Check what the next node is
                Node currentNode = vehicle.getNextNode();
                Node nextNode = vehicle.getNextNode();
                System.out.println("My current node is "+currentNode.toString());
                System.out.println("My next node is "+nextNode.toString());

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
    }

    public void run(){
        startFlow();
    }
}
