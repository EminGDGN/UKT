package ukt.model.kenningModel;

import ukt.model.kenningModel.Interface.Direction;

public class SpecInterface {

	private String name;
	private Direction direction;
	private String type;
	
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
		System.out.println("Voici la spécification de l'interface : " + this.name + " elle est de type " + this.type + " et de direction " + this.directionToString(this.direction));
	}
}
