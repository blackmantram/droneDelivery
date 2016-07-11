package co.s4n.dronedelivery;

import java.io.IOException;

import co.s4n.dronedelivery.config.DeliveryConfig;

public class BulkDelivery {
	
	private IDeliveryFactory provider;
	
	public BulkDelivery(IDeliveryFactory provider) {
		this.provider = provider;
	}
	
	public void deliverAll() throws IOException
	{
		for(int i=1; i<=DeliveryConfig.MAX_DELIVERIES; i++)
		{
			String numberFile = getNumberFile(i);
			Delivery delivery = provider.getDelivery("in"+numberFile+".txt", "out"+numberFile+".txt");
			if (delivery != null)
				delivery.go();
		}
	}
	
	private String getNumberFile(int number)
	{
		return String.format("%02d", number);
	}
}
