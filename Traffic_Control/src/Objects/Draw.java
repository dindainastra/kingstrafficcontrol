
package Objects;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Draw extends JPanel {
   
    // Variables declaration
    private Person p; //temp
    
    //Create arrays for each objects
    ArrayList<SquareJunction> squarejuncions = new ArrayList<>();
    ArrayList<SRoad> sroads = new ArrayList<>();
    ArrayList<Car> cars=new ArrayList<>();
    ArrayList<Motorbike> motorbikes=new ArrayList<>();
    ArrayList<Bike> bikes=new ArrayList<>();
    ArrayList<Lorry> lorries=new ArrayList<>();
    
    //Draw cars, roads, traffic lights
    public Draw() {
        //Draw map network
        //sroads.add(new SRoad(getposx(),getposy(),gettraffic(),gettrafficcolor(),getrotation());
          sroads.add(new SRoad(100,25,00,2,0));
          sroads.add(new SRoad(200,25,01,3,0));
          sroads.add(new SRoad(350,25,11,2,0));
          
          sroads.add(new SRoad(500,25,11,1,90)); 
//          sroads.add(new SRoad(350,75,01,2,90)); --BUG
//        sroads.add(new SRoad(350,275,01,2,90));          
          squarejuncions.add(new SquareJunction(300,25));
        
          //Draw traffic
          cars.add(new Car(p,210,30));
          motorbikes.add(new Motorbike(250,55));  
          lorries.add(new Lorry(240,30));
          bikes.add(new Bike(170,30));
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
        for (Car car : cars) {
            car.doDrawing(f); 
        }
        for (Motorbike motorbike : motorbikes) {
            motorbike.doDrawing(f); 
        }
        for (Lorry lorry : lorries) {
            lorry.doDrawing(f); 
        }
        for (Bike bike : bikes) {
            bike.doDrawing(f); 
        }
    }    
}

