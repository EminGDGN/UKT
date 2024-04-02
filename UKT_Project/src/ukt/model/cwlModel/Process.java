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
		outputs = new ArrayList<>();
		inputs = new ArrayList<>();
	}
	
	public void addInput(Input input) {
		this.inputs.add(input);
	}
	
	public void addOutput(Output output) {
		this.outputs.add(output);
	}
	
	public ArrayList<Input> getInputs(){
		return this.inputs;
	}
	
	public  ArrayList<Output> getOuputs(){
		return this.outputs;
	}
	
	public String toString() {
		return "cwlVersion: v" + this.version + "\n";
	}
	
	public String toStep() {
		String s = this.almostTab() + this.name + ":\n" +
				   this.tab() + "run : " + this.name + ".cwl\n" +
				   this.tab() +	"in: \n";
			 		for (Linkable input : inputs) {
						s+= input.toString();
					}
					s+= this.tab() + "out: \n";
					for (Linkable output : outputs) {
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
	
	public Process getParent() {
		return this.parent;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isMainWorkflow() {
		return this.getParent() == null;
	}
	
	public Linkable getLinkableByName(String name) {
		if(isMainWorkflow()) {
			for(Input input: this.inputs) {
				if(input.getName().equals(name)) {
					return input;
				}
			}
		}else {
			for(Output output: this.outputs) {
				if(output.getName().equals(name)) {
					return output;
				}
			}
		}
		return null;
	}
	
	
}
