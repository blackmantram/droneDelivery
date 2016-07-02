import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import co.s4n.dronedelivery.Direction;
import co.s4n.dronedelivery.Drone;
import co.s4n.dronedelivery.Position;

public class DroneMovementTests {

Drone drone;
	
	@Before
	public void Setup()
	{
		drone = new Drone();
	}
	
	@Test
    public void testMovesForward() {
        drone.moveForward();
        assertY(1);
    }
	
	@Test
    public void testMovesTowardsEast() {
        drone.turnRight();
		drone.moveForward();
        assertX(1);
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
