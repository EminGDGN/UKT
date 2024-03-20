package ukt.model.cwlModel;

import java.util.LinkedList;
import ukt.model.cwlModel.InputParameters.InputParameter;

public class Input {

	private Process parent;
	private String name;
	private Types type;
	private String path;
	private LinkedList<InputParameter> parameters;
	
	//constructor used in inputs clause => type is known
	public Input(String name, Types type, Process parent){
		this.name = name;
		this.type = type;
		this.parent = parent;
		this.parameters = new LinkedList<>();
	}
	
	//constructor use in "in" clause => when previous parameter is known
	public Input(String name, String path, Process parent){
		this.name = name;
		this.path = path;
		this.parent = parent;
		this.parameters = new LinkedList<>();

	}
	
	@Override
	public String toString() {
		String s = this.tab() + this.name + ": \n" +
				((this.path != null)? this.tab() + "\tsource: " + this.path : this.tab() + "\ttype: " + this.type.toString());
		for (InputParameter parameter : parameters) {
			s += parameter.toString();
		}
		return s;
				
	}
	
	public String tab() {
		return this.parent.tab() + "\t";
		
	}
}
