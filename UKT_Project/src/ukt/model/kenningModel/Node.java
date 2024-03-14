package ukt.model.kenningModel;

import java.util.ArrayList;

public class Node {
	
	private String id;
	private String name;
	private ArrayList<Interface> interfaces;
	private ArrayList<Property> properties;
	
	public Node(String id, String name) {
		this.id = id;
		this.name = name;
		interfaces = new ArrayList<>();
		properties = new ArrayList<>();
	}
	
	public ArrayList<Interface> getInterfaces () {
		return this.interfaces;
	}
	
	@SuppressWarnings("unchecked")
	public void setInterfaces(ArrayList<Interface> interfaces) {
		this.interfaces = (ArrayList<Interface>)interfaces.clone();
	}
	
	@SuppressWarnings("unchecked")
	public void setProperties(ArrayList<Property> properties) {
		this.properties = (ArrayList<Property>)properties.clone();
	}
	
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
