package co.s4n.dronedelivery;

public class Drone {

	private Position currentPosition;
	private Compass compass;
	
	public Drone() {
		currentPosition = new Position();
		currentPosition.direction = Direction.NORTH;
		compass = new Compass();
	}
	
    public void moveForward() {
    	if (currentPosition.direction == Direction.NORTH)
    		currentPosition.y = 1;
    	else if (currentPosition.direction == Direction.EAST)
    		currentPosition.x = 1;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

	public void turnRight() {
		currentPosition.direction = compass.getNextRight();
	}

	public void turnLeft() {
		currentPosition.direction = compass.getNextLeft();
	}
    
}
