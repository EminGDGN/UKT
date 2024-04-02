package ukt.model.cwlModel;

import java.util.ArrayList;

public class Step{
	
	private Process process;
	private ArrayList<Process> previousSteps;
	private ArrayList<Process> nextSteps;
	
	public Step(Process process){
		this.process = process;
		this.nextSteps = new ArrayList<>();
		this.previousSteps = new ArrayList<>();
	}
	
	public String toString() {
		return process.toStep();
	}
	
	public  ArrayList<Process> getNextSteps() {
		return this.nextSteps;
	}
	
	public  ArrayList<Process> getPreviousSteps() {
		return this.previousSteps;
	}
	
	public void addNextSteps(Process p) {
		this.nextSteps.add(p);
	}
	
	public void addPreviousSteps(Process p) {
		this.previousSteps.add(p);
	}
	
	public Process getProcess() {
		return this.process;
	}
	

}
