package Controllers;

import Objects.Car;
import Objects.Draw;
import Objects.Vehicle;

public class CarFlow implements Runnable {
    Car car;
    Draw map;
    Vehicle vehicle;

    public CarFlow(Car c){
        this.car = c;
    }

    public CarFlow(Car c, Draw map){
        this.car = c;
        this.map = map;
    }

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
        for (int steps=7; steps>0; steps--) {
            try {
                //Could pass in the next node to the move method
                //Move method carries the car all the way to the node
                //Then on the next iteration we decide the next node (randomly) and pass it again to the move method
                car.move();
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
