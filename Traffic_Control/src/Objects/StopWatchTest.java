package Objects;

/**
 * Created by daniella on 04/03/2016.
 */
/*****************************************************************/
/* Copyright 2013 Code Strategies                                */
/* This code may be freely used and distributed in any project.  */
/* However, please do not remove this credit if you publish this */
/* code in paper or electronic form, such as on a web site.      */
/*****************************************************************/
import java.io.IOException;

import org.apache.commons.lang.time.StopWatch;

public class StopWatch {

    public static void main(String[] args) throws IOException {

        StopWatchTest stopWatch = new StopWatchTest();

        System.out.println("STARTING STOPWATCH");
        stopWatch.start();

        sleep();

        System.out.println("SUSPENDING STOPWATCH");
        stopWatch.suspend();
        System.out.println("Stopwatch time: " + stopWatch);
        sleep();
        System.out.println("Stopwatch time: " + stopWatch + " (doesn't change since suspended)");
        System.out.println("RESUMING STOPWATCH");
        stopWatch.resume();

        sleep();

        System.out.println("Stopwatch time: " + stopWatch);
        System.out.println("SPLITTING STOPWATCH");
        stopWatch.split();
        sleep();
        // Note: stopWatch must be split to call toSplitString()
        System.out.println("Stopwatch split time: " + stopWatch.toSplitString()
                + " (reported time doesn't change but stopwatch still running)");
        System.out.println("Stopwatch time: " + stopWatch);
        sleep();
        System.out.println("Stopwatch split time: " + stopWatch.toSplitString()
                + " (reported time doesn't change but stopwatch still running)");
        System.out.println("Stopwatch time: " + stopWatch);
        System.out.println("UNSPLITTING STOPWATCH (removes split effect)");
        stopWatch.unsplit();

        sleep();

        System.out.println("STOPPING STOPWATCH");
        stopWatch.stop();
        System.out.println("Stopwatch time: " + stopWatch);

        System.out.println("RESETTING STOPWATCH");
        stopWatch.reset();
        System.out.println("Stopwatch time: " + stopWatch);

        // Note: stopWatch.start() can now be called since reset() was called.
    }

    public static void sleep() {
        System.out.println("1 second goes by");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}