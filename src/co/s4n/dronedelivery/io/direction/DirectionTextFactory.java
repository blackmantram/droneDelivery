package co.s4n.dronedelivery.io.direction;

import co.s4n.dronedelivery.core.Direction;

public class DirectionTextFactory {
	public IDirectionText getDirectionText(Direction direction)
	{
		switch (direction) {
			case NORTH:
				return new NorthText();
			case SOUTH:
				return new SouthText();
			case EAST:
				return new EastText();
			case WEST:
				return new WestText();
			default:
				return null;
		}
	}
}
