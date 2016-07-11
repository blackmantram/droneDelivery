import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import co.s4n.dronedelivery.BulkDelivery;
import co.s4n.dronedelivery.Delivery;
import co.s4n.dronedelivery.IDeliveryFactory;
import co.s4n.dronedelivery.core.Drone;

public class BulkDroneDelivery {
	public static void main(String[] args) throws IOException {
		
		String folder = "./data/";
		
		IDeliveryFactory provider = new IDeliveryFactory() {
			
			@Override
			public Delivery getDelivery(String infilename, String outfilename) {
				try
				{
					BufferedReader reader = new BufferedReader(new FileReader(folder+infilename));
					BufferedWriter writer = new BufferedWriter(new FileWriter(folder+outfilename));
					return new Delivery(reader, writer, new Drone());
				} catch (IOException e) {
					return null;
				}
			}
		};
		BulkDelivery bulkDelivery = new BulkDelivery(provider );
		bulkDelivery.deliverAll();
	}
}
