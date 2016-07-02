import static org.junit.Assert.*;

import org.junit.Test;

import co.s4n.dronedelivery.*;

public class DroneTests {

	@Test
    public void testMovesForward() {
        Drone drone = new Drone();
        drone.moveForward();
        Direction direction = drone.getCurrentPosition();
        assertEquals(1, direction.y);
    }

}
