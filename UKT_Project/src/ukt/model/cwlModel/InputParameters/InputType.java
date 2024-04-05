package ukt.model.cwlModel.InputParameters;

import ukt.model.cwlModel.Input;
import ukt.model.cwlModel.Types;

public class InputType extends InputParameter{
	
	private Types type;

	public InputType(Input parent, Types type) {
		super(parent);
		this.type = type;
	}

	/**
	 * @return a string describing a type for an input in cwl format
	 */
	@Override
	public String toString() {
		return this.tab() + "type: " + this.type.toString() + "\n";
	}

}
