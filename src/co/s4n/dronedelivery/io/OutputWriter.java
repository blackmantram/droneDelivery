package co.s4n.dronedelivery.io;

import java.io.BufferedWriter;
import java.io.IOException;

import co.s4n.dronedelivery.core.Direction;
import co.s4n.dronedelivery.core.Drone;
import co.s4n.dronedelivery.core.Position;

public class OutputWriter {
	
	private Drone drone;
	
	public  OutputWriter(Drone drone)
	{
		this.drone = drone;
	}

	public void writeHeader(BufferedWriter writer) throws IOException {
		writer.write(Texts.HEADER);
		writer.newLine();
	}

	public void writeCurrentPosition(BufferedWriter writer) throws IOException {
		writer.write("(0, 0) dirección "+getDirectionText());
		writer.newLine();
	}
	
	private String getDirectionText()
	{
		Position position = drone.getCurrentPosition();
		if (position.direction == Direction.NORTH)
			return (Texts.NORTH);
		if (position.direction == Direction.EAST)
			return (Texts.EAST);
		if (position.direction == Direction.SOUTH)
			return (Texts.SOUTH);
		if (position.direction == Direction.WEST)
			return (Texts.OCCIDENTE);
		return "";
	}
}
