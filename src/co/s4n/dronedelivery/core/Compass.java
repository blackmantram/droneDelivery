package co.s4n.dronedelivery.core;

public class Compass {

	private Direction[] directions = {
		Direction.NORTH,
		Direction.EAST,
		Direction.SOUTH,
		Direction.WEST
	};
	private int needle = 0;
	
	public Direction getNextRight()
	{
		needle++;
		if (needle == directions.length)
			needle = 0;
		return directions[needle];
	}

	public Direction getNextLeft() {
		needle--;
		if (needle < 0)
			needle = directions.length-1;
		return directions[needle];
	}
}
