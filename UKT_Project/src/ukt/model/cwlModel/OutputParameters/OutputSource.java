package ukt.model.cwlModel.OutputParameters;

import ukt.model.cwlModel.Output;

public class OutputSource extends OutputParameter{
	
	private String source;

	public OutputSource(Output parent, String source) {
		super(parent);
		this.source = source;
	}
	
	@Override
	public String toString() {
		return this.tab() + "outputSource: " + this.source;
	}

}
