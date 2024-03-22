package ukt.model.cwlModel.InputParameters;

import ukt.model.cwlModel.Input;

public abstract class InputParameter {
	
	protected Input parent;
	
	InputParameter(Input parent){
		this.parent = parent;
	}
	
	public String tab() {
		return this.parent.tab() +  "  ";
	}
}
