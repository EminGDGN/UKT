package ukt.fc;

public class Connection {

	private int id;
	private Interface from;
	private Interface to;
	
	public Connection(int id, Interface from, Interface to) {
		this.id = id;
		this.from = from;
		this.to = to;
	}
}
