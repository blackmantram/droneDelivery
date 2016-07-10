package delivery;

import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import co.s4n.dronedelivery.Delivery;
import co.s4n.dronedelivery.config.Texts;
import co.s4n.dronedelivery.core.Drone;

public class DeliveryTests {
	
	Drone drone;
	BufferedReader reader;
	BufferedWriter writer;
	
	@Test
	public void testDelivers() throws IOException {
		setup();
		deliver();
		InOrder inOrder = Mockito.inOrder(reader, writer);
		inOrder.verify(writer).write(Texts.HEADER);
		inOrder.verify(writer).newLine();
		
		inOrder.verify(reader).readLine();
		inOrder.verify(writer).write("(-2, 4) dirección Norte");
		inOrder.verify(writer).newLine();
		
		inOrder.verify(reader).readLine();
		inOrder.verify(writer).write("(-1, 3) dirección Sur");
		inOrder.verify(writer).newLine();
		inOrder.verify(writer).flush();
		inOrder.verify(writer).close();
	}
	
	private void setup() throws IOException {
		drone = new Drone();
		reader = mock(BufferedReader.class);
		writer = mock(BufferedWriter.class);
		setupMock();
	}
	
	private void setupMock() throws IOException
	{
		when(reader.readLine())
		.thenReturn("AAAAIAAD")
		.thenReturn("DDAIAD")
		.thenReturn(null);
	}
	
	private void deliver() throws IOException
	{
		Delivery delivery = new Delivery(reader, writer, drone);
		delivery.go();
	}
}
