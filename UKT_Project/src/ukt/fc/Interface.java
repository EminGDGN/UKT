package ukt.fc;

public class Interface {
	
	public enum Status{
		IN,
		OUT
	}

	private int id;
	private String name;
	private boolean enabled;
	private Status direction;
	
	public Interface(int id, String name) {
		this.id = id;
		this.name = name;
		this.enabled = true;
		this.direction = Status.IN;
	}
}
