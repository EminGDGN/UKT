package ukt.model.kenningModel;

import ukt.model.kenningModel.Interface.Direction;

public class Property {

	private String id; // Id of the property
	private String name; // Name of the property
	private String type;
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
		System.out.println("La propriété d'id : " + this.id + " à pour nom : " + this.name + " et pour type " + this.type);
	}
}
