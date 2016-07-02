import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;

import co.s4n.dronedelivery.core.*;
import co.s4n.dronedelivery.io.InputReader;

import org.junit.Before;
import org.junit.Test;

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
		ReadToken("A");
		assertEquals(1, drone.getCurrentPosition().y);
	}
	
	@Test
	public void testCanReadRightToken() throws IOException {
		ReadToken("D");
		assertEquals(Direction.EAST, drone.getCurrentPosition().direction);
	}
	
	@Test
	public void testCanReadLeftToken() throws IOException {
		ReadToken("I");
		assertEquals(Direction.WEST, drone.getCurrentPosition().direction);
	}
	
	private void ReadToken(String token) throws IOException
	{
		when(reader.readLine()).thenReturn(token);
		inputReader.read(reader);
	}

}
