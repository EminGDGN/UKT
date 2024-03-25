package ukt.model.kenningModel;

public class Connection {

	private String id; // Id of the connection
	private Interface from; // Interface from wich the connection start
	private Interface to; // Interface from wich the connection end
	
	public Connection(String id, Interface from, Interface to) {
		this.id = id;
		this.from = from;
		this.to = to;
	}
	
	public String getId () {
		return this.id;
	}
	
	public Interface getInterfaceFrom() {
		return this.from;
	}
	
	public Interface getInterfaceTo() {
		return this.to;
	}
	
	public String getIdFrom() {
		return this.from.getId();
	}
	
	public String getIdTo() {
		return this.to.getId();
	}
	
	public void print() {
		System.out.print("The connection of id : " + this.id + " is going from : ");
		from.print();
		System.out.print(" to ");
		to.print();
		System.out.println();
	}
}
