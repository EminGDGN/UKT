package ukt.model.kenningModel;

public class Interface {
	
	public enum Direction {
		IN,
		OUT,
		INOUT
	}

	private String id; // Id of the interface
	private String name; // Name of the interface
	private boolean enabled; // Is the interface enabled
	private Direction direction; // The direction of this interface (In, Out, Inout)
	private String type; // Type of the interface (String, int, boolean...)
	
	public Interface(String id, String name) {
		this.id = id;
		this.name = name;
		this.enabled = true;
		this.direction = Direction.IN;
		this.type = "String";
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
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
	
	public String getName() {
		return this.name;
	}
	
	public String getId() {
		return this.id;
	}
	
	/**
	 * 
	 * @param d a direction
	 * @return A string equivalent to the direction d
	 */
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
	
	/**
	 * Print all informations about the node
	 */
	public void print() {
		System.out.println("The interface of id : " + this.id + " as the name : " + this.name + " he is " + (this.enabled ? "active" : "inactive") + " and of direction " + this.directionToString(this.direction) + " and of type " + this.type);
	}
}
