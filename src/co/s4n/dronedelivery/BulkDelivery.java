package co.s4n.dronedelivery;

import java.io.IOException;

public class BulkDelivery {
	public BulkDelivery(IDeliveryFactory provider) throws IOException {
		for(int i=1; i<=2; i++)
		{
			Delivery delivery = provider.getDelivery("in0"+i+".txt", "out0"+i+".txt");
			if (delivery != null)
				delivery.go();
		}
	}
}
