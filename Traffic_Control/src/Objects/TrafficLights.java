package Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class TrafficLights extends JPanel implements Runnable {

    private final int width = 3, length = 50;
    private final int Red = 1;
    private final int Yellow = 2;
    private final int Green = 3;
    private final int YellowReverse = 4;
    private int pos_x, pos_y, rotates;
    private int currentColour;
    private TrafficLights previousTrafficLight;
    private TrafficLights nextTrafficLight;

    private int delayForGreenLight = 4;
    private boolean canIChange = false;

    private long delay;
    private int signal = 1;

    /**
     * Constructor. Sets the position of the traffic light.
     * RGB values to be used for painting the GUI.
     *
     * @param x_coordinate
     * @param y_coordinate
     * @param rotation
     */
    public TrafficLights(int x_coordinate, int y_coordinate, int signal, int rotation, long delay) {
        this.pos_x = x_coordinate;
        this.pos_y = y_coordinate;
        this.rotates = rotation;
        this.currentColour = signal;
        this.delay = delay;
    }

    /**
     * This method takes the initial state of the traffic lights (Red) and makes decisions accordingly
     *
     * @return
     */
    public int change() {
        switch (currentColour) {
            case Red:
                currentColour = Yellow;
                break;
            case Yellow:
                currentColour = Green;
                break;
            case Green:
                currentColour = YellowReverse;
                break;
            case YellowReverse:
                currentColour = Red;
        }
        return currentColour;
    }

    /**
     * Draw of Traffic Light. RGB used.
     * * @param g
     */
    public void doDrawing(Graphics2D g) {
        AffineTransform old3 = g.getTransform();
        g.rotate(Math.toRadians(rotates), pos_x, pos_y);

        int R, G, B;
        if (currentColour == Red) {
            R = 255;
            G = 0;
            B = 0;
        } else if (currentColour == Green) {
            R = 0;
            G = 255;
            B = 0;
        } else {//Yellow
            R = 255;
            G = 215;
            B = 0;
        }

        g.setColor(new Color(R, G, B));
        g.fillRect(pos_x, pos_y, width, length);
        g.setTransform(old3);
    }

    @Override
    public void paintComponent(Graphics g) {
        doDrawing((Graphics2D) g);

    }

    /**
     * Run method
     */
    @Override
    public void run() {
        for (; ; ) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            doRun();
        }
    }

    /**
     * Run the traffic light simulation
     */
    public void doRun() {
        //There are four states: 1 (RED), 2 (YELLOW AFTER RED), 3 GREEN, 4 (YELLOW AFTER GREEN)
        if (currentColour == 1) {
            // If current colour is Red, then it need to check
            // 1. next traffic light colour, to make sure the next traffic light's changing as it should be
            // 2. previous traffic light colour, to make sure that it will change as it should be
            if (canIChange && nextTrafficLight.currentColour == 1) {
                nextTrafficLight.currentColour = nextTrafficLight.change();
            }
            canIChange = false;
            if (isItMyTurnToChange()) {
                this.currentColour = change();
            }
        } else if (currentColour == 2) {
            //If the previous colour is Yellow after red, change
            this.currentColour = change();
        } else if (currentColour == 3) {
            //If the previous colour is green,
            //wait for the delay
            //then change
            if (signal <= delayForGreenLight) {
                signal++;
            } else {
                this.currentColour = change();
                signal = 0;
            }
        } else if (currentColour == 4) {
            //If the previous colour is Yellow after green, change
            this.currentColour = change();
            this.delayForGreenLight = 3;
            canIChange = true;
        }
    }

    /**
     * @return
     */
    public long getDelay() {
        return delay;
    }

    /**
     * To know which traffic light that should come after this traffic light
     *
     * @param nextTrafficLight
     */
    public void nextTrafficLightIs(TrafficLights nextTrafficLight) {
        this.nextTrafficLight = nextTrafficLight;
    }

    /**
     * @param previousTrafficLight
     */
    public void previousTrafficLightIs(TrafficLights previousTrafficLight) {
        this.previousTrafficLight = previousTrafficLight;
    }

    /**
     * Boolean check to know if it is already this traffic light turn to change
     *
     * @return
     */
    public boolean isItMyTurnToChange() {
        if (previousTrafficLight.currentColour == 1 && previousTrafficLight.canIChange) {
            return true;
        } else {
            return false;
        }
    }

    public int getCurrentColour() {
        return currentColour;
    }

    /**
     * Set current colour
     *
     * @param currentColour
     */
    public void setCurrentColor(int currentColour) {
        this.currentColour = currentColour;
    }

    /**
     * Set the delay for green light
     *
     * @param delayForGreenLight
     */
    public void setGreenLightDelay(int delayForGreenLight) {
        this.delayForGreenLight = delayForGreenLight;
    }
}