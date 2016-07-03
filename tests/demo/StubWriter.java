package demo;

import java.io.BufferedWriter;
import java.io.Writer;

public class StubWriter extends BufferedWriter {

	private String output = "";
	
	public StubWriter(Writer out) {
		super(out);
	}
	
	public void write(String s) {
		output += s;
	}
	
	public void newLine() {
		output += System.lineSeparator();
	}
	
	public String getOutput()
	{
		return output;
	}

}
