package ukt.fc;

public class Property {

	private String id;
	private String name;
	//private String value;
	
	public Property(String id, String name) {
		this.id = id;
		this.name = name;
		//this.value = value;
	}
	
	public void print() {
		System.out.println("La propriété d'id : " + this.id + " à pour nom : " + this.name + " et pour valeur ");
	}
}
