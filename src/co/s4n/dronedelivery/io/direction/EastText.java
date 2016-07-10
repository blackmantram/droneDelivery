package co.s4n.dronedelivery.io.direction;

import co.s4n.dronedelivery.io.Texts;

public class EastText implements IDirectionText {

	@Override
	public String getText() {
		return Texts.EAST;
	}

}
