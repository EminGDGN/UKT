package ukt.fc;

import java.util.ArrayList;

public class Graph {
	
	private String id;
	private String name;
	private ArrayList<Connection> connections;
	private ArrayList<Node> nodes;
	
	public Graph(String id, String name) {
		this.id = id;
		this.name = name;
		connections = new ArrayList<>();
		nodes = new ArrayList<>();
	}
	
	public void setNodes (ArrayList<Node> n) {
		this.nodes = n;
	}
	
	public void print() {
		System.out.println("Le graph d'id : " + this.id + " à pour nom : " + this.name);
		System.out.println("Les noeuds de ce graph sont : ");
		for (Node n : this.nodes) {
			n.print();
		}
		for (Connection c : this.connections) {
			c.print();
		}
	}
}
