package elevatormain;

public class Person extends ElevatorObject {

	private static final long serialVersionUID = -8466783652465007464L;
	
	private int targetFloor;
	private boolean boarded = false;

	public Person(int x, int y) {
		super(x, y);
	}
	
	//second constructor
	public Person(int x, int y, int targetFloor) {
		super(x, y);
		this.targetFloor = targetFloor;
	}
	
	public int getTargetFloor() {
		return targetFloor;
	}

	public void setBoarded(boolean boarded) {
		this.boarded = boarded;
	}
	
	public boolean isBoarded() {
		return boarded;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.targetFloor);
	}
}
