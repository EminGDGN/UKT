package ukt.model.cwlModel;

import java.util.ArrayList;
import java.util.LinkedList;

import ukt.model.cwlModel.OutputParameters.OutputParameter;


public class Output extends Linkable{
	private ArrayList<OutputParameter> parameters;

	
	public Output(String name, Process parent){
		super(name, parent);
		this.parameters = new ArrayList<>();
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
		if(this.parameters.size() == 0) {
			return " ["+this.name+"]\n";
		}else {
			String s = this.tab() + this.name + ": \n";
			for (OutputParameter parameter : parameters) {
				s += parameter.toString();
			}
			return s;
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<OutputParameter> getParametrers(){
		return this.parameters;
	}
}
