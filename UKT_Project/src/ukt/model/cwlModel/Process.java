package ukt.model.cwlModel;

import java.util.ArrayList;

public abstract class Process {

	protected Process parent; 					//The calling process
	protected String name;
	private float version;						//version of the file => last one is 1.2
	protected ArrayList<Output> outputs;		//Output parameters
	protected ArrayList<Input> inputs;			//In put parameters

	
	Process(float version, String name, Process parent){
		this.name = name;
		this.version = version;
		this.parent = parent;
		outputs = new ArrayList<>();
		inputs = new ArrayList<>();
	}
	
	Process(String name){
		this.name = name;
		this.version = 1.2f;
		this.parent = null;
		outputs = new ArrayList<>();
		inputs = new ArrayList<>();
	}
		
	public void setName(String name) {
		this.name = name;
	}

	public float getVersion() {
		return version;
	}

	public ArrayList<Output> getOutputs() {
		return outputs;
	}

	public ArrayList<Input> getInputs() {
		return inputs;
	}
	
	public void setInputs(ArrayList<Input> inputs) {
		this.inputs = (ArrayList<Input>)inputs.clone();
	}

	public void addInput(Input input) {
		this.inputs.add(input);
	}
	
	public void addOutput(Output output) {
		this.outputs.add(output);
	}
		
	/**
	 * @return Default cwl instruction
	 */
	public String toString() {
		return "cwlVersion: v" + this.version + "\n";
	}
	
	
	/**
	 * 
	 * @return a string describing a process used in a main workflow
	 * 			in cwl format
	 */
	public String toStep() {
		String s = this.almostTab() + this.name + ":\n" +
				   this.tab() + "run : " + this.name + ".cwl\n" +
				   this.tab() +	"in: \n";
			 		for (Linkable input : inputs) {
						s+= input.toString();
					}
					s+= this.tab() + "out:";
					for (Linkable output : outputs) {
						s+= output.toString();
					}
		return s;
	}
	
	/**
	 * 
	 * @return cwl indentation
	 * used only for a new step
	 */
	public String tab() {
		if(this.parent!= null) {
			return this.parent.tab() + "    ";
		}
		return "";
	}
	
	/**
	 * 
	 * @return cwl indentation
	 */
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
	
	/**
	 * 
	 * @return true if this process is the only or encompassing
	 * 		   false otherwise 
	 */
	public boolean isMainWorkflow() {
		return this.getParent() == null;
	}
	
	/**
	 * 
	 * @param name
	 * @return a Linkable by its name
	 * 			This method condsiders only Linkable that can be used in a 'source' field
	 * 			Only general inputs or step outputs
	 */
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
