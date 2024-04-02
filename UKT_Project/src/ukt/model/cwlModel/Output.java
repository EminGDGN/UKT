package ukt.model.cwlModel;

import java.util.ArrayList;
import java.util.LinkedList;

import ukt.model.cwlModel.OutputParameters.OutputParameter;


public class Output {
	private String name;
	private Types type;
	private Process parent;
	private ArrayList<OutputParameter> parameters;
	
	//constructor used for outputs clause
	public Output(String name, Types type, Process parent){
		this.name = name;
		this.type = type;
		this.parent = parent;
		this.parameters = new ArrayList();
	}
	
	//constructi used for out clause
	public Output(String name, Process parent){
		this.name = name;
		this.parent = parent;
	}
	
	public void addOutputParameter(OutputParameter op) {
		this.parameters.add(op);
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Types getType() {
		return type;
	}

	public void setType(Types type) {
		this.type = type;
	}

	public Process getParent() {
		return parent;
	}

	public void setParent(Process parent) {
		this.parent = parent;
	}

	public ArrayList<OutputParameter> getParameters() {
		return parameters;
	}

	public void setParameters(ArrayList<OutputParameter> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		if(this.type == null) {
			return " ["+this.name+"]\n";
		}else {
			String s = this.tab() + this.name + ": \n" +
					this.tab() + "  type: " + this.type.toString() + "\n";
			for (OutputParameter parameter : parameters) {
				s += parameter.toString();
			}
			return s;
		}
	}
	
	public String tab() {
		return this.parent.tab() + "  ";
		
	}
}
