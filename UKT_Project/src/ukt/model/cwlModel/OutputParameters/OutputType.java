package ukt.model.cwlModel.OutputParameters;

import ukt.model.cwlModel.Output;
import ukt.model.cwlModel.Types;

public class OutputType extends OutputParameter{
	
	private Types type;

	public OutputType(Output parent, Types type) {
		super(parent);
		this.type = type;
	}

	@Override
	public String toString() {
		return this.tab() + "type: " + this.type.toString() + "\n";
	}

}
