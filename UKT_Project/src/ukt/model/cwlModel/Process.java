package ukt.model.cwlModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	
	
	
	public Process getParent() {
		return parent;
	}



	public String getName() {
		return name;
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
					s+= this.tab() + "out:";
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
	
	public void run(String name) {
		try {
			java.lang.Process p = Runtime.getRuntime().exec("cwltool " + name + ".cwl");
			
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";           
			while ((line = reader.readLine())!= null) {
				System.out.println(line);
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
