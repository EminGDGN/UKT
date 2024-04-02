package ukt.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import ukt.model.cwlModel.CommandLineTool;
import ukt.model.cwlModel.Input;
import ukt.model.cwlModel.Output;
import ukt.model.cwlModel.Process;
import ukt.model.cwlModel.Step;
import ukt.model.cwlModel.Types;
import ukt.model.cwlModel.Workflow;
import ukt.model.cwlModel.OutputParameters.OutputParameter;
import ukt.model.cwlModel.OutputParameters.OutputSource;


public class testCwlModel {

public static void main(String[] args) {
		
		//General Process
		Workflow w = new Workflow(1.2f, "echoUppercase", null);
		
		//Inputs
		w.addInput(new Input("message", Types.STRING, w));
		
		//Outputs
		Output output = new Output("out", Types.STRING, w);
		OutputParameter op = new OutputSource(output,"uppercase/uppercase_message");
		output.addOutputParameter(op);
		w.addOutput(output);
		
		//Steps
		Process echoProcess = new CommandLineTool(1.2f, "echo", "echo", w);
		echoProcess.addInput(new Input("message", "message", echoProcess));
		echoProcess.addOutput(new Output("out", echoProcess));
		Step echo = new Step(echoProcess);
		
		Process upperProcess = new CommandLineTool(1.2f, "uppercase", "uppercase", w);
		upperProcess.addInput(new Input("message", "echo/out", upperProcess));
		upperProcess.addOutput(new Output("uppercase_message", upperProcess));
		Step uppercase = new Step(upperProcess);
		
		w.addStep(uppercase);
		w.addStep(echo);

		String cwl = w.toString();
		System.out.println(cwl);
		String path = "echo_upper.cwl";
		
		try {
			Files.writeString(Paths.get(path), cwl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
		
	}
}
