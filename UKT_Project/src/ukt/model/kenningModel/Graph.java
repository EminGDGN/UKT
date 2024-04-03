package ukt.model.kenningModel;

import java.util.ArrayList;

import ukt.model.kenningModel.Interface.Direction;
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
	
	public ArrayList<String> getNextParametersID(Parameter p) {
		ArrayList<String> result = new ArrayList<>();
		
		if (p.getParamType() == Type.PROPERTY) {
			return null;
		}
		
		Interface i = this.getInterfaceOfParameter(p);
		
		for (Connection c : this.connections) {
			if (c.getIdFrom() == i.getId()) {
				result.add(c.getIdTo());
			}
		}
		
		
		return result;
	}
	
	public ArrayList<Property> getProperties () {
		ArrayList<Property> allProperties = new ArrayList<Property>();
		for (Node n : this.nodes) {
			for (Property p : n.getProperties()) {
				allProperties.add(p);
			}
		}
		return allProperties;
	}
	
	public ArrayList<Node> getNodeConnectedTo (Parameter p) {
		
		if (p.getDirection() == Direction.IN) {
			ArrayList<Node> temp = new ArrayList<>();
			temp.add(this.getNodeOfParameter(p));
			return temp;
		} else {
			if (p.getParamType() == Type.PROPERTY) {
				return null;
			} else {
				Interface i = this.getInterface(p.getId());
				return this.getSuivant(this.getNodeOfInterface(i));
			}
		}
	}
	
	public ArrayList<Node> getFinalNodes () {
		ArrayList<Node> temp = new ArrayList<>();
		for (Node n : this.nodes) {
			if (this.getSuivant(n).size() == 0) {
				temp.add(n);
			}
		}
		return temp;
	}
	
	public Interface getInterface (String id) {
		for (Interface i : this.getInterfaces()) {
			if (i.getId().equals(id)) {
				return i;
			}
		}
		return null;
	}
	
	public Property getProperty (String id) {
		for (Property p : this.getProperties()) {
			if (p.getId().equals(id)) {
				return p;
			}
		}
		return null;
	}
	
	public Node getNodeOfParameter (Parameter p) {
		if (p.getParamType() == Type.INTERFACE) {
			return this.getNodeOfInterface(this.getInterface(p.getId()));
		} else {
			return this.getNodeOfProperty(this.getProperty(p.getId()));
		}
	}
	
	public Interface getInterfaceOfParameter (Parameter p) {
		if (p.getParamType() == Type.INTERFACE) {
			return this.getInterface(p.getId());
		} else {
			return null;
		}
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
	 * @param p a property
	 * @return all node that have this interface
	 */
	public Node getNodeOfProperty(Property p) {
		for (Node n : this.nodes) {
			if (n.containProperty(p)) {
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
	
	public String getName(){
		return this.name;
	}
	
	public ArrayList<Node> getAllNodes(){
		return this.nodes;
	}
}
