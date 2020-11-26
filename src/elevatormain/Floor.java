package elevatormain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Floor extends ElevatorObject {

	private static final long serialVersionUID = 1L;
	public int currentFloor;
	private ArrayList<Person> people = new ArrayList<Person>();
	Random random = new Random();
	
	private int floorsNum;
	private int floorHeight;

	public Floor(int currentFloor, int x, int y, int peoplePerFloor, Content building) {
		super(x, y);
		this.currentFloor = currentFloor; // 0
		
		this.floorsNum = building.getFloorsNum();
		this.floorHeight = building.getFloorHeight();

		for (int i = 0; i < peoplePerFloor; i++) {
			int targetFloor = random.nextInt(building.getFloorsNum()); // floorsNum
			while (targetFloor == currentFloor) {
				targetFloor = random.nextInt(building.getFloorsNum());
			}

			addPerson(new Person(x, y, targetFloor));
		}
	}

//	public ArrayList<Person> emptyFloor() {
//		ArrayList<Person> peopleToRemove = new ArrayList<Person>();
//		Person personRemoved = null;
//		int deletedCount = 0;
//		System.out.println("enters emptFloor method");
//		if (!people.isEmpty()) {
//			for(int i = 0; i < people.size(); i++) {
//				Person person = people.get(i - deletedCount);
//				personRemoved = removePerson(person);
//				peopleToRemove.add(personRemoved);
//				deletedCount++;
//			}
//		}
//		return peopleToRemove;
//	}

	public void draw(Graphics g) {
		g.setColor(Color.cyan);
		g.drawRect(0, currentFloor * this.floorHeight, Panel.WIDTH, this.floorHeight);
		g.drawString(Integer.toString(currentFloor), 5, y + 15);

		int fontSize = 15;
		g.setColor(Color.yellow);
		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
		
		int space = 0;
		int lspace = 270;
		for (int i = 0; i < people.size(); i++) {
			g.drawString(Integer.toString(people.get(i).getTargetFloor()), x + lspace - space, y + 15);
			space += 10;
		}
	}
	
	public ArrayList<Person> getPeople() {
		return people;
	}
	
	public void setPeople(ArrayList<Person> people) {
		this.people = people;
	}

	public void addPerson(Person person) {
		people.add(person);
	}
	
	public Iterator<Person> iterator() {
		return new Iterator<Person>() {
			
			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < people.size();
			}

			@Override
			public Person next() {
				Person person = people.get(index++);
				return person;
			}
			
			@Override
			public void remove() {
				people.remove(index);
				index--;
			}
		};
	}

//	public Person removePerson(Person person) {
//		System.out.println("removePerson method is working");
//		int theIndex = people.indexOf(person);
//		Person removedOne = people.remove(theIndex);
//		return removedOne;
//	}

}
