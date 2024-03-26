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
}
