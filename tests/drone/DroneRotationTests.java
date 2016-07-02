package drone;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import co.s4n.dronedelivery.core.*;

public class DroneRotationTests {
	
	Drone drone;
	
	@Before
	public void Setup()
	{
		drone = new Drone();
	}
	
	@Test
    public void testStartsAtNorth() {
		assertDirection(Direction.NORTH);
    }

	@Test
    public void testTurnsRight() {
        drone.turnRight();
        assertDirection(Direction.EAST);
    }
	
	@Test
    public void testTurnsLeft() {
        drone.turnLeft();
        assertDirection(Direction.WEST);
    }
	
	@Test
    public void testTurnsSouthClockwise() {
        drone.turnRight();
        drone.turnRight();
        assertDirection(Direction.SOUTH);
    }
	
	@Test
    public void testTurnsSouthCounterclockwise() {
        drone.turnLeft();
        drone.turnLeft();
        assertDirection(Direction.SOUTH);
    }
	
	@Test
    public void testTurns360ClockWise() {
        drone.turnRight();
        drone.turnRight();
        drone.turnRight();
        drone.turnRight();
        assertDirection(Direction.NORTH);
    }

	private void assertDirection(Direction expected) {
		Position position = drone.getCurrentPosition();
        assertEquals(expected, position.direction);
	}

}
