package co.s4n.dronedelivery.io;

import java.io.BufferedWriter;
import java.io.IOException;

import co.s4n.dronedelivery.config.Texts;
import co.s4n.dronedelivery.core.Drone;
import co.s4n.dronedelivery.core.Position;
import co.s4n.dronedelivery.io.direction.DirectionTextFactory;

public class OutputWriter {
	
	private Drone drone;
	private DirectionTextFactory directionTextFactory;
	
	public  OutputWriter(Drone drone)
	{
		this.drone = drone;
		directionTextFactory = new DirectionTextFactory();
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
		return directionTextFactory.getDirectionText(position.direction).getText();
	}
}
