import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import co.s4n.dronedelivery.core.Drone;
import co.s4n.dronedelivery.core.Position;

public class DroneMovementTests {

Drone drone;
	
	@Before
	public void Setup()
	{
		drone = new Drone();
	}
	
	@Test
    public void testMovesNorth() {
        drone.moveForward();
        assertY(1);
    }
	
	@Test
    public void testMovesTwoStepsNorth() {
        drone.moveForward();
        drone.moveForward();
        assertY(2);
    }
	
	@Test
    public void testMovesEast() {
        drone.turnRight();
		drone.moveForward();
        assertX(1);
    }
	
	@Test
    public void testMovesTwoStepsEast() {
		drone.turnRight();
		drone.moveForward();
        drone.moveForward();
        assertX(2);
    }
	
	@Test
    public void testMovesWest() {
        drone.turnLeft();
		drone.moveForward();
        assertX(-1);
    }
	
	@Test
    public void testMovesTwoStepsWest() {
        drone.turnLeft();
		drone.moveForward();
		drone.moveForward();
        assertX(-2);
    }
	
	@Test
    public void testMovesSouth() {
        drone.turnLeft();
        drone.turnLeft();
		drone.moveForward();
        assertY(-1);
    }
	
	@Test
    public void testMovesTwoStepsSouth() {
        drone.turnLeft();
        drone.turnLeft();
		drone.moveForward();
		drone.moveForward();
        assertY(-2);
    }
	
	private void assertX(int expected) {
		Position position = drone.getCurrentPosition();
        assertEquals(expected, position.x);
	}
	
	private void assertY(int expected) {
		Position position = drone.getCurrentPosition();
        assertEquals(expected, position.y);
	}

}
