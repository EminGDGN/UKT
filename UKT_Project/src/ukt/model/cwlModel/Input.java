package ukt.model.cwlModel;

import java.util.ArrayList;
import java.util.LinkedList;
import ukt.model.cwlModel.InputParameters.InputParameter;

public class Input {

	private Process parent;
	private String name;
	private ArrayList<InputParameter> parameters;
	
	public Input(String name, Process parent){
		this.name = name;
		this.parent = parent;
		this.parameters = new ArrayList<>();
	}
	
	public void addInputParameter(InputParameter ip) {
		this.parameters.add(ip);
	}
	
	@Override
	public String toString() {
		String s = this.tab() + this.name + ": \n";
		for (InputParameter parameter : parameters) {
			s += parameter.toString();
		}
		return s;
				
	}
	
	public String tab() {
		return this.parent.tab() + "  ";
		
	}
}
