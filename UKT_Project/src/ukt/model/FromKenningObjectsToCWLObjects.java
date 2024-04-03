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

	public FromKenningObjectsToCWLObjects(Graph graph) {
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

	private ArrayList<Parameter> getMainInParameters() {

		ArrayList<Parameter> parameters = new ArrayList<>();

		for (Node node : this.graph.getInitialNodes()) {
			parameters.addAll(node.getInParameters());
		}

		return parameters;
	}

	private ArrayList<Parameter> getMainOutParameters() {

		ArrayList<Parameter> parameters = new ArrayList<>();

		for (Node node : this.graph.getFinalNodes()) {
			parameters.addAll(node.getOutParameters());
		}

		return parameters;
	}

	private void setMainInParameters() {
		for (Parameter parameter : this.getMainInParameters()) {
			Input input = new Input(parameter.getName(), this.workflow);
			input.addInputParameter(new InputType(input, Types.getEnum(parameter.getType().toLowerCase())));
			this.workflow.addInput(input);
		}
	}

	private void setMainOutParameters() {
		for (Parameter parameter : this.getMainOutParameters()) {
			Output output = new Output(parameter.getName(), this.workflow);
			output.addOutputParameter(new OutputType(output, Types.getEnum(parameter.getType().toLowerCase())));
			output.addOutputParameter(new OutputSource(output,
					this.workflow.getStepByName(this.graph.getNodeOfParameter(parameter).getName()).getProcess()
							.getLinkableByName(parameter.getName())));
			this.workflow.addOutput(output);
		}
	}

	private void linkParameters(Process previousProcess, ArrayList<Parameter> inParameters) {
		for (Parameter inParameter : inParameters) {
			ArrayList<Node> targets = this.graph.getNodeConnectedTo(inParameter);
			if (targets != null) {
				for (Node node : targets) {
					// adds input
					Process process = this.workflow.getStepByName(node.getName()).getProcess();
					Input input = new Input(this.getCorrespondingParameterName(node, inParameter), process);
					System.out.println(inParameter.getName() + "   " + node.getName());
					// input.addInputParameter(new
					// InputType(input,Types.getEnum(inParameter.getType().toLowerCase())));
					input.addInputParameter(
							new Source(input, previousProcess.getLinkableByName(inParameter.getName())));
					process.addInput(input);

					// Adds all out parameters
					ArrayList<Parameter> outParameters = node.getOutParameters();
					for (Parameter outParameter : outParameters) {
						Output output = new Output(outParameter.getName(), process);
						// output.addOutputParameter(new OutputType(output,
						// Types.getEnum(outParameter.getType().toLowerCase())));
						process.addOutput(output);

						linkParameters(process, outParameters);
					}
				}
			}
		}
	}

	private void createAllSteps(ArrayList<Node> nodes) {
		for (Node node : nodes) {
			this.workflow.addStep(new Step(new CommandLineTool(1.2f, node.getName(), node.getName(), this.workflow)));
		}
	}

	private String getCorrespondingParameterName(Node n, Parameter p) {
		ArrayList<String> ids = this.graph.getNextParametersID(p);
		ArrayList<Parameter> parameters = n.getInParameters();
		if (ids != null && parameters != null) {
			for (String string : ids) {
				for (Parameter parameter : parameters) {
					if (string.equals(parameter.getId())) {
						return parameter.getName();
					}
				}
			}
		}
		return p.getName();
	}

}
