package ukt.model.cwlModel.OutputParameters;

import ukt.model.cwlModel.Linkable;
import ukt.model.cwlModel.Output;

public class OutputSource extends OutputParameter{
	
	private Linkable source;

	public OutputSource(Output parent, Linkable source) {
		super(parent);
		this.source = source;
	}
	
	@Override
	public String toString() {
		return this.tab() + "outputSource: " + this.source.getCWLPath() + "\n";
	}

}
