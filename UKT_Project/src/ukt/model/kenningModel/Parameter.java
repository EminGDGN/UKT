package ukt.model.kenningModel;

import ukt.model.kenningModel.Interface.Direction;

public class Parameter {
	public enum Type {
		INTERFACE,
		PROPERTY
	}
	private String type; // Type of the parameter (String, int, boolean...)
	private String name; // Name of the parameter
	private Direction direction; // Direction of the property (In, Out, Inout)
	private String id; // Id of the param
	private Type paramType; // Type of the param
	
	
	public Parameter (String name, String type, Direction direction, String id, Type t) {
		this.name = name;
		this.type = type;
		this.direction = direction;
		this.id = id;
		this.paramType = t;
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
	
	public String getId () {
		return this.id;
	}
	
	public Type getParamType () {
		return this.paramType;
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
		System.out.println("The parameter : " + this.name + " has id " + this.id + " has type " + this.type + " and for direction : " + this.directionToString(this.direction));
	}
}
