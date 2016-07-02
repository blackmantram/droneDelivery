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
		String line;
		while((line = reader.readLine()) != null)
		{
			for (int i=0; i<line.length(); i++)
			{
				String token = Character.toString(line.charAt(i));
				readToken(token);
			}
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
}
