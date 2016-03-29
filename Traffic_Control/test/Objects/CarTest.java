package Objects;

import Controllers.CarFlow;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {
    Person p;
    Car car;
    CarFlow.Direction d;

    @Before
    public void setUp() throws Exception {
        int pos_x = 1400;
        int pos_y = 700;
        car = new Car(p,pos_x,pos_y);
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Test:
     * 1. It never goes out of bound, so the x should not be more than 1400
     * and the y should not more than 700
     * 2.
     * @throws Exception
     */
    @Test
    public void testMove() throws Exception {
        /*public void move(CarFlow.Direction d){
        if(d == CarFlow.Direction.LEFT){
            this.pos_x -= 1;
        }else if(d == CarFlow.Direction.RIGHT){
            this.pos_x += 1;
        }else if(d == CarFlow.Direction.DOWN){
            this.pos_y += 1;
        }else if(d == CarFlow.Direction.UP){
            this.pos_y -= 1;
        }
    }*/
        //want to check car move
//        car.move(CarFlow.Direction.DOWN);
//        assertEquals(pos_x,-1);



        //assertFalse(car.get_pos_x()>1400);
        //assertFalse(car.get_pos_y()>700);
    }

    /**
     *
     * @throws Exception
     */

    @Test
    public void testTurn() throws Exception {
        Double turnDir = Math.PI/2;
        //car.turn(CarFlow.Direction.UP);
        assertTrue(car.getRotate() == turnDir || car.getRotate() ==- turnDir);

        /*public void turn(CarFlow.Direction d){
        if(d == CarFlow.Direction.LEFT || d == CarFlow.Direction.UP) {
            rotate = -turnDir;
        }else {
            rotate = turnDir;
        }
    }*/
        //    assertTrue(Car.getRotate() == turnDir || Car.getRotate() ==- turnDir);

    }
}