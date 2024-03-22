package ukt.model.kenningModel;

import java.util.ArrayList;

public class Node {
	
	private String id; //Node ID
	private String name; //Name of the node
	private ArrayList<Interface> interfaces; //All interfaces of the node
	private ArrayList<Property> properties; //All properties of the node
	
	public Node(String id, String name) {
		this.id = id;
		this.name = name;
		interfaces = new ArrayList<>();
		properties = new ArrayList<>();
	}
	
	/**
	 * 
	 * @param i an interface
	 * @return true if this node contains the interface i false otherwise
	 */
	public boolean containInterface (Interface i) {
		for (Interface it : this.interfaces) {
			if (it.getId().equals(i.getId())) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Interface> getInterfaces () { 
		return this.interfaces;
	}
	
	public String getName() {
		return this.name;
	}
	
	@SuppressWarnings("unchecked")
	public void setInterfaces(ArrayList<Interface> interfaces) {
		this.interfaces = (ArrayList<Interface>)interfaces.clone();
	}
	
	@SuppressWarnings("unchecked")
	public void setProperties(ArrayList<Property> properties) {
		this.properties = (ArrayList<Property>)properties.clone();
	}
	
	/**
	 * Print all informations about the node
	 */
	public void print() {
		System.out.println("Le noeud d'id : " + this.id + " à pour nom : " + this.name);
		System.out.println("Les interfaces de ce noeud sont : ");
		for (Interface i : this.interfaces) {
			i.print();
		}
		System.out.println("Les propriétés de ce noeud sont : ");
		for (Property p : this.properties) {
			p.print();
		}
	}
}
