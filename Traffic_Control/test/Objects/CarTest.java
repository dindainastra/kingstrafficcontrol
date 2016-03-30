package Objects;

import Controllers.VehicleFlowHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {
    Person p;
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
        car.move(VehicleFlowHelper.Direction.LEFT);
        assertEquals(701,car.get_pos_y());
        car.move(VehicleFlowHelper.Direction.DOWN);
        assertEquals(702,car.get_pos_y());
        car.move(VehicleFlowHelper.Direction.UP);
        assertEquals(701,car.get_pos_y());
        car.move(VehicleFlowHelper.Direction.LEFT);
        assertEquals(1399,car.get_pos_x());
        for(int i = 0; i < 10; i++){
            car.move(VehicleFlowHelper.Direction.LEFT);
        }
        assertEquals(1389,car.get_pos_x());
        car.move(VehicleFlowHelper.Direction.RIGHT);
        assertEquals(1390,car.get_pos_x());
    }

    /**
     *
     * @throws Exception
     */

    @Test
    public void testTurn() throws Exception {
        car.turn(VehicleFlowHelper.Direction.DOWN, VehicleFlowHelper.Direction.DOWN, 90);
        assertEquals((Double)90.0,(Double)car.getRotate());
        car.turn(VehicleFlowHelper.Direction.DOWN, VehicleFlowHelper.Direction.DOWN, 0);
        assertEquals((Double)Math.toRadians(90),(Double)car.getRotate());
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