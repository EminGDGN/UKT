package ukt.model.kenningModel;

public class Connection {

	private String id;
	private Interface from;
	private Interface to;
	
	public Connection(String id, Interface from, Interface to) {
		this.id = id;
		this.from = from;
		this.to = to;
	}
	
	public void print() {
		System.out.print("La connection d'id : " + this.id + " vas de ");
		from.print();
		System.out.print(" à ");
		to.print();
		System.out.println();
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
}
