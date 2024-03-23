package ukt.model.kenningModel;

public class SpecProperty {
	
	private String name; // Name of the property
	private String type; // Value of the property
	
	public SpecProperty(String name) {
		this.name = name;
	}
	
	public void setType (String type) {
		this.type = type;
	}
	
	public String getName () {
		return this.name;
	}
	
	public String getType() {
		return this.type;
	}
	
	/**
	 * Print all informations about the node
	 */
	public void print() {
		System.out.println("Voici la spécification de la propriété : " + this.name + " elle est de type " + this.type);
	}
}
