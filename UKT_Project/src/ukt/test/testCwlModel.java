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
import ukt.model.cwlModel.InputParameters.InputParameter;
import ukt.model.cwlModel.InputParameters.InputType;
import ukt.model.cwlModel.InputParameters.Source;
import ukt.model.cwlModel.OutputParameters.OutputType;

import ukt.model.cwlModel.OutputParameters.OutputParameter;
import ukt.model.cwlModel.OutputParameters.OutputSource;


public class testCwlModel {

public static void main(String[] args) {
		
		//General Process
		Workflow w = new Workflow(1.2f, "echoUppercase", null);
		
		//Inputs
		Input mainInput = new Input("message", w);
		InputParameter miParameter = new InputType(mainInput, Types.String);
		mainInput.addInputParameter(miParameter);
		w.addInput(mainInput);
		
		//Steps
		//echo
		Process echoProcess = new CommandLineTool(1.2f, "echo", "echo", w);
		
		Input in1 = new Input("message", echoProcess);
		in1.addInputParameter(new Source(in1,mainInput));
		echoProcess.addInput(in1);
		
		Output op3 = new Output("out", echoProcess);
		//op3.addOutputParameter(new OutputType(op3, Types.String));
		echoProcess.addOutput(op3);
		
		Step echo = new Step(echoProcess);
		
		//uppercase
		Process upperProcess = new CommandLineTool(1.2f, "uppercase", "uppercase", w);
		Input in2 = new Input("message", upperProcess);
		in2.addInputParameter(new Source(in2,  op3));
		upperProcess.addInput(in2);
		Output op4 = new Output("uppercase_message", upperProcess);
		upperProcess.addOutput(op4);
		Step uppercase = new Step(upperProcess);
		
		//Outputs
		Output output = new Output("out", w);
		OutputParameter op1 = new OutputSource(output,op4);
		OutputParameter op2 = new OutputType(output, Types.String);
		output.addOutputParameter(op1);
		output.addOutputParameter(op2);

		w.addOutput(output);

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
