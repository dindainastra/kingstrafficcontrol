package Objects;


public class Bus implements Vehicle {

	private Person driver;

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPriority(int priority) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Person getPerson() {
		// TODO Auto-generated method stub
		return this.driver;
	}


	@Override
	public void setPerson(Person p) {
		this.driver = p;
		
	}

	
	
}
