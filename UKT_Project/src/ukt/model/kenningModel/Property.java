package ukt.model.kenningModel;

import ukt.model.kenningModel.Interface.Direction;

public class Property {

	private String id; // Id of the property
	private String name; // Name of the property
	private String type; // Type of the property (String, int, boolean ...)
	//private String value; // Value of the property
	
	public Property(String id, String name) {
		this.id = id;
		this.name = name;
		this.type = "String";
		//this.value = value;
	}
	
	public String getType() {
		return this.type;
	}
	
	public Direction getDirection() {
		return Direction.IN;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return this.name;
	}
	
	/**
	 * Print all informations about the node
	 */
	public void print() {
		System.out.println("The property of id : " + this.id + " as the name : " + this.name + " and for type : " + this.type);
	}
}
