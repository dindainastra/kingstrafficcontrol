
package Objects;

import java.awt.*;

public class TrafficLights implements Runnable{
	
	private static int pos_x, pos_y, rotates;
    private static final int width = 3, length = 50;
    private static int R,G,B;
    public static final int Red = 1;
    public static final int Yellow = 2;
    public static final int Green = 3;
    public static final int YellowReverse = 4;
    private int currentColour = Red;
    private final static int RED_SECS = 30;
    private final static int YELLOW_SECS = 35;
    private final static int GREEN_SECS = 35;
    private final static int YellowReverse_SECS = 30;
    private Thread runner;

    
  //set traffic light colour and shape
    static void trafficlightgui(int x_coordinate, int y_coordinate, int RGB, int rotation){    
        TrafficLights.pos_x = x_coordinate;
        TrafficLights.pos_y = y_coordinate;
        rotates = rotation;
        if (RGB==1){//RED
            R = 255; G=0; B=0;
        }else if (RGB==2){//GREEN
            R = 0; G=255; B=0;
        }else if (RGB ==3){//AMBER
            R = 255; G=215; B=0;
        }
    }
    /**
     * This method takes the initial state of the traffic lights (Red) and makes decisions accordingly
     * @return
     */
    public int change() {
        switch (currentColour) {
            case Red:
                currentColour = Yellow;
                System.out.println("Yellow ");
                break;
            case Yellow:
                currentColour = Green;
                System.out.println("Green");

                break;
            case Green:
                currentColour = YellowReverse;
                System.out.println("Yellow");

                break;
            case YellowReverse:
                currentColour = Red;
                System.out.println("Red");
        }
        return currentColour;
    }

    public int getCurrentColour() {
        return currentColour;
    }
    /**
     *
     */

    public void run() {
        System.out.println("Red");

//        while (this.currentColour != Red) {
//            this.change();
//        }

        for (;;) {
            ////System.out.println(this.currentColour);
            //System.out.println(this.currentColour);
            this.currentColour = change();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Error: "+e.getLocalizedMessage());
            }

            this.change();
        }
    }

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

    public void start() {

        runner = new Thread((Runnable) this);
        runner.start();
    }

    public void stop() {

        if (runner != null) {

            runner.stop();
            //runner = null;
        }
    }

 //   public static void main(String[] args){
 //       TrafficLights a = new TrafficLights();
 //       a.run();
 //   }

  //draw traffic light
    protected static void doDrawing(Graphics2D g){
        //AffineTransform old3 = g.getTransform();
        //g.rotate(Math.toRadians(rotates),pos_x,pos_y);
        g.setColor(new Color (R,G,B));
        g.fillRect(pos_x, pos_y, width, length); 
        //g.setTransform(old3);
    }
}



