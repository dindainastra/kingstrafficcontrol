package Objects;

import Controllers.CarFlow;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {
    Person p;
    CarFlow.Direction d;
    int pos_x=1400;
    int pos_y=700;
    Car car = new Car(p,pos_x,pos_y);

    /**
     * Test:
     * 1. It never goes out of bound, so the x should not be more than 1400
     * and the y should not more than 700
     * //assertFalse(car.get_pos_x()>1400);
     * //assertFalse(car.get_pos_y()>700);
     * 2.
     * @throws Exception
     */
    @Test
    public void testMove() throws Exception {
        car.move(CarFlow.Direction.DOWN);
        assertEquals(701,car.get_pos_y());
        car.move(CarFlow.Direction.DOWN);
        assertEquals(702,car.get_pos_y());
        car.move(CarFlow.Direction.UP);
        assertEquals(701,car.get_pos_y());
        car.move(CarFlow.Direction.LEFT);
        assertEquals(1399,car.get_pos_x());
        for(int i = 0; i < 10; i++){
            car.move(CarFlow.Direction.LEFT);
        }
        assertEquals(1389,car.get_pos_x());
        car.move(CarFlow.Direction.RIGHT);
        assertEquals(1390,car.get_pos_x());
    }

    /**
     *
     * @throws Exception
     */

    @Test
    public void testTurn() throws Exception {
        car.turn(CarFlow.Direction.DOWN, CarFlow.Direction.DOWN, 90);
        assertEquals((Double)90.0,(Double)car.rotate);
//        car.turn(CarFlow.Direction.DOWN, CarFlow.Direction.DOWN, 0);
//        assertEquals((Double)90.0,(Double)car.rotate);
//        public void turn(CarFlow.Direction d, CarFlow.Direction carDirection, double degree) {
//            double turnDir = degree;
//
//            if(degree == 0){
//                turnDir = Math.toRadians(90);
//            }
//
////		if(carDirection == CarFlow.Direction.RIGHT || carDirection == CarFlow.Direction.UP) {
//            if (d == CarFlow.Direction.UP || d == CarFlow.Direction.LEFT) {
//                rotate = -turnDir;
//            } else if (d == CarFlow.Direction.DOWN || d == CarFlow.Direction.RIGHT) { //works
//                rotate = turnDir;
//            }
////		}else if(carDirection == CarFlow.Direction.LEFT || carDirection == CarFlow.Direction.DOWN){
////			if (d == CarFlow.Direction.UP || d == CarFlow.Direction.LEFT) {
////				rotate = turnDir;
////			} else if (d == CarFlow.Direction.DOWN || d == CarFlow.Direction.RIGHT) { //works
////				rotate = -turnDir;
////			}
////		}
//
//            this.repaint();
//        }
    }
}