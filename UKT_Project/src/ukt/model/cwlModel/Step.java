package ukt.model.cwlModel;

public class Step{
	
	private Process process; // The process it runs
	
	public Step(Process process){
		this.process = process;
	}
	
	public Process process() {
		return process;
	}
	
	public String toString() {
		return process.toStep();
	}
	
	public Process getProcess() {
		return this.process;
	}
	

}
