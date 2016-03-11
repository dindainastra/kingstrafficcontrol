package Objects;

public class Person {

	private String name; //Only for testing purpose
	private int politenessLevel; //1-10 (10 max politeness)
	private boolean pedestrian;
	private int decision;

	public Person(String name, int politenessLevel, boolean pedestrian) {
		super();
		this.name = name;
		this.politenessLevel = politenessLevel;
		this.pedestrian = pedestrian;
		this.decision =  0;
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
	
	public void setDecision(int i){
		this.decision = i;
	}
	
	public int getDecision(){
		return decision;
	}

}
