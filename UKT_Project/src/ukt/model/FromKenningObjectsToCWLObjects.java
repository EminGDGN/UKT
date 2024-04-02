package ukt.model;

import java.util.ArrayList;
import java.util.Iterator;

import ukt.model.cwlModel.CommandLineTool;
import ukt.model.cwlModel.Input;
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
	
	Workflow workflow;
	Graph graph;
	
	public FromKenningObjectsToCWLObjects(Graph graph){
		this.graph = graph;
		this.workflow = new Workflow(1.2f, graph.getName(), null);
	}
	
	public Workflow getCWLWorkflow() {
		this.translate();
		return this.workflow;
	}
	
	private void translate() {
				
		this.createAllSteps(graph.getAllNodes());
		
		this.setMainInParameters();
		
		ArrayList<Parameter> mainInParameters = this.getMainInParameters();
		this.linkParameters(this.workflow, mainInParameters);
		
		this.setMainOutParameters();

	}
	
	private ArrayList<Parameter> getMainInParameters(){
		
		ArrayList<Parameter> parameters = new ArrayList<>();
		
		for(Node node: this.graph.getInitialNodes()) {
			parameters.addAll(node.getInParameters());
		}
		
		return parameters;
	}
	
private ArrayList<Parameter> getMainOutParameters(){
		
		ArrayList<Parameter> parameters = new ArrayList<>();
		
		for(Node node: this.graph.getFinalNodes()) {
			parameters.addAll(node.getOutParameters());
		}
		
		return parameters;
	}
	
	private void setMainInParameters() {
		for(Parameter parameter: this.getMainInParameters()) {
			Input input = new Input(parameter.getName(), this.workflow);
			input.addInputParameter(new InputType(input,Types.getEnum(parameter.getType().toLowerCase())));
			this.workflow.addInput(input);
		}
	}
	
	private void setMainOutParameters() {		
		for(Parameter parameter: this.getMainOutParameters()) {
			Output output = new Output(parameter.getName(), this.workflow);
			output.addOutputParameter(new OutputType(output,Types.getEnum(parameter.getType().toLowerCase())));
			output.addOutputParameter(new OutputSource(output, 
					this.workflow.getStepByName(this.graph.getNodeOfParameter(parameter).getName()).getProcess()
					.getLinkableByName(parameter.getName())));
			this.workflow.addOutput(output);
		}
	}

	private void linkParameters(Process previousProcess, ArrayList<Parameter> inParameters) {
		for(Parameter inParameter: inParameters) {
			ArrayList<Node> targets = this.graph.getNodeConnectedTo(inParameter);
			if(targets != null) {
				for(Node node: targets) {
					//adds input
					Process process = this.workflow.getStepByName(node.getName()).getProcess();
					Input input = new Input(inParameter.getName(), process);
					//input.addInputParameter(new InputType(input,Types.getEnum(inParameter.getType().toLowerCase())));
					input.addInputParameter(new Source(input, previousProcess.getLinkableByName(inParameter.getName())));
					process.addInput(input);
					
					//Adds all out parameters
					ArrayList<Parameter> outParameters = node.getOutParameters();
					for(Parameter outParameter: outParameters) {
						Output output = new Output(outParameter.getName(), process);
						output.addOutputParameter(new OutputType(output,  Types.getEnum(outParameter.getType().toLowerCase())));
						process.addOutput(output);
						
						linkParameters(process, outParameters);
					}
				}
			}
		}
	}
	
	private void createAllSteps(ArrayList<Node> nodes) {
		for(Node node: nodes) {
			this.workflow.addStep(new Step(new CommandLineTool(1.2f, node.getName(), node.getName(), this.workflow)));
		}
	}
	
	
	
	
	
	
//	public static Workflow translate(Graph graph) {
//		//creating overall workflow
//		Workflow workflow = new Workflow(1.2f, graph.getName(), null);
//			
//		//getting first nodes => getting overall cwl inputs
//		ArrayList<Node> nodes = graph.getInitialNodes();
//		
//		TranslateFirstInitialNodes(graph, workflow, nodes);
//		
//		return workflow;
//	}
//	
//	public static void TranslateFirstInitialNodes(Graph grapĥ, Workflow workflow, ArrayList<Node> nodes) {
//		//Adding all initial inputs
//		for(Node node: nodes) {
//			
//			//creating step
//			Process process = new CommandLineTool(1.2f, node.getName(), node.getName(), workflow);
//			Step step = new Step(process);
//	
//			for(Parameter parameter: node.getInParameters()) {
//				//add workflow inputs
//				Input inputMain = new Input(parameter.getName(), workflow);
//				inputMain.addInputParameter(new InputType(inputMain, Types.getEnum(parameter.getType().toLowerCase())));
//				workflow.addInput(inputMain);
//				
//				//add initial nodes parameters
//				Input inputInitialNodes = new Input(parameter.getName(), process);
//				inputInitialNodes.addInputParameter(new InputType(inputInitialNodes, Types.getEnum(parameter.getType().toLowerCase())));
//				inputInitialNodes.addInputParameter(new Source(inputInitialNodes, inputMain));
//				process.addInput(inputInitialNodes);
//
//			}
//			
//			ArrayList<Output> outputs = new ArrayList<>();
//			for(Parameter parameter: node.getOutParameters()) {
//				Output output = new Output(parameter.getName(), process);
//				output.addOutputParameter(new OutputType(output,  Types.getEnum(parameter.getType().toLowerCase())));
//				outputs.add(output);
//				process.addOutput(output);
//			}
//			workflow.addStep(step);
//			
//			ArrayList<Node> nextNodes = grapĥ.getSuivant(node);
//			if(nextNodes == null || nextNodes.size()==0) {
//				TranslateMainOutputs(grapĥ, workflow, outputs);
//			}else {
//				TranslateNodes(grapĥ, workflow, nextNodes, outputs);
//			}
//		}
//	}
//	
//	public static void TranslateNodes(Graph graph, Workflow workflow, ArrayList<Node> nodes, ArrayList<Output> comingOutputs) {
//		for(Node node: nodes) {
//			
//			//creating step
//			Process process = new CommandLineTool(1.2f, node.getName(), node.getName(), workflow);
//			Step step = new Step(process);
//	
//			for(Output output: comingOutputs) {
//				
//				//add initial nodes parameters
//				Input input = new Input(output.getName(), process);
//				input.addInputParameter(new Source(input, output));
//				process.addInput(input);
//			}
//			
//			ArrayList<Output> outputs = new ArrayList<>();
//			for(Parameter parameter: node.getOutParameters()) {
//				Output output = new Output(parameter.getName(), process);
//				output.addOutputParameter(new OutputType(output,  Types.getEnum(parameter.getType().toLowerCase())));
//				outputs.add(output);
//				process.addOutput(output);
//			}
//			workflow.addStep(step);
//			
//			ArrayList<Node> nextNodes = graph.getSuivant(node);
//			if(nextNodes == null || nextNodes.size() == 0) {
//				TranslateMainOutputs(graph, workflow, outputs);
//			}else{
//				TranslateNodes(graph, workflow, nextNodes, outputs);
//			}
//		}
//	}
//	
//	public static void TranslateMainOutputs(Graph graph, Workflow workflow, ArrayList<Output> outputs) {
//		for(Output output: outputs) {
//			Output op = new Output(output.getName(), workflow);
//			op.addOutputParameter(new OutputSource(op, output));
//			for (OutputParameter outputParameter : output.getParametrers()) {
//				op.addOutputParameter(outputParameter);
//			}
//			workflow.addOutput(op);
//		}
//		
//	}

}
