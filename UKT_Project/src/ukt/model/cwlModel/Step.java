package ukt.model.cwlModel;

import java.util.ArrayList;

public class Step{
	
	private Process process;
	private ArrayList<Process> previousSteps;
	private ArrayList<Process> nextSteps;
	
	public Step(Process process){
		this.process = process;
	}
	
	public String toString() {
		return process.toStep();
	}
	
	public Process process() {
		return process;
	}

}
