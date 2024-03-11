package ukt.fc;

import java.util.ArrayList;

public class Graph {
	
	private int id;
	private String name;
	private ArrayList<Connection> connections;
	private ArrayList<Node> nodes;
	
	public Graph(int id, String name) {
		this.id = id;
		this.name = name;
		connections = new ArrayList<>();
		nodes = new ArrayList<>();
	}
}
