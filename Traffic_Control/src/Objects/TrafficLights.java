
package Objects;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class TrafficLights extends JPanel implements Runnable{

    private int pos_x, pos_y, rotates;
    private final int width = 3, length = 50;
    private int R,G,B;
    private final int Red = 1;
    private final int Yellow = 2;
    private final int Green = 3;
    private final int YellowReverse = 4;
    private int currentColour = Red;
    private final int RED_SECS = 30;
    private final int YELLOW_SECS = 30;
    private final int GREEN_SECS = 30;
    private final int YellowReverse_SECS = 30;
    private TrafficLights resumeNextLight;
    private boolean suspendRequest;
    private int checkFirst;
    private long delay;
    private int signal;
    private int numberOfWays;

    //set traffic light colour and shape
    /**
     * Constructor. Sets the position of the traffic light. 
     * RGB values to be used for painting the GUI.
     * @param x_coordinate
     * @param y_coordinate
     * @param rotation
     */
    public TrafficLights(int x_coordinate, int y_coordinate, int numberOfWays, int signal, int rotation, long delay){
        this.pos_x = x_coordinate;
        this.pos_y = y_coordinate;
        this.rotates = rotation;
        this.signal = signal;
        this.delay = delay;
        this.numberOfWays = numberOfWays;
        //this.currentColour = RGB;
    }

    /**
     * This method takes the initial state of the traffic lights (Red) and makes decisions accordingly
     * @return
     */
    public int change() {
        switch (currentColour) {
            case Red:
                currentColour = Yellow;
                //this.checkSuspended();
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
                //resumeNextLight.requestResume();
                //this.requestSuspended();
        }
        return currentColour;
    }

    public int getCurrentColour() {
        return currentColour;
    }
    /**
     * This method iterates the change of colours.
     */

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
        g.setTransform(old3);
    }

    @Override
    public void paintComponent(Graphics g){
        doDrawing((Graphics2D)g);
    }


    @Override
    public void run() {
            try {
                if(numberOfWays == 4){
                    //signal will change after 16 if the traffic light is in a junction
                    if(signal ==1){
                        this.currentColour = 3;
                    } else if(signal ==5 || signal ==9 || signal ==13){
                        this.currentColour = 1;
                    } else if(signal==3 || signal == 4 || signal ==16 || signal == 1){
                        this.currentColour = change();
                    }
                    Thread.sleep(getDelay());
                    if(signal == 16){
                        signal = 1;
                    } else {
                        signal++;
                    }
                } else if (numberOfWays == 3) {
                    //signal will change after 12 if the traffic light is in a three-way junction
                    if(signal ==1){
                        this.currentColour = 3;
                    } else if(signal ==5 ||signal ==9){
                        this.currentColour = 1;
                    } else if(signal==3 || signal == 4 || signal ==12 || signal == 1){
                        this.currentColour = change();
                    }
                    Thread.sleep(getDelay());
                    if(signal == 12){
                        signal = 1;
                    } else {
                        signal++;
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Error: "+e.getLocalizedMessage());
            }
    }

    private long getDelay() {
        return delay;
    }
}



