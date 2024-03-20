package ukt.model.cwlModel.OutputParameters;

import ukt.model.cwlModel.Output;

public abstract class OutputParameter {
	
	protected Output parent;
	
	public OutputParameter(Output parent) {
		this.parent = parent;
	}
	
	public String tab() {
		return this.parent.tab() + "\t";
	}
	
	
}
