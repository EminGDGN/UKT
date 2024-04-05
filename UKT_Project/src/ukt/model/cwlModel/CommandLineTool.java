package ukt.model.cwlModel;

public class CommandLineTool extends Process{
	
	private String baseCommand;
	
	public CommandLineTool(float version,String name, String baseCommand, Process parent){
		super(version, name, parent);
		this.baseCommand = baseCommand;
	}
	
	public CommandLineTool(String name, String baseCommand){
		super(name);
		this.baseCommand = baseCommand;
	}
	
	/**
	 * @return a string of the entire cwl file for a specified baseCommand and its parameters
	 */
	@Override
	public String toString() {
		String s = 	super.toString() +
					"class: CommandLineTool\n" +
					"BaseCommand: " + this.baseCommand + "\n";
					s+= "\n" +
					"\n" +
					"inputs: ";
					for (Input input : inputs) {
						s+= input.toString();
					}
					s+= "\n" +
					"outputs: \n";
					for (Output output : outputs) {
						s+= output.toString();
					}
		return s;
				
	}
}
