package elevatormain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;

public class Content extends JPanel {
	
	private int buildingWidth;  
	private int buildingHeight;

	private int floorsNum; 
	private int floorHeight;
	
	static private int MAX_CAPACITY = 10;
	
	private boolean called = false;

	private ArrayList<Floor> floors;
	private int peoplePerFloor = 0;
	private Random random = new Random();
	
	private ArrayList<Person> peopleInElevator;

	private static final long serialVersionUID = 1L;

	// Up decreases y
	// Down increases y
	public static enum STATE {
		Stop, Up, Down
	}

	// start at buildingHeight - 53
	private STATE state = STATE.Down;
	private STATE prevState = STATE.Up;
	private int currentFloor = 0;
	private int stopCount = 0;
	
	private int x, y, velY;

	// Constructor for Elevator class
	public Content(int x, int y, int floorsNum) {
		this.x = x;
		this.y = y;
		velY = 1; // (+) sign moves elevator down, (-) moves elevator up
		setBackground(Color.GRAY);
		
		this.peopleInElevator = new ArrayList<Person>();
		
		this.floorsNum = floorsNum;
		floors = new ArrayList<Floor>();
		
		buildingWidth  = 75;
		buildingHeight = Panel.HEIGHT;
		
		floorHeight = Math.round(Panel.HEIGHT / floorsNum);

		for (int currentFloor = 0; currentFloor < floorsNum; currentFloor++) {
			peoplePerFloor = random.nextInt(15);
			floors.add(new Floor(currentFloor, 0, Math.round((floorsNum - 1 - currentFloor) * floorHeight), peoplePerFloor, this));
		}
	}
	
	public void tick() {
		if (this.state == STATE.Up) {
			up();
		} else if (this.state == STATE.Down) {
			down();
		} else {
			stop();
		}

		y += velY;

		// Get current floor of elevator
		currentFloor = (this.getFloorsNum() - (y / this.getFloorHeight())) - 1;
		
		// When it touches top of frame change state (max floor))
		if (y <= 0 && this.prevState == STATE.Up) {
			this.state = STATE.Up; // increase y
			this.prevState = STATE.Down;
			this.called = false;
		}
		// When it touches bottom of frame change state (ground floor))
		else if ((y >= this.getBuildingHeight() - 52) && this.prevState == STATE.Down) {
			this.state = STATE.Down; // decrease y
			this.prevState = STATE.Up;
			this.called = false;
		}
		// Stop every time it reaches a floor
		else if (y == (this.getBuildingHeight() - 53) - (currentFloor * this.getFloorHeight())) { 
			this.state = STATE.Stop;

			if (stopCount == 0) {
				ArrayList<Person> peopleInCurrentFloor = floors.get(currentFloor).getPeople();
				Iterator<Person> iterator = peopleInCurrentFloor.iterator();
				while (iterator.hasNext()) {
					Person person = iterator.next();
					if (person.getTargetFloor() != currentFloor && peopleInElevator.size() < MAX_CAPACITY) {
						peopleInElevator.add(person);
						iterator.remove();
					}
				}
				iterator = peopleInElevator.iterator();
				while (iterator.hasNext()) {
					Person person = iterator.next();
					if (person.getTargetFloor() == currentFloor) {
						peopleInCurrentFloor.add(person);
						iterator.remove();
					}
				}
				floors.get(currentFloor).setPeople(peopleInCurrentFloor);
				floors.get(currentFloor).repaint();
				revalidate();
				repaint();
			}
		}
		
		repaint();
		
		System.out.println(
				"current floor height: " + (this.getBuildingHeight() - currentFloor * this.getFloorHeight()));
		System.out.println("y: " + y);
		System.out.println("floors: " + this.getBuildingHeight() / this.getFloorHeight());
		System.out.println("Floor height: " + this.getFloorHeight());
		System.out.println("elevator is at floor: " + currentFloor);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.drawRect(Panel.WIDTH / 2 - 32, 0, buildingWidth, buildingHeight);
		for (int i = 0; i < floorsNum ; i++) {
			floors.get(i).draw(g);
		}
		g.fillRect(x, y, this.getBuildingWidth(), this.getBuildingHeight() / this.getFloorsNum());
		g.setColor(Color.BLACK);
		g.drawString(Integer.toString(peopleInElevator.size()), x + this.getBuildingWidth()/2, y + 15 + (this.getBuildingHeight() / this.getFloorsNum())/2);
	}
	
	@Override
	public Dimension getPreferredSize() {
	    return new Dimension(WIDTH, HEIGHT);
	}
	
	public int getBuildingWidth() {
		return buildingWidth;
	}

	public void setBuildingWidth(int buildingWidth) {
		this.buildingWidth = buildingWidth;
	}

	public int getFloorsNum() {
		return floorsNum;
	}

	public void setFloorsNum(int floorsNum) {
		this.floorsNum = floorsNum;
	}

	public int getBuildingHeight() {
		return buildingHeight;
	}

	public void setBuildingHeight(int buildingHeight) {
		this.buildingHeight = buildingHeight;
	}

	public int getFloorHeight() {
		return floorHeight;
	}

	public void setFloorHeight(int floorHeight) {
		this.floorHeight = floorHeight;
	}

	private void up() {
		// increase y
		if (velY == -1) {
			velY *= -1;
		}
		// System.out.println("currentFloor: " + currentFloor);
	}

	private void down() {
		// decrease y
		if (velY == 1) {
			velY *= -1;
		}
	}

	private void stop() {
		velY = 0;
		// stop timer
		if (stopCount == 20) {
			this.state = prevState;
			stopCount = 0;
			if (this.prevState == STATE.Up) {
				this.state = STATE.Down;
				velY = -1;
			} else {
				this.state = STATE.Up;
				velY = 1;
			}
		} else {
			stopCount++;
		}
	}

}
