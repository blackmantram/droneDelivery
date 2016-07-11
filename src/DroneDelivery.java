import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import co.s4n.dronedelivery.Delivery;
import co.s4n.dronedelivery.core.Drone;

public class DroneDelivery {

	public static void main(String[] args) throws IOException {
		String folder = "./data/";
		BufferedReader reader = new BufferedReader(new FileReader(folder+"in.txt"));
		BufferedWriter writer = new BufferedWriter(new FileWriter(folder+"out.txt"));
		Delivery delivery = new Delivery(reader, writer, new Drone());
		delivery.go();
	}

}
