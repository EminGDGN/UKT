package ukt.model.kenningModel;

import ukt.model.kenningModel.Interface.Direction;

public class Parameter {
	private String type;
	private String name;
	private Direction direction;
	
	
	public Parameter (String name, String type, Direction direction) {
		this.name = name;
		this.type = type;
		this.direction = direction;
	}
	
	public Direction getDirection () {
		return this.direction;
	}
	
	public String getName () {
		return this.name;
	}
	
	public String getType() {
		return this.type;
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
	
	public void print() {
		System.out.println("Paramètre : " + this.name + " est de type " + this.type + " et de direction " + this.directionToString(this.direction));
	}
}
