package ukt.model.cwlModel;

import java.util.ArrayList;
import java.util.LinkedList;
import ukt.model.cwlModel.InputParameters.InputParameter;
import ukt.model.cwlModel.InputParameters.Source;

public class Input extends Linkable{

	private ArrayList<InputParameter> parameters;
	
	public Input(String name, Process parent){
		super(name, parent);
		this.parameters = new ArrayList<>();
	}
	
	public void addInputParameter(InputParameter ip) {
		this.parameters.add(ip);
	}
	
	/**
	 * @return a string describing this output to cwl format
	 */
	@Override
	public String toString() {
		String s = this.tab() + this.name + ": \n";
		for (InputParameter parameter : parameters) {
			if(!this.parent.isMainWorkflow() && !(parameter instanceof Source)) {
				continue;
			}else {
				s += parameter.toString();
			}
		}
		return s;
				
	}
	
	public Input clone(Process parent) {
		Input in = new Input(name, parent);
		for(InputParameter p : parameters) {
			if (!(p instanceof Source)) {
				in.addInputParameter(p);
			}
		}
		return in;
	}
}
