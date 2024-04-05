package ukt.model.kenningModel;

import ukt.model.kenningModel.Interface.Direction;

public class SpecInterface {

	private String name; // Name of the interace
	private Direction direction; // Direction of the interface (In, Out, Inout)
	private String type; // Type of the interface (String, int, boolean ...)
	
	public SpecInterface(String name) {
		this.name = name;
		this.direction = Direction.IN;
		this.type = "String";
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType (String type) {
		this.type = type;
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
		System.out.println("Here is the specification of the interface : " + this.name + " it's type is " + this.type + " and it's direction is " + this.directionToString(this.direction));
	}
}
