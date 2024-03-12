package ukt.fc;

import java.util.ArrayList;

public class Node {
	
	private int id;
	private String name;
	private ArrayList<Interface> interfaces;
	private ArrayList<Property> properties;
	
	public Node(int id, String name) {
		this.id = id;
		this.name = name;
		interfaces = new ArrayList<>();
		properties = new ArrayList<>();
	}
}
