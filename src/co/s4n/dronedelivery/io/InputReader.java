package co.s4n.dronedelivery.io;

import java.io.BufferedReader;
import java.io.IOException;

import co.s4n.dronedelivery.config.DeliveryConfig;
import co.s4n.dronedelivery.config.Tokens;
import co.s4n.dronedelivery.core.Drone;

public class InputReader {
	
	private Drone drone;
	private IReadListener readListener;
	private int maxReadings = DeliveryConfig.MAX_LINES;
	
	public InputReader(Drone drone){
		this.drone = drone;
	}
	
	public InputReader(Drone drone, int maxReadings){
		this.drone = drone;
		this.maxReadings = maxReadings;
	}

	public void read(BufferedReader reader) throws IOException{
		String line;
		int readings = 0;
		while((line = reader.readLine()) != null)
		{
			readings++;
			for (int i=0; i<line.length(); i++)
			{
				String token = Character.toString(line.charAt(i));
				readToken(token);
			}
			if (readListener != null)
				readListener.lineRead();
			if (readings == maxReadings)
				break;
		}
	}
	
	private void readToken(String token)
	{
		if (token.equals(Tokens.FORWARD))
			drone.moveForward();
		if (token.equals(Tokens.RIGHT))
			drone.turnRight();
		if (token.equals(Tokens.LEFT))
			drone.turnLeft();
	}

	public void addReadListener(IReadListener readListener) {
		this.readListener = readListener;
	}
}
