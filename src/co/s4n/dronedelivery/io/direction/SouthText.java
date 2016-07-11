package co.s4n.dronedelivery.io.direction;

import co.s4n.dronedelivery.config.Texts;

public class SouthText implements IDirectionText {

	@Override
	public String getText() {
		return Texts.SOUTH;
	}

}
