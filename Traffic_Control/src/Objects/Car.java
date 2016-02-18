package Objects;


public class Car implements Vehicle {

	private Person driver;
	private int priorityLevel;
	
	public Car(Person p){
		driver = p;
		priorityLevel = 0; //default no priority
	}
	
	public int getPriority() {
		return this.priorityLevel;
	}

	public void setPriority(int priority) {
		this.priorityLevel = priority;
	}

	public String getDriversName() {
		return driver.getName();
	}
	
	public int getDriversPolitenssLevel() {
		return driver.getPolitenessLevel();
	}
	
	public void setPerson(Person p) {
		this.driver = p;
	}

	@Override
	public int getPerson() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
