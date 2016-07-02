package co.s4n.dronedelivery.io;

import java.io.BufferedReader;
import java.io.IOException;

import co.s4n.dronedelivery.core.Drone;

public class InputReader {
	
	private Drone drone;
	
	public InputReader(Drone drone){
		this.drone = drone;
	}

	public void read(BufferedReader reader) throws IOException{
		String token = reader.readLine();
		if (token == "A")
			drone.moveForward();
		if (token == "D")
			drone.turnRight();
		if (token == "I")
			drone.turnLeft();
	}
}
