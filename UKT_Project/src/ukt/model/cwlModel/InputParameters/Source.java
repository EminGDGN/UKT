package ukt.model.cwlModel.InputParameters;

import ukt.model.cwlModel.Input;
import ukt.model.cwlModel.Linkable;

public class Source extends InputParameter{

	private Linkable source;
	
	public Source(Input parent, Linkable source) {
		super(parent);
		this.source = source;
	}

	/**
	 * @return a string describing input source in cwl format
	 */
	@Override
	public String toString() {
		return this.tab() + "source: " + this.source.getCWLPath() + "\n";
	}

}
