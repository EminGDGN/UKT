package ukt.model.cwlModel;

import java.util.ArrayList;

public abstract class Process {

	protected Process parent;
	protected String name;
	private float version;
	protected ArrayList<Output> outputs;
	protected ArrayList<Input> inputs;

	
	Process(float version, String name, Process parent){
		this.name = name;
		this.version = version;
		this.parent = parent;
		outputs = new ArrayList<Output>();
		inputs = new ArrayList<Input>();
	}
	
	public void addInput(Input input) {
		this.inputs.add(input);
	}
	
	public void addOutput(Output output) {
		this.outputs.add(output);
	}
	
	public String toString() {
		return "cwlVersion: v" + this.version + "\n";
	}
	
	public String toStep() {
		String s = this.almostTab() + this.name + ":\n" +
				   this.tab() + "run : " + this.name + ".cwl\n" +
				   this.tab() +	"in: \n";
			 		for (Input input : inputs) {
						s+= input.toString();
					}
					s+= "\n" +
					this.tab() + "out: ";
					for (Output output : outputs) {
						s+= output.toString();
					}
		return s;
	}
	
	public String tab() {
		if(this.parent!= null) {
			return this.parent.tab() + "    ";
		}
		return "";
	}
	
	public String almostTab() {
		if(this.parent!= null) {
			return this.parent.tab() + "  ";
		}
		return "";
	}
	
	
}
