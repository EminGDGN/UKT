package ukt.model.cwlModel.InputParameters;

import ukt.model.cwlModel.Input;

public class Source extends InputParameter{

	private String path;
	
	public Source(Input parent, String path) {
		super(parent);
		this.path = path;
	}

	@Override
	public String toString() {
		return this.tab() + "source: " + this.path + "\n";
	}

}
