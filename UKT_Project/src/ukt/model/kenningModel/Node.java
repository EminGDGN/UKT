package ukt.model.kenningModel;

import java.util.ArrayList;

import ukt.model.kenningModel.Interface.Direction;

public class Node {
	
	private String id; // Id of the node
	private String name; // Name of the node
	private ArrayList<Interface> interfaces; // All interfaces of the node
	private ArrayList<Property> properties; // All properties of the node
	
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
	
	/**
	 * 
	 * @return all in, out, inout interface/property of this node
	 */
	public ArrayList<Parameter> getParameters() {
		ArrayList<Parameter> result = new ArrayList<>();
		
		for (Interface i : this.interfaces) {
			result.add(new Parameter(i.getName(), i.getType(), i.getDirection()));
		}
		
		for (Property p : this.properties) {
			result.add(new Parameter(p.getName(), p.getType(), p.getDirection()));
		}
		
		return result;
	}
	
	/**
	 * 
	 * @return true if this node is initial (a node is initial if it has no in parameters)
	 */
	public boolean isInitial () {
		return (this.getInParameters().size() - this.properties.size()) == 0;
	}
	
	/**
	 * 
	 * @return all out and inout interface/property of this node
	 */
	public ArrayList<Parameter> getOutParameters() {
		ArrayList<Parameter> result = new ArrayList<>();
		
		for (Interface i : this.interfaces) {
			if (i.getDirection() == Direction.OUT || i.getDirection() == Direction.INOUT) {
				result.add(new Parameter(i.getName(), i.getType(), i.getDirection()));
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @return all in and inout interface/property of this node
	 */
	public ArrayList<Parameter> getInParameters() {
		ArrayList<Parameter> result = new ArrayList<>();
		
		for (Interface i : this.interfaces) {
			if (i.getDirection() == Direction.IN || i.getDirection() == Direction.INOUT) {
				result.add(new Parameter(i.getName(), i.getType(), i.getDirection()));
			}
		}
		
		for (Property p : this.properties) {
			result.add(new Parameter(p.getName(), p.getType(), p.getDirection()));
		}
		
		return result;
	}
	
	public ArrayList<Interface> getInterfaces () { 
		return this.interfaces;
	}
	
	public ArrayList<Property> getProperties () { 
		return this.properties;
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
		System.out.println("The node of id : " + this.id + " as for name : " + this.name);
		System.out.println("The interfaces of this node are : ");
		for (Interface i : this.interfaces) {
			i.print();
		}
		System.out.println("The properties of this node are : ");
		for (Property p : this.properties) {
			p.print();
		}
	}
}
