package co.s4n.dronedelivery;

import java.io.IOException;

public class BulkDelivery {
	
	private int MAX_DELIVERIES = 20;
	
	public BulkDelivery(IDeliveryFactory provider) throws IOException {
		for(int i=1; i<=MAX_DELIVERIES; i++)
		{
			String numberFile = getNumberFile(i);
			Delivery delivery = provider.getDelivery("in"+numberFile+".txt", "out"+numberFile+".txt");
			if (delivery == null)
				break;
			delivery.go();
		}
	}
	
	private String getNumberFile(int number)
	{
		return String.format("%02d", number);
	}
}
