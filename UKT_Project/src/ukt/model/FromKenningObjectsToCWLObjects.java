package ukt.model;

import java.util.ArrayList;

import ukt.model.cwlModel.CommandLineTool;
import ukt.model.cwlModel.Input;
import ukt.model.cwlModel.Linkable;
import ukt.model.cwlModel.Output;
import ukt.model.cwlModel.Process;
import ukt.model.cwlModel.Step;
import ukt.model.cwlModel.Types;
import ukt.model.cwlModel.Workflow;
import ukt.model.cwlModel.InputParameters.InputType;
import ukt.model.cwlModel.InputParameters.Source;
import ukt.model.cwlModel.OutputParameters.OutputSource;
import ukt.model.cwlModel.OutputParameters.OutputType;
import ukt.model.kenningModel.Graph;
import ukt.model.kenningModel.Node;
import ukt.model.kenningModel.Parameter;

public class FromKenningObjectsToCWLObjects {
	
	public static Workflow translate(Graph graph) {
		//creating overall workflow
		Workflow workflow = new Workflow(1.2f, graph.getName(), null);
			
		//getting first nodes => getting overall cwl inputs
		ArrayList<Node> nodes = graph.getInitialNodes();
		
		//Adding all initial inputs
		for(Node node: nodes) {
			ArrayList<Parameter> parameters = node.getInParameters();
			for(Parameter parameter: parameters) {
				Input input = new Input(parameter.getName(), workflow);
				System.out.println(parameter.getType());
				input.addInputParameter(new InputType(input, Types.getEnum(parameter.getType().toLowerCase())));
				workflow.addInput(new Input(parameter.getName(), workflow));
			}
		}
		
		do {
			
			nodes = graph.getSuivant(nodes.get(0)); // because linear => to improve
			
			for(Node node: nodes) {
				Process process = new CommandLineTool(1.2f, node.getName(), node.getName(), workflow);
				Step step = new Step(process);
							
				for(Parameter param: node.getInParameters()) {
					switch (param.getDirection()) {
					case IN: 
						Input input = new Input(param.getName(), process);
						System.out.println(param.getType());

						input.addInputParameter(new InputType(input, Types.getEnum(param.getType().toLowerCase())));
						Linkable link = (input.getParent().getParent() == null)? input.getParent().getInputs().get(0): input.getParent().getOuputs().get(0);
						input.addInputParameter(new Source(input, link));
						process.addInput(input);
						break;
					case OUT:
						Output output = new Output(param.getName(), process);
						output.addOutputParameter(new OutputType(output, Types.getEnum(param.getType().toLowerCase())));
						process.addOutput(output);
						break;
					case INOUT:
						Input i = new Input(param.getName(), process);
						i.addInputParameter(new InputType(i, Types.getEnum(param.getType().toLowerCase())));
						Linkable l = (i.getParent().getParent() == null)? i.getParent().getInputs().get(0): i.getParent().getOuputs().get(0);
						i.addInputParameter(new Source(i, l));
						process.addInput(i);
						Output o = new Output(param.getName(), process);
						o.addOutputParameter(new OutputType(o, Types.getEnum(param.getType().toLowerCase())));
						process.addOutput(o);
						break;
					default:
						throw new IllegalArgumentException("Unexpected value");
					}
				}
				workflow.addStep(step);
			}
			
		} while (graph.getSuivant(nodes.get(0))!= null); //because lineat we can take only the first element => to improve
		
		
		//Adding all initial outputs
		for(Node node: nodes) {
			ArrayList<Parameter> parameters = node.getOutParameters();
			for(Parameter parameter: parameters) {
				Output output = new Output(parameter.getName(), workflow);
				output.addOutputParameter(new OutputType(output, Types.getEnum(parameter.getType().toLowerCase())));
				output.addOutputParameter(new OutputSource(output, output.getParent().getOuputs().get(0))); //because linear
				workflow.addOutput(new Output(parameter.getName(), workflow));
			}
		}
			
		
		
		
		
		
		
		
		
		
		
		
		
		return workflow;
	}

}
