import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import co.s4n.dronedelivery.*;

public class DroneTests {
	
	Drone drone;
	
	@Before
	public void Setup()
	{
		drone = new Drone();
	}

	@Test
    public void testMovesForward() {
        drone.moveForward();
        Position position = drone.getCurrentPosition();
        assertEquals(1, position.y);
    }
	
	@Test
    public void testTurnsRight() {
        drone.turnRight();
        Position position = drone.getCurrentPosition();
        assertEquals(Direction.EAST, position.direction);
    }

}
