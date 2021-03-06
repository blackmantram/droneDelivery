package io;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;

import co.s4n.dronedelivery.config.DeliveryConfig;
import co.s4n.dronedelivery.config.Tokens;
import co.s4n.dronedelivery.core.*;
import co.s4n.dronedelivery.io.IReadListener;
import co.s4n.dronedelivery.io.InputReader;

import org.junit.Before;
import org.junit.Test;

public class InputTests {
	
	private Drone drone;
	private InputReader inputReader;
	BufferedReader reader;
	
	@Before
	public void setup()
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
	
	@Test
	public void testCallsBackOnLineread() throws IOException {
		IReadListener readListener = mock(IReadListener.class);
		inputReader.addReadListener(readListener);
		readPath(Tokens.FORWARD + Tokens.RIGHT + Tokens.FORWARD);
		inputReader.read(reader);
		verify(readListener, atMost(1)).lineRead();
	}
	
	@Test
	public void testLimitNumberOfReadingsToMaxLines() throws IOException {
		configLineRead(DeliveryConfig.MAX_LINES+10);
		inputReader.read(reader);
		verify(reader, times(DeliveryConfig.MAX_LINES)).readLine();
	}
	
	@Test
	public void testOverwritesNumberOfReadings() throws IOException {
		inputReader = new InputReader(drone, 3);
		when(reader.readLine())
			.thenReturn(Tokens.FORWARD + Tokens.RIGHT)
			.thenReturn(Tokens.FORWARD + Tokens.RIGHT)
			.thenReturn(Tokens.FORWARD + Tokens.RIGHT)
			.thenReturn(Tokens.FORWARD + Tokens.RIGHT)
			.thenReturn(null);
		inputReader.read(reader);
		verify(reader, times(3)).readLine();
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
	
	private void configLineRead(int times) throws IOException{
		String[] subsequentCalls = new String[times-1];
		for (int i=0; i<times-1; i++)
			subsequentCalls[i] =  Tokens.FORWARD + Tokens.RIGHT;
		when(reader.readLine()).thenReturn(Tokens.FORWARD + Tokens.RIGHT, subsequentCalls)
			.thenReturn(null);
	}

}
