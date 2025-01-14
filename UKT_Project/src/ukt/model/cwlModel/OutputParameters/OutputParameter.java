package ukt.model.cwlModel.OutputParameters;

import ukt.model.cwlModel.Output;

public abstract class OutputParameter {
	
	protected Output parent;
	
	public OutputParameter(Output parent) {
		this.parent = parent;
	}
	
	/**
	 * 
	 * @return cwl indentation
	 */
	public String tab() {
		return this.parent.tab() + "  ";
	}
	
	public abstract String toString();
	
}
