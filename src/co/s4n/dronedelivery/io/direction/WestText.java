package co.s4n.dronedelivery.io.direction;

import co.s4n.dronedelivery.config.Texts;

public class WestText implements IDirectionText {

	@Override
	public String getText() {
		return Texts.WEST;
	}

}
