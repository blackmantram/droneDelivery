package co.s4n.dronedelivery;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import co.s4n.dronedelivery.core.Drone;
import co.s4n.dronedelivery.io.IReadListener;
import co.s4n.dronedelivery.io.InputReader;
import co.s4n.dronedelivery.io.OutputWriter;

public class Delivery implements IReadListener {
	
	BufferedReader reader;
	BufferedWriter writer;
	InputReader inputReader;
	OutputWriter outputWriter;
	
	public Delivery(BufferedReader reader, BufferedWriter writer, Drone drone) {
		this.reader = reader;
		this.writer = writer;
		this.outputWriter = new OutputWriter(drone);
		this.inputReader = new InputReader(drone);
		this.inputReader.addReadListener(this);
	}

	public void go() throws IOException {
		outputWriter.writeHeader(writer);
		inputReader.read(reader);
		writer.flush();
		writer.close();
	}

	@Override
	public void lineRead() throws IOException {
		outputWriter.writeCurrentPosition(writer);
	}

}
