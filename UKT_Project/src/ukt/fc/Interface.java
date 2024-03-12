package ukt.fc;

public class Interface {
	
	public enum Direction {
		IN,
		OUT,
		INOUT
	}

	private String id;
	private String name;
	private boolean enabled;
	private Direction direction;
	
	public Interface(String id, String name) {
		this.id = id;
		this.name = name;
		this.enabled = true;
		this.direction = Direction.IN;
	}
	
	public void setEnable (Boolean enable) {
		this.enabled = enable;
	}
	
	public void setDirection (Direction dir) {
		this.direction = dir;
	}
	
	public void setDirection (String dir) {
		if (dir.equals("output")) {
			this.direction = Direction.OUT;
		} else if (dir.equals("input")) {
			this.direction = Direction.IN;
		} else {
			this.direction = Direction.INOUT;
		}
	}
	
	public String directionToString (Direction d) {
		switch (d) {
		case IN:
			return "input";
		case OUT:
			return "output";
		case INOUT:
			return "input/output";
		default:
			return "";
		}
	}
	
	public void print() {
		System.out.println("			L'interface d'id : " + this.id + " à pour nom : " + this.name + " et est " + (this.enabled ? "active" : "désactive") + " de direction " + this.directionToString(this.direction));
	}
}
