package ukt.model.cwlModel;

import java.util.ArrayList;

public class Workflow extends Process{
	
	private ArrayList<Step> steps;
	
	public Workflow(float version, String name, Process parent) {
		super(version, name, parent);
		this.steps = new ArrayList<>();
	}
	
	public void addStep(Step s) {
		this.steps.add(s);
	}

	@Override
	public String toString() {
		String s =  super.toString() + 
					"class: Workflow \n" +
					"\n" +
					"inputs: \n";
		for (Linkable input : inputs) {
			s+= input.toString();
		}
		s+= "\n" +
		"outputs: \n";
		for (Linkable output : outputs) {
			s+= output.toString();
		}
		s+= "\n" +
		"steps: \n";
		for(Step step: steps) {
			s+=  step.toString();
		}
		
		return s;
		
	}
	
	public Step getStepByName(String name) {
		for(Step step: steps) {
			if(step.getProcess().getName().equals(name)) {
				return step;
			}
		}
		return null;
	}

}
