package delivery;

import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import co.s4n.dronedelivery.BulkDelivery;
import co.s4n.dronedelivery.Delivery;
import co.s4n.dronedelivery.IDeliveryFactory;
import co.s4n.dronedelivery.config.DeliveryConfig;

public class BulkDeliveryTests {
	
	Delivery delivery;
	IDeliveryFactory fileProvider;
	
	@Before
	public void setup()
	{
		delivery = mock(Delivery.class);
		fileProvider = mock(IDeliveryFactory.class);
	}

	@Test
	public void testMakesFirstDelivery() throws IOException {
		callBulkDelivery(1);
		verify(fileProvider).getDelivery("in01.txt", "out01.txt");
		verify(delivery).go();
	}
	
	@Test
	public void testMakesFirstTwentyDeliveries() throws IOException {
		callBulkDelivery(20);
		verify(fileProvider, times(1)).getDelivery("in10.txt", "out10.txt");
		verify(fileProvider, times(1)).getDelivery("in20.txt", "out20.txt");
	}
	
	@Test
	public void testMakesMaxReadingAttempts() throws IOException {
		callBulkDelivery(2);
		verify(fileProvider, times(DeliveryConfig.MAX_DELIVERIES)).getDelivery(any(String.class), any(String.class));
		verify(delivery, times(2)).go();
	}
	
	private void callBulkDelivery(int filesNumber) throws IOException
	{
		for(int i=1; i<=filesNumber; i++)
			ConfigureProviderCall(i, false);
		ConfigureProviderCall(filesNumber+1, true);
		new BulkDelivery(fileProvider).deliverAll();
	}
	
	private void ConfigureProviderCall(int number, Boolean shouldReturnNull)
	{
		String numberFile = getNumberFile(number);
		when(fileProvider.getDelivery("in"+numberFile+".txt", "out"+numberFile+".txt"))
			.thenReturn(shouldReturnNull ? null : delivery);
	}
	
	private String getNumberFile(int number)
	{
		return String.format("%02d", number);
	}

}
