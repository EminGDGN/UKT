package ukt.model.kenningModel;

import ukt.model.kenningModel.Interface.Direction;

public class Parameter {
	private String type; // Type of the parameter (String, int, boolean...)
	private String name; // Name of the parameter
	private Direction direction; // Direction of the property (In, Out, Inout)
	
	
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
		System.out.println("The parameter : " + this.name + " as type " + this.type + " and for direction : " + this.directionToString(this.direction));
	}
}
