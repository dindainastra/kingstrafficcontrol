package Objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrafficLightsTest {
    int pos_x;
    int pos_y;
    int rotates;
    int delay;
    //int currentColour =1;

    TrafficLights light1 = new TrafficLights(pos_x, pos_y, 1, rotates, delay);
    TrafficLights light2 = new TrafficLights(pos_x, pos_y, 2, rotates, delay);
    TrafficLights light3 = new TrafficLights(pos_x, pos_y, 1, rotates, delay);
    TrafficLights light4 = new TrafficLights(pos_x, pos_y, 1, rotates, delay);

    @Test
    public void testChange() throws Exception {

        assertEquals(2, light2.getCurrentColour());
        light2.change();
        assertEquals(3, light2.getCurrentColour());
        light2.change();
        assertEquals(4, light2.getCurrentColour());
        light2.change();
        assertEquals(1, light2.getCurrentColour());
    }

    @Test
    public void testDoRun() throws Exception {
        light2.nextTrafficLightIs(light3);
        light2.previousTrafficLightIs(light1);
        light1.canIChange = true;
        light2.doRun();
        assertFalse(light2.canIChange);
        assertEquals(3, light2.getCurrentColour());
        light2.doRun();
        assertEquals(3, light2.getCurrentColour());
        light2.doRun();
        assertEquals(3, light2.getCurrentColour());
        light2.doRun();
        assertEquals(3, light2.getCurrentColour());
        light2.doRun();
        assertEquals(3, light2.getCurrentColour());
        light2.doRun();
        assertEquals(4, light2.getCurrentColour());
        light2.doRun();
        assertEquals(1,light2.getCurrentColour());
        assertTrue(light2.canIChange);
    }

    @Test
    public void testIsItMyTurnToChange() throws Exception {
        light2.nextTrafficLightIs(light3);
        light2.previousTrafficLightIs(light1);
        light1.canIChange = true;
        assertTrue(light2.isItMyTurnToChange());
    }

    @Test
    public void testOneGreenWhileOtherRed() throws Exception{
        light1.nextTrafficLightIs(light2);
        light1.previousTrafficLightIs(light4);
        light2.nextTrafficLightIs(light3);
        light2.previousTrafficLightIs(light1);
        light3.nextTrafficLightIs(light4);
        light3.previousTrafficLightIs(light2);
        light4.nextTrafficLightIs(light1);
        light4.previousTrafficLightIs(light3);

//        for (int i = 0; i < numberOfTimes; i++) {
//            nextState();
//        }

        assertEquals(1,light1.getCurrentColour());
        assertEquals(2,light2.getCurrentColour());
        assertEquals(1,light3.getCurrentColour());
        assertEquals(1,light4.getCurrentColour());

        for (int i =0;i<5;i++){
            light1.doRun();
            light2.doRun();
            light3.doRun();
            light4.doRun();
            assertEquals(1,light1.getCurrentColour());
            assertEquals(3,light2.getCurrentColour());
            assertEquals(1,light3.getCurrentColour());
            assertEquals(1,light4.getCurrentColour());
        }

        light1.doRun();
        light2.doRun();
        light3.doRun();
        light4.doRun();
        assertEquals(1,light1.getCurrentColour());
        assertEquals(4,light2.getCurrentColour());
        assertEquals(1,light3.getCurrentColour());
        assertEquals(1,light4.getCurrentColour());

        light1.doRun();
        light2.doRun();
        light3.doRun();
        light4.doRun();
        assertEquals(1,light1.getCurrentColour());
        assertEquals(1,light2.getCurrentColour());
        assertEquals(2,light3.getCurrentColour());
        assertEquals(1,light4.getCurrentColour());
    }

    @Test
    public void testAnotherOneGreenWhileOtherRed() throws Exception{
        int checkLight1;
        int checkLight2;
        int checkLight3;
        int checkLight4;

        light1.nextTrafficLightIs(light2);
        light1.previousTrafficLightIs(light4);
        light2.nextTrafficLightIs(light3);
        light2.previousTrafficLightIs(light1);
        light3.nextTrafficLightIs(light4);
        light3.previousTrafficLightIs(light2);
        light4.nextTrafficLightIs(light1);
        light4.previousTrafficLightIs(light3);

//        for (int i = 0; i < numberOfTimes; i++) {
//            nextState();
//        }

        for (int i =0;i<20;i++){
            light1.doRun();
            light2.doRun();
            light3.doRun();
            light4.doRun();
        }

        if(!(light1.getCurrentColour()==1)){
            checkLight1 = 0;
        } else {
            checkLight1 = light1.getCurrentColour();
        }

        if(!(light2.getCurrentColour()==1)){
            checkLight2 = 0;
        } else {
            checkLight2 = light2.getCurrentColour();
        }
        if(!(light3.getCurrentColour()==1)){
            checkLight3 = 0;
        } else {
            checkLight3 = light3.getCurrentColour();
        }
        if(!(light4.getCurrentColour()==1)){
            checkLight4 = 0;
        } else {
            checkLight4 = light1.getCurrentColour();
        }

        assertEquals(3,checkLight1+checkLight2+checkLight3+checkLight4);
    }
}