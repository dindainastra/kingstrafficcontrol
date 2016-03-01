
package Objects;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

public class Draw extends JPanel {
   
    // Variables declaration
    private Person p; //temp
    Timer timer=null;
    //Create arrays for each objects
    ArrayList<SquareJunction> squarejuncions = new ArrayList<>();
    ArrayList<SRoad> sroads = new ArrayList<>();
    ArrayList<CRoad> croads = new ArrayList<>();
    ArrayList<Car> cars=new ArrayList<>();
    ArrayList<Emergency> emergencies =new ArrayList<>();
    ArrayList<Motorbike> motorbikes=new ArrayList<>();
    ArrayList<Bike> bikes=new ArrayList<>();
    ArrayList<Lorry> lorries=new ArrayList<>();
    
    Car car1=new Car(p,100,230);
    
    //Draw cars, roads, traffic lights
    public Draw() {
        //Draw horizontal roads
        //sroads.add(new SRoad(getposx(),getposy(),gettraffic(),gettrafficcolor(),getrotation());  
          sroads.add(new SRoad(100,225,00,2,0));
          sroads.add(new SRoad(200,225,01,3,0));
          sroads.add(new SRoad(350,225,10,3,0));
         /* sroads.add(new SRoad(450,225,01,2,0));
          sroads.add(new SRoad(350,75,00,2,0));
          sroads.add(new SRoad(450,75,00,2,0));
          //Draw vertical roads
          sroads.add(new SRoad(600,125,11,1,90)); 
          sroads.add(new SRoad(350,125,01,2,90));
          sroads.add(new SRoad(300,325,01,2,90));
          */
          //Draw curved roads
          /*croads.add(new CRoad(500,75,360));
          croads.add(new CRoad(500,175,270));
          croads.add(new CRoad(250,225,270));
          croads.add(new CRoad(250,275,90));
          
          croads.add(new CRoad(300,75,90));
          */
          //draw roundabout
          /*croads.add(new CRoad(225,400,90));
          croads.add(new CRoad(225,400,180));
          croads.add(new CRoad(225,400,270));
          croads.add(new CRoad(225,400,360));
          */        
                        
          //Draw junctions
          squarejuncions.add(new SquareJunction(300,225));
          
          //Draw traffic
          
          //cars.add(new Car(p,210,230));
          /*motorbikes.add(new Motorbike(250,255));  
          lorries.add(new Lorry(240,230));
          bikes.add(new Bike(170,230));
          emergencies.add(new Emergency(120,230));
                  */
    }
    
    public void move(){
        timer=new Timer(10, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (car1.getpos_x()>400) {
                    car1.setpos_x(100);
                }
                else {
                    int x=car1.getpos_x();
                car1.setpos_x(x+1);
                }   
                repaint();
            }
        });
        /*try {
            timer.wait(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Draw.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        timer.start();
        timer.setInitialDelay(100);
        timer.setDelay(500);
        
        }    
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D f =(Graphics2D) g;
        super.paintComponent(g);
        //draw background 
        g.setColor(new Color (50,150,50));
        g.fillRect(0, 0, 700, 700);   
        
        for(SRoad SRoad : sroads){
            SRoad.doDrawing(f);
        } 
        /*
        for(CRoad CRoad : croads){
            CRoad.doDrawing(f);
        }*/
        for(SquareJunction SquareJunction : squarejuncions){
            SquareJunction.doDrawing(f);
        }
        car1.doDrawing(f);
        move();
        /*
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
        for (Emergency emergency : emergencies) {
            emergency.doDrawing(f); 
        }*/ 
    }   
}

