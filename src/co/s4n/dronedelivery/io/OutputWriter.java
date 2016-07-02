package co.s4n.dronedelivery.io;

import java.io.BufferedWriter;
import java.io.IOException;

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
		writer.write(getPositionText()+Texts.CONNECTOR+getDirectionText());
		writer.newLine();
	}
	
	private String getPositionText()
	{
		Position position = drone.getCurrentPosition();
		return "("+position.x+", "+position.y+")";
	}
	
	private String getDirectionText()
	{
		Position position = drone.getCurrentPosition();
		switch (position.direction) {
			case NORTH:
				return (Texts.NORTH);
			case EAST:
				return (Texts.EAST);
			case SOUTH:
				return (Texts.SOUTH);
			case WEST:
				return (Texts.WEST);
			default:
				return "";
		}
	}
}
