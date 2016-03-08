
package Objects;

import java.awt.Graphics2D;

public class TrafficLights implements Runnable{

    public static final int Red = 1;
    public static final int Yellow = 3;
    public static final int Green = 2;
    public static final int Orange = 4;
    private int currentColour = Red;
    private final static int RED_SECS = 15;
    private final static int YELLOW_SECS = 5;
    private final static int GREEN_SECS = 15;
    private Thread runner;

    /**
     * This method takes the initial state of the traffic lights (Red) and makes decisions accordingly
     * @return
     */
    public int change() {
        switch (currentColour) {
            case Red:
                currentColour = Orange;
                System.out.println("Red and Yellow ");
                break;
            case Orange:
                currentColour = Green;
                System.out.println("Green");

                break;
            case Green:
                currentColour = Yellow;
                System.out.println("Yellow");

                break;
            case Yellow:
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

//    public static void main(String[] args){
//        TrafficLights a = new TrafficLights();
//        a.run();
//    }

    public void doDrawing(Graphics2D g) {
        // TODO Auto-generated method stub

    }

}



