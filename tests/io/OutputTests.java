package io;

import static org.mockito.Mockito.*;

import java.io.BufferedWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import co.s4n.dronedelivery.core.Drone;
import co.s4n.dronedelivery.io.OutputWriter;
import co.s4n.dronedelivery.io.Texts;

public class OutputTests {
	
	private Drone drone;
	private OutputWriter otput;
	private BufferedWriter writer;
	
	@Before
	public void setup() {
		drone = new Drone();
		otput = new OutputWriter(drone);
		writer = mock(BufferedWriter.class);
	}
	
	@Test
	public void testCanWriteHeader() throws IOException {
		otput.writeHeader(writer);
		verifyLineWritten(Texts.HEADER);
	}
	
	@Test
	public void testCanWritePositionNorth() throws IOException {
		otput.writeCurrentPosition(writer);
		verifyLineWritten("(0, 0) dirección Norte");
	}
	
	@Test
	public void testCanWritePositionEast() throws IOException {
		drone.turnRight();
		otput.writeCurrentPosition(writer);
		verifyLineWritten("(0, 0) dirección Oriente");
	}
	
	@Test
	public void testCanWritePositionSouth() throws IOException {
		drone.turnRight();
		drone.turnRight();
		otput.writeCurrentPosition(writer);
		verifyLineWritten("(0, 0) dirección Sur");
	}
	
	@Test
	public void testCanWritePositionWest() throws IOException {
		drone.turnLeft();
		otput.writeCurrentPosition(writer);
		verifyLineWritten("(0, 0) dirección Occidente");
	}
	
	@Test
	public void testCanWriteOneYHeadingNorth() throws IOException {
		drone.moveForward();
		otput.writeCurrentPosition(writer);
		verifyLineWritten("(0, 1) dirección Norte");
	}
	
	@Test
	public void testCanWriteMinusOneXHeadingEast() throws IOException {
		drone.turnRight();
		drone.moveForward();
		otput.writeCurrentPosition(writer);
		verifyLineWritten("(1, 0) dirección Oriente");
	}
	
	private void verifyLineWritten(String line) throws IOException
	{
		verify(writer).write(line);
		verify(writer).newLine();
	}

}
