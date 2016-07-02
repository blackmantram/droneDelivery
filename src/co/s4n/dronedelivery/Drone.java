package co.s4n.dronedelivery;

public class Drone {

	private Position currentPosition = new Position();
	
    public void moveForward() {
        currentPosition.y = 1;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

	public void turnRight() {
		currentPosition.direction = Direction.EAST;
	}
    
}
