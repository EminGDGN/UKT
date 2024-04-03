package ukt.model.cwlModel;

import java.util.ArrayList;

import ukt.model.cwlModel.OutputParameters.OutputParameter;
import ukt.model.cwlModel.OutputParameters.OutputSource;
import ukt.model.cwlModel.OutputParameters.OutputType;


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
		for(OutputParameter o : parameters) {
			if(o instanceof OutputType) {
				return ((OutputType)o).getType();
			}
		}
		return null;
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
	
	public Output clone(Process parent) {
		Output out = new Output(name, parent);
		for(OutputParameter p : parameters) {
			if (!(p instanceof OutputSource)) {
				out.addOutputParameter(p);
			}
		}
		return out;
	}

	@Override
	public String toString() {
		if(this.parameters.size() == 0) {
			return " ["+this.name+"]\n";
		}else {
			
			String s = "\n" + this.tab() + this.name + ": \n";
			for (OutputParameter parameter : parameters) {
				s += parameter.toString();
			}
			return s;
		}
	}
}
