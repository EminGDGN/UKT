package ukt.model.cwlModel.InputParameters;

import ukt.model.cwlModel.Input;

public class Defalut extends InputParameter{

	private String defaultValue;
	
	public Defalut(Input parent, String defalutValue) {
		super(parent);
		this.defaultValue = defalutValue;
	}
	
	@Override
	public String toString() {
		return this.tab() + "default: "+ this.defaultValue+"\n";
	}
}
