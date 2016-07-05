package co.s4n.dronedelivery.io.direction;

import co.s4n.dronedelivery.io.Texts;

public class NorthText implements IDirectionText {

	@Override
	public String getText() {
		return Texts.NORTH;
	}

}
