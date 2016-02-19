package Objects;

/*
 * 
 * Person is used due to the fact we will need to control the pedestrians and the car drivers. 
 * In this case the Hierarchy of the objects should be on top with the Person Object.
 * eg  Person -> Vehicle -> Node -> Node Manager   
 * We will control every event that occurs in the Cars via Person.
 * 
 */

public class Person {

	private String name; //Only for testing purpose
	private int politenessLevel; //1-10 (10 max politeness)
	private boolean pedestrian;
	
	public Person(String name, int politenessLevel, boolean pedestrian) {
		super();
		this.name = name;
		this.politenessLevel = politenessLevel;
		this.pedestrian = pedestrian;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPolitenessLevel() {
		return politenessLevel;
	}

	public void setPolitenessLevel(int politenessLevel) {
		this.politenessLevel = politenessLevel;
	}

	public boolean isPedestrian() {
		return pedestrian;
	}

	public void setPedestrian(boolean pedestrian) {
		this.pedestrian = pedestrian;
	}
	
	
	
	
}
