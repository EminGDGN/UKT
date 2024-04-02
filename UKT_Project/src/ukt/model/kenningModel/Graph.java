package ukt.model.kenningModel;

import java.util.ArrayList;

import ukt.model.kenningModel.Parameter.Type;

public class Graph {
	
	private String id; // Id of the graph
	private String name; // Name of the graph
	private ArrayList<Connection> connections; // All the connections of the graph
	private ArrayList<Node> nodes; // All the nodes of the graph
	
	public Graph(String id, String name) {
		this.id = id;
		this.name = name;
		connections = new ArrayList<>();
		nodes = new ArrayList<>();
	}
	
	public void setNodes (ArrayList<Node> n) {
		this.nodes = n;
	}
	
	public void setConnection (ArrayList<Connection> c) {
		this.connections = c;
	}
	
	public ArrayList<Interface> getInterfaces () {
		ArrayList<Interface> allInterfaces = new ArrayList<Interface>();
		for (Node n : this.nodes) {
			for (Interface i : n.getInterfaces()) {
				allInterfaces.add(i);
			}
		}
		return allInterfaces;
	}
	
	public ArrayList<Node> getNodeConnectedTo (Parameter p) {
		if (p.getParamType() == Type.PROPERTY) {
			return null;
		} else {
			Interface i = this.getInterface(p.getId());
			return this.getSuivant(this.getNodeOfInterface(i));
		}
	}
	
	public Interface getInterface (String id) {
		for (Interface i : this.getInterfaces()) {
			if (i.getId().equals(id)) {
				return i;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @return all the initial node of this graph (an initial node is a node without in parameter)
	 */
	public ArrayList<Node> getInitialNodes() {
		ArrayList<Node> initialNodes = new ArrayList<Node>();
		
		for (Node n : this.nodes) {
			if (n.isInitial()) {
				initialNodes.add(n);
			}
		}
		
		return initialNodes;
	}
	
	/**
	 * 
	 * @param i an interface
	 * @return all node that have this interface
	 */
	public Node getNodeOfInterface(Interface i) {
		for (Node n : this.nodes) {
			if (n.containInterface(i)) {
				return n;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param n a node of this graph
	 * @return A list of every nodes of this graph directly before n in the graph 
	 * (meaning there is a connection from every nodes of the list to n)
	 */
	public ArrayList<Node> getPreviousNodes(Node n) {
		ArrayList<Node> precedent = new ArrayList<>();
		
		for (Interface i : n.getInterfaces()) {
			for (Connection c : this.connections) {
				if (c.getIdTo().equals(i.getId())) {
					if (!precedent.contains(this.getNodeOfInterface(c.getInterfaceFrom()))) {
						precedent.add(this.getNodeOfInterface(c.getInterfaceFrom()));
					}
				}
			}
		}
		
		return precedent;
	}
	
	/**
	 * 
	 * @param n a node of this graph
	 * @return A list of every nodes of this graph directly before n in the graph 
	 * (meaning there is a connection from every nodes of the list to n)
	 */
	public ArrayList<Node> getSuivant(Node n) {
		ArrayList<Node> suivant = new ArrayList<>();
		for (Interface i : n.getInterfaces()) {
			for (Connection c : this.connections) {
				if (c.getIdFrom().equals(i.getId())) {
					if (!suivant.contains(this.getNodeOfInterface(c.getInterfaceTo()))) {
						suivant.add(this.getNodeOfInterface(c.getInterfaceTo()));
					}
				}
			}
		}
		return suivant;
	}
	
	public void print() {
		System.out.println("The graph of id : " + this.id + " as for name : " + this.name);
		System.out.println("The nodes of this graph are : ");
		for (Node n : this.nodes) {
			n.print();
		}
		System.out.println("The connections of this graph are : ");
		for (Connection c : this.connections) {
			c.print();
		}
	}
}
