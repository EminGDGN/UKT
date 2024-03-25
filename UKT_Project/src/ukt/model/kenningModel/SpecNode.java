package ukt.model.kenningModel;

import java.util.ArrayList;

public class SpecNode {
	private String name; //Name of the node
	private ArrayList<SpecInterface> interfaces; //All interfaces of the node
	private ArrayList<SpecProperty> properties; //All properties of the node
	
	public SpecNode(String name) {
		this.name = name;
		interfaces = new ArrayList<>();
		properties = new ArrayList<>();
	}
	
	public ArrayList<SpecInterface>getSpecInterface() {
		return this.interfaces;
	}
	
	public ArrayList<SpecProperty>getSpecProperties() {
		return this.properties;
	}
	
	public String getName() {
		return this.name;
	}
	
	@SuppressWarnings("unchecked")
	public void setInterfaces(ArrayList<SpecInterface> interfaces) {
		this.interfaces = (ArrayList<SpecInterface>)interfaces.clone();
	}
	
	@SuppressWarnings("unchecked")
	public void setProperties(ArrayList<SpecProperty> properties) {
		this.properties = (ArrayList<SpecProperty>)properties.clone();
	}
	
	/**
	 * Print all informations about the node
	 */
	public void print() {
		System.out.println("Here is the specification of the node : " + this.name);
		System.out.println("The interfaces of this node are : ");
		for (SpecInterface i : this.interfaces) {
			i.print();
		}
		System.out.println("The properties of this node are : ");
		for (SpecProperty p : this.properties) {
			p.print();
		}
	}
}
