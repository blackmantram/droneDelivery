import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;

import co.s4n.dronedelivery.core.*;
import co.s4n.dronedelivery.io.InputReader;
import co.s4n.dronedelivery.io.Tokens;

import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;

public class InputTests {
	
	private Drone drone;
	private InputReader inputReader;
	BufferedReader reader;
	
	@Before
	public void Setup()
	{
		drone = new Drone();
		inputReader = new InputReader(drone);
		reader = mock(BufferedReader.class);
	}

	@Test
	public void testCanReadForwardToken() throws IOException {
		readPath(Tokens.FORWARD);
		assertEquals(1, drone.getCurrentPosition().y);
	}
	
	@Test
	public void testCanReadRightToken() throws IOException {
		readPath(Tokens.RIGHT);
		assertEquals(Direction.EAST, drone.getCurrentPosition().direction);
	}
	
	@Test
	public void testCanReadLeftToken() throws IOException {
		readPath(Tokens.LEFT);
		assertEquals(Direction.WEST, drone.getCurrentPosition().direction);
	}
	
	@Test
	public void testCanReadPath() throws IOException {
		readPath(Tokens.FORWARD + Tokens.RIGHT + Tokens.FORWARD);
		assertPosition(1,1,Direction.EAST);
	}
	
	@Test
	public void testCanReadFile() throws IOException {
		when(reader.readLine())
			.thenReturn(Tokens.FORWARD + Tokens.RIGHT)
			.thenReturn(Tokens.FORWARD + Tokens.LEFT)
			.thenReturn(null);
		inputReader.read(reader);
		assertPosition(1,1,Direction.NORTH);
	}
	
	private void readPath(String path) throws IOException
	{
		when(reader.readLine()).thenReturn(path).thenReturn(null);
		inputReader.read(reader);
	}
	
	private void assertPosition(int x, int y, Direction dir)
	{
		Position position = drone.getCurrentPosition();
		assertEquals(x, position.x);
		assertEquals(y, position.y);
		assertEquals(dir, position.direction);
	}

}
