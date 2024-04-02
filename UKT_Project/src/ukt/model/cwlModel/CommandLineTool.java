package ukt.model.cwlModel;

public class CommandLineTool extends Process{
	
	private String baseCommand;
	
	public CommandLineTool(float version,String name, String baseCommand, Process parent){
		super(version, name, parent);
		this.baseCommand = baseCommand;
	}
	
	@Override
	public String toString() {
		String s = 	super.toString() +
					"class: CommandLineTool\n" +
					"BaseCommand: " + this.baseCommand + "\n";
					s+= "\n" +
					"\n" +
					"inputs: ";
					for (Linkable input : inputs) {
						s+= input.toString();
					}
					s+= "\n" +
					"outputs: \n";
					for (Linkable output : outputs) {
						s+= output.toString();
					}
		return s;
				
	}
}
