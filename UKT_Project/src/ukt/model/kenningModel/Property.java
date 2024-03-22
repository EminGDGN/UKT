package ukt.model.kenningModel;

public class Property {

	private String id; // Id of the property
	private String name; // Name of the property
	//private String value; // Value of the property
	
	public Property(String id, String name) {
		this.id = id;
		this.name = name;
		//this.value = value;
	}
	
	/**
	 * Print all informations about the node
	 */
	public void print() {
		System.out.println("La propriété d'id : " + this.id + " à pour nom : " + this.name + " et pour valeur ");
	}
}
