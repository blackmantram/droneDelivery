package demo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;

import org.junit.Before;
import org.junit.Test;

import co.s4n.dronedelivery.Delivery;
import co.s4n.dronedelivery.core.Drone;

public class ExampleDemo {

	Drone drone;
	BufferedReader reader;
	StubWriter writer;
	
	@Before
	public void setup() throws IOException
	{
		reader = mock(BufferedReader.class);
		writer = new StubWriter(new StringWriter());
		drone = new Drone();
		setupInput();
	}
	
	@Test
	public void test() throws IOException {
		Delivery delivery = new Delivery(reader, writer, drone);
		delivery.go();
		assertEquals(getExpectedOutput(), writer.getOutput());
	}
	
	private void setupInput() throws IOException{
		when(reader.readLine())
		.thenReturn("AAAAIAAD")
		.thenReturn("DDAIAD")
		.thenReturn("AAIADAD")
		.thenReturn(null);
	}
	
	private String getExpectedOutput(){
		String out = "";
		out += "== Reporte de entregas ==" + System.lineSeparator();
		out += "(-2, 4) dirección Norte" + System.lineSeparator();
		out += "(-1, 3) dirección Sur" + System.lineSeparator();
		out += "(0, 0) dirección Occidente" + System.lineSeparator();
        return out;
	}

}
