package delivery;

import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import co.s4n.dronedelivery.BulkDelivery;
import co.s4n.dronedelivery.Delivery;
import co.s4n.dronedelivery.IDeliveryFactory;

public class BulkDeliveryTests {
	
	IDeliveryFactory fileProvider;
	
	@Before
	public void setup()
	{
		fileProvider = mock(IDeliveryFactory.class);
	}

	@Test
	public void textMakesFirstDelivery() throws IOException {
		Delivery delivery = mock(Delivery.class);
		when(fileProvider.getDelivery("in01.txt", "out01.txt")).thenReturn(delivery);
		new BulkDelivery(fileProvider);
		verify(fileProvider).getDelivery("in01.txt", "out01.txt");
		verify(delivery).go();
	}
	
	@Test
	public void textMakesFirstTwoDeliveries() throws IOException {
		Delivery delivery = mock(Delivery.class);
		when(fileProvider.getDelivery("in01.txt", "out01.txt")).thenReturn(delivery);
		when(fileProvider.getDelivery("in02.txt", "out02.txt")).thenReturn(delivery);
		new BulkDelivery(fileProvider);
		verify(fileProvider, times(2)).getDelivery(any(String.class), any(String.class));
		verify(delivery, times(2)).go();
	}

}
