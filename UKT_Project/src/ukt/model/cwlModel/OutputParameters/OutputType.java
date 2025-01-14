package ukt.model.cwlModel.OutputParameters;

import ukt.model.cwlModel.Output;
import ukt.model.cwlModel.Types;

public class OutputType extends OutputParameter{
	
	private Types type;

	public OutputType(Output parent, Types type) {
		super(parent);
		this.type = type;
	}

	public Types getType() {
		return type;
	}
	
	/**
	 * @return a string describing a type for an output in cwl format
	 */
	@Override
	public String toString() {
		return this.tab() + "type: " + this.type.toString() + "\n";
	}

}
