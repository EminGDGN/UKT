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
	
	@Override
	public String toString() {
		if(this.type == null) {
			return " ["+this.name+"]\n";
		}else {
			String s = this.tab() + this.name + ": \n" +
					this.tab() + "\tType: " + this.type.toString() + "\n";
			for (OutputParameter parameter : parameters) {
				s += parameter.toString();
			}
			return s;
		}
	}
	
	public String tab() {
		return this.parent.tab() + "\t";
		
	}
}
