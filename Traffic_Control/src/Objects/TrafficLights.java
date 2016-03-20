
package Objects;

//import org.junit.internal.runners.statements.RunAfters;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class TrafficLights extends JPanel implements Runnable{

    private int pos_x, pos_y, rotates;
    private final int width = 3, length = 50;
    public final int Red = 1;
    public final int Yellow = 2;
    public final int Green = 3;
    public final int YellowReverse = 4;
    private int currentColour = Red;
    private final int RED_SECS = 30;
    private final int YELLOW_SECS = 30;
    private final int GREEN_SECS = 30;
    private final int YellowReverse_SECS = 30;
    private TrafficLights resumeNextLight;


    //set traffic light colour and shape
    /**
     * Constructor. Sets the position of the traffic light. 
     * RGB values to be used for painting the GUI.
     * @param x_coordinate
     * @param y_coordinate
     * @param RGB
     * @param rotation
     */
    public TrafficLights(int x_coordinate, int y_coordinate, int RGB, int rotation){
        this.pos_x = x_coordinate;
        this.pos_y = y_coordinate;
        this.rotates = rotation;
        this.currentColour = RGB;
    }

    public void setNextLight(TrafficLights NextLight) {
        resumeNextLight = NextLight;
    } // end setOtherLight.
    /**
     * This method takes the initial state of the traffic lights (Red) and makes decisions accordingly
     * @return
     */
    public int change() {
        switch (currentColour) {
            case Red:
                currentColour = Yellow;
                //System.out.println("Yellow ");
                break;
            case Yellow:
                currentColour = Green;
                //System.out.println("Green");

                break;
            case Green:
                currentColour = YellowReverse;
                //System.out.println("Yellow");

                break;
            case YellowReverse:
                currentColour = Red;
                //System.out.println("Red");
        }
        return currentColour;
    }

    public int getCurrentColour() {
        return currentColour;
    }
    /**
     * This method iterates the change of colours.
     */


/*
    public void run() {
//    	System.out.println("Red");
//    	System.out.println(Red);

        for (;;) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Error: "+e.getLocalizedMessage());
            }

            //System.out.println(this.change());
        }
    }*/

    private int getSecs() {

        switch (currentColour) {

            case Red:
            default:

                return RED_SECS * 100;

            case Yellow:

                return YELLOW_SECS * 100;

            case Green:

                return GREEN_SECS * 100;

            case YellowReverse:
                return YellowReverse_SECS *100;
        }
    }

    /*

    public void start() {

        runner = new Thread((Runnable) this);
        runner.start();
    }*/

    /**
     * used for testing the sequence
     * @param args
     */
    /*
    public static void main(String[] args){
        TrafficLights a = new TrafficLights();
        a.run();
    }*/


 /**
  * Draw of Traffic Light. RGB used.
  * @param g
  */
 public void doDrawing(Graphics2D g){
        AffineTransform old3 = g.getTransform();
        g.rotate(Math.toRadians(rotates),pos_x,pos_y);
     int R, G, B;
     if (currentColour==Red){
         R = 255; G=0; B=0;
     }else if (currentColour==Green){
         R = 0; G=255; B=0;
     }else {//Yellow
         R = 255; G=215; B=0;
     }

        g.setColor(new Color (R,G,B));
        g.fillRect(pos_x, pos_y, width, length);
     System.out.format("masuk sini woy %d %d %d %d %d %d %d %n", pos_x, pos_y, width, length,R,G,B);
        g.setTransform(old3);
    }

    @Override
    public void paintComponent(Graphics g){
        doDrawing((Graphics2D)g);
    }


    @Override
    public void run() {
        for (;;) {
            try {
                this.currentColour = change();
                System.out.println("Changing to -"+currentColour);
                repaint();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Error: "+e.getLocalizedMessage());
            }

            System.out.println(this.change());

        }
    }
}



